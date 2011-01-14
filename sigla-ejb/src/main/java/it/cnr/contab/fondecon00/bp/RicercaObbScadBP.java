package it.cnr.contab.fondecon00.bp;

import it.cnr.contab.fondecon00.core.bulk.*;
import it.cnr.contab.fondecon00.ejb.FondoEconomaleComponentSession;
import it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;

/**
 * Gestione della ricerca delle obbligazioni (scadenze) da associare
 * alle spese relative a un fondo economale da reintegrare
 */

public class RicercaObbScadBP extends it.cnr.jada.util.action.BulkBP {

	private int status = INSERT;
	private boolean bringBack = true;
	public RicercaObbScadBP() throws it.cnr.jada.action.BusinessProcessException {
		super("Th");
	}

	public RicercaObbScadBP(String function) throws it.cnr.jada.action.BusinessProcessException {
		super(function+"Th");
	}

	public Filtro_ricerca_obbligazioniVBulk createEmptyModelForSearch(ActionContext context) throws BusinessProcessException {

		try {
			return createNewBulk(context).initializeForSearch(this,context);
		} catch(Exception e) {
			throw handleException(e);
		}
	}

	/**
	 * Crea un OggettoBulk vuoto della classe compatibile con la CRUDComponentSession del ricevente
	 */
	public Filtro_ricerca_obbligazioniVBulk createNewBulk(ActionContext context) throws BusinessProcessException {
		try {
			Filtro_ricerca_obbligazioniVBulk bulk = new Filtro_ricerca_obbligazioniVBulk();
			bulk.setUser(context.getUserInfo().getUserid());
			bulk.setCd_unita_organizzativa(it.cnr.contab.utenze00.bp.CNRUserContext.getCd_unita_organizzativa(context.getUserContext()));
			return bulk;
		} catch(Exception e) {
			throw handleException(e);
		}
	}

protected it.cnr.jada.util.jsp.Button[] createToolbar() {
	it.cnr.jada.util.jsp.Button[] toolbar = new it.cnr.jada.util.jsp.Button[2];
	int i = 0;
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.search");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.startSearch");
//	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.bringBack");
	return toolbar;
}

	/**
	 * Effettua una operazione di ricerca per un attributo di un modello.
	 * @param actionContext contesto dell'azione in corso
	 * @param clauses Albero di clausole da utilizzare per la ricerca
	 * @param bulk prototipo del modello di cui si effettua la ricerca
	 * @param context modello che fa da contesto alla ricerca (il modello del FormController padre del
	 * 			controller che ha scatenato la ricerca)
	 * @return un RemoteIterator sul risultato della ricerca o null se la ricerca non ha ottenuto nessun risultato
	 */
	public it.cnr.jada.util.RemoteIterator find(ActionContext actionContext,it.cnr.jada.persistency.sql.CompoundFindClause clauses,it.cnr.jada.bulk.OggettoBulk bulk,it.cnr.jada.bulk.OggettoBulk context,String property) throws it.cnr.jada.action.BusinessProcessException {

		try {
			FondoEconomaleComponentSession fpcs = (FondoEconomaleComponentSession)actionContext.getBusinessProcess().createComponentSession("CNRFONDECON00_EJB_FondoEconomaleComponentSession", FondoEconomaleComponentSession.class);
			return it.cnr.jada.util.ejb.EJBCommonServices.openRemoteIterator(
														actionContext,
														fpcs.cerca(
																actionContext.getUserContext(),
																clauses,
																bulk,
																context,
																property));
		} catch (it.cnr.jada.comp.ComponentException e) {
			throw handleException(e);
		} catch (java.rmi.RemoteException e) {
			throw handleException(e);
		}
	}

	public it.cnr.jada.util.RemoteIterator findObb_scad(it.cnr.jada.action.ActionContext actionContext) throws it.cnr.jada.action.BusinessProcessException {

		try {
			FondoEconomaleComponentSession fpcs = (FondoEconomaleComponentSession)actionContext.getBusinessProcess().createComponentSession("CNRFONDECON00_EJB_FondoEconomaleComponentSession", FondoEconomaleComponentSession.class);
			return fpcs.cercaObb_scad(actionContext.getUserContext(), (Filtro_ricerca_obbligazioniVBulk)getModel());
		} catch (it.cnr.jada.comp.ComponentException e) {
			throw handleException(e);
		} catch (java.rmi.RemoteException e) {
			throw handleException(e);
		}
	}

	public java.util.Dictionary getSearchResultColumns() {
		return getModel().getBulkInfo().getColumnFieldPropertyDictionary();
	}

	public int getStatus() {
		return status;
	}

	protected void init(it.cnr.jada.action.Config config,it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {

		super.init(config,context);
		setStatus(SEARCH);
	}

public boolean isBringBack() {
	return bringBack;
}
public boolean isBringbackButtonEnabled() {
	return isSearching();
}
public boolean isBringbackButtonHidden() {
	return !isBringBack();
}
	public boolean isSearchButtonHidden() {
		return isSearching();
	}

	public boolean isSearching() {
		return status == SEARCH;
	}

	public boolean isStartSearchButtonHidden() {
		return !isSearching();
	}

	/**
	 * Inzializza il ricevente nello stato di SEARCH.
	 */
	public void resetForSearch(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		try {
			setModel(context, createEmptyModelForSearch(context));
			((it.cnr.contab.fondecon00.core.bulk.Filtro_ricerca_obbligazioniVBulk) getModel()).setFornitore(new it.cnr.contab.anagraf00.core.bulk.TerzoBulk());
			setStatus(SEARCH);
			setDirty(false);
			resetChildren(context);
		} catch(Throwable e) {
			throw new it.cnr.jada.action.BusinessProcessException(e);
		}
	}

public void setBringBack(boolean newBringBack) {
	bringBack = newBringBack;
}
	public void setStatus(int newStatus) {
		status = newStatus;
	}

}
