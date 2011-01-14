package it.cnr.contab.gestiva00.actions;

import it.cnr.contab.gestiva00.core.bulk.*;
import it.cnr.contab.gestiva00.bp.*;
import it.cnr.jada.action.*;

/**
 * Insert the type's description here.
 * Creation date: (02/12/2003 13.18.55)
 * @author: Roberto Peli
 */
public abstract class LiquidazioneIvaAnnualeAction extends StampaAction {
/**
 * LiquidazioneIvaAnnualeAction constructor comment.
 */
public LiquidazioneIvaAnnualeAction() {
	super();
}
protected void aggiornaRegistriStampati(ActionContext context)
	throws Throwable {

	//Does nothing by default
}
protected Forward basicDoCerca(
	ActionContext context)
	throws Throwable {

	LiquidazioneIvaAnnualeBP bp= (LiquidazioneIvaAnnualeBP) context.getBusinessProcess();

	bp.rollbackUserTransaction();
	
	Liquidazione_iva_annualeVBulk bulk = (Liquidazione_iva_annualeVBulk)bp.getModel();

	if (bp.isBulkPrintable())
		((IPrintable)bulk).setId_report(null);
	bulk.setRistampa(false);
       	
	bp.setModel(context, bp.manage(context, bulk));
	
	return context.findDefaultForward();
}
protected Forward basicDoRistampa(ActionContext context) 
	throws Throwable {

	LiquidazioneIvaAnnualeBP bp= (LiquidazioneIvaAnnualeBP) context.getBusinessProcess();

	Liquidazione_iva_annualeVBulk bulk = (Liquidazione_iva_annualeVBulk)bp.getModel();
	bulk.setRistampa(false);

	return doStampa(context, bulk, (String)null);
}
protected void basicDoStampaAnnullata(
	ActionContext context)
	throws Throwable {

	context.getBusinessProcess().rollbackUserTransaction();

	((LiquidazioneIvaAnnualeBP)context.getBusinessProcess()).resetForSearch(context);
}
public Forward doConfirmCloseForm(ActionContext context,int option) throws BusinessProcessException {

	context.getBusinessProcess().rollbackUserTransaction();
	
	return super.doConfirmCloseForm(context, option);
}
}
