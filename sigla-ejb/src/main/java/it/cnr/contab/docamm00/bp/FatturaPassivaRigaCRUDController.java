package it.cnr.contab.docamm00.bp;

import java.util.List;

import it.cnr.contab.docamm00.ejb.FatturaPassivaComponentSession;
import it.cnr.contab.docamm00.docs.bulk.Fattura_attiva_rigaBulk;
import it.cnr.contab.docamm00.docs.bulk.Fattura_passiva_rigaBulk;
import it.cnr.contab.docamm00.docs.bulk.Fattura_passivaBulk;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.IntrospectionException;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.util.action.SimpleCRUDBP;
/**
 * Insert the type's description here.
 * Creation date: (10/16/2001 11:32:54 AM)
 * @author: Roberto Peli
 */
public class FatturaPassivaRigaCRUDController extends it.cnr.jada.util.action.SimpleDetailCRUDController {
	private boolean inventoriedChildDeleted = false;
/**
 * FatturaPassivaRigaCRUDController constructor comment.
 * @param name java.lang.String
 * @param modelClass java.lang.Class
 * @param listPropertyName java.lang.String
 * @param parent it.cnr.jada.util.action.FormController
 */
public FatturaPassivaRigaCRUDController(String name, Class modelClass, String listPropertyName, it.cnr.jada.util.action.FormController parent) {
	super(name, modelClass, listPropertyName, parent);
}
/**
 * Restituisce true se � possibile aggiungere nuovi elementi
 */
public boolean isGrowable() {
	
	Fattura_passivaBulk fatturaP = (Fattura_passivaBulk)getParentModel();
	return	super.isGrowable() && !((it.cnr.jada.util.action.CRUDBP)getParentController()).isSearching() &&
			!fatturaP.isPagata();
			//Tolto come da richiesta 423. Per NDC e NDD reimplementato
			//&&
			//fatturaP.getProtocollo_iva() == null &&
			//fatturaP.getProtocollo_iva_generale() == null;
	
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2001 4:23:46 PM)
 * @return boolean
 */
public boolean isInventoriedChildDeleted() {
	return inventoriedChildDeleted;
}
/**
 * Restituisce true se � possibile aggiungere nuovi elementi
 */
public boolean isShrinkable() {
	Fattura_passivaBulk fatturaP = (Fattura_passivaBulk)getParentModel();
	return	super.isShrinkable() && !((it.cnr.jada.util.action.CRUDBP)getParentController()).isSearching() &&
			!fatturaP.isPagata();
			//Tolto come da richiesta 423
			//&&
			//fatturaP.getProtocollo_iva() == null &&
			//fatturaP.getProtocollo_iva_generale() == null;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2001 4:23:46 PM)
 * @param newInventoriedChildDeleted boolean
 */
public void setInventoriedChildDeleted(boolean newInventoriedChildDeleted) {
	inventoriedChildDeleted = newInventoriedChildDeleted;
}
public void validate(ActionContext context,OggettoBulk model) throws ValidationException {
	try {
		((FatturaPassivaComponentSession)(((SimpleCRUDBP)getParentController()).createComponentSession())).validaRiga(context.getUserContext(), (Fattura_passiva_rigaBulk)model);
	} catch (it.cnr.jada.comp.ApplicationException e) {
		throw new ValidationException(e.getMessage());
	} catch (Throwable e) {
		throw new it.cnr.jada.DetailedRuntimeException(e);
	}
}
public void validateForDelete(ActionContext context, OggettoBulk detail) throws ValidationException {
	try {
		Fattura_passiva_rigaBulk fpr = (Fattura_passiva_rigaBulk)detail;
		if (fpr.getTi_associato_manrev() != null && fpr.ASSOCIATO_A_MANDATO.equalsIgnoreCase(fpr.getTi_associato_manrev()))
			throw new ValidationException("Impossibile eliminare il dettaglio \"" + 
											((fpr.getDs_riga_fattura() != null) ?
												fpr.getDs_riga_fattura() :
												String.valueOf(fpr.getProgressivo_riga().longValue())) + 
											"\" perch� associato a mandato.");
		FatturaPassivaComponentSession comp = ((FatturaPassivaComponentSession)(((SimpleCRUDBP)getParentController()).createComponentSession()));
		comp.eliminaRiga(context.getUserContext(), fpr);

		if (!fpr.isPagata() && !fpr.isToBeCreated()) {
			try {
				List result = comp.findManRevRigaCollegati(context.getUserContext(), fpr);
				if (result!=null && !result.isEmpty())
					throw new ValidationException("Impossibile eliminare il dettaglio \"" + 
							((fpr.getDs_riga_fattura() != null) ?
								fpr.getDs_riga_fattura() :
								String.valueOf(fpr.getProgressivo_riga().longValue())) + 
							"\" perch� associato a mandato annullato.");
			} catch (PersistencyException e) {
				throw new ComponentException(e);
			} catch (IntrospectionException e) {
				throw new ComponentException(e);
			}
		}
	} catch (it.cnr.jada.comp.ApplicationException e) {
		throw new ValidationException(e.getMessage());
	} catch (ValidationException e) {
		throw e;
	} catch (Throwable e) {
		throw new it.cnr.jada.DetailedRuntimeException(e);
	}
}
public void writeHTMLToolbar(
		javax.servlet.jsp.PageContext context,
		boolean reset,
		boolean find,
		boolean delete) throws java.io.IOException, javax.servlet.ServletException {

		super.writeHTMLToolbar(context, reset, find, delete);

		String command = null;
		if (getParentController() != null)
			command = (getParentModel() instanceof it.cnr.contab.docamm00.docs.bulk.Nota_di_creditoBulk) ?
							"javascript:submitForm('doStornaDettagli')" :
							(getParentModel() instanceof it.cnr.contab.docamm00.docs.bulk.Nota_di_debitoBulk) ?
							"javascript:submitForm('doAddebitaDettagli')" :
							"javascript:submitForm('doRicercaObbligazione')";
		it.cnr.jada.util.jsp.JSPUtils.toolbarButton(
			context,
			"img/history16.gif",
			!(isInputReadonly() || getDetails().isEmpty() || ((CRUDFatturaPassivaBP)getParentController()).isSearching())? command : null,
			true,
			"Contabilizza",
			HttpActionContext.isFromBootstrap(context));
		
		if (getParentController() instanceof CRUDFatturaPassivaIBP) {
			CRUDFatturaPassivaBP bp = (CRUDFatturaPassivaBP)getParentController();
			boolean enabled = bp.isDetailDoubleable() && 
							  (!(isInputReadonly() || getDetails().isEmpty() || bp.isSearching() || bp.isViewing()) ||
	                            bp.isManualModify()); 
	
			Fattura_passiva_rigaBulk riga = (Fattura_passiva_rigaBulk)getModel();
			enabled = enabled && !(riga==null || riga.getTi_associato_manrev() != null && riga.ASSOCIATO_A_MANDATO.equalsIgnoreCase(riga.getTi_associato_manrev()));

			it.cnr.jada.util.jsp.JSPUtils.toolbarButton(
					context,
					"img/bookmarks16.gif",
					enabled ? "javascript:submitForm('doSdoppiaDettaglio');" : null,
					true,"Sdoppia",
					HttpActionContext.isFromBootstrap(context));
		}
}
}
