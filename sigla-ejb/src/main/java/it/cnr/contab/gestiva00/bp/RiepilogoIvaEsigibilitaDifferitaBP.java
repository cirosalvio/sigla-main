package it.cnr.contab.gestiva00.bp;

import it.cnr.contab.gestiva00.ejb.*;
import it.cnr.contab.gestiva00.core.bulk.*;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.BulkList;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.util.action.BulkBP;
import it.cnr.jada.util.ejb.EJBCommonServices;


public abstract class RiepilogoIvaEsigibilitaDifferitaBP extends StampaRegistriIvaBP {

	private int status = INSERT;
public RiepilogoIvaEsigibilitaDifferitaBP() {
	this("");
}
public RiepilogoIvaEsigibilitaDifferitaBP(String function) {
	super(function+"Tr");
}
public Stampa_registri_ivaVBulk aggiornaRegistriStampati(
	ActionContext context,
	Stampa_registri_ivaVBulk model)
	throws Throwable {
	
	getRegistri_stampati().reset(context);
		
	BulkList stampeEseguite = new BulkList();
	stampeEseguite = createComponentSession().findRegistriStampati(
															context.getUserContext(),
															model);
	model.setRegistri_stampati(stampeEseguite);

	return model;
}
/**
 * Invocato per creare un modello vuoto da usare su una nuova richiesta di ricerca.
 */
public Riepilogo_iva_esigibilita_differitaVBulk createEmptyModelForSearch(ActionContext context) throws BusinessProcessException {

	try {
		return createNewBulk(context);
	} catch(Exception e) {
		throw handleException(e);
	}
}
public abstract Riepilogo_iva_esigibilita_differitaVBulk createNewBulk(ActionContext context) throws BusinessProcessException;
protected void init(it.cnr.jada.action.Config config,it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {

	super.init(config,context);
	setStatus(SEARCH);
	resetForSearch(context);
}
/**
 * Inzializza il ricevente nello stato di SEARCH.
 */
public void resetForSearch(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
	try {
		setModel(context,createEmptyModelForSearch(context));
		setStatus(SEARCH);
		setDirty(false);
		resetChildren(context);
	} catch(Throwable e) {
		throw new it.cnr.jada.action.BusinessProcessException(e);
	}
}
}
