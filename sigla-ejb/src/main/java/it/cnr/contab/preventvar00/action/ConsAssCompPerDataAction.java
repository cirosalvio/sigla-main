package it.cnr.contab.preventvar00.action;
import java.rmi.RemoteException;

import javax.ejb.RemoveException;

import it.cnr.contab.doccont00.bp.ConsDispCompResDipIstBP;
import it.cnr.contab.doccont00.bp.ConsDispCompResIstCdrGaeBP;
import it.cnr.contab.doccont00.bp.ConsDispCompResIstVoceBP;
import it.cnr.contab.doccont00.bp.ConsDispCompResVoceNatBP;
import it.cnr.contab.doccont00.bp.ConsDispCompetenzaResiduoIstitutoBP;
import it.cnr.contab.preventvar00.consultazioni.bp.ConsAssCompPerDataBP;
import it.cnr.contab.preventvar00.consultazioni.bp.ConsAssCompPerDataDettagliBP;
import it.cnr.contab.preventvar00.consultazioni.bp.ConsVarStanzCompetenzaBP;
import it.cnr.contab.preventvar00.consultazioni.bulk.V_cons_ass_comp_per_dataBulk;
import it.cnr.contab.preventvar00.consultazioni.bulk.V_cons_var_pdggBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Forward;
import it.cnr.jada.util.action.BulkAction;
import it.cnr.jada.util.action.ConsultazioniAction;
import it.cnr.jada.util.action.ConsultazioniBP;


public class ConsAssCompPerDataAction extends BulkAction {


public Forward doCerca(ActionContext context) throws RemoteException, InstantiationException, RemoveException{
	try {
		ConsAssCompPerDataBP bp= (ConsAssCompPerDataBP) context.getBusinessProcess();
		V_cons_ass_comp_per_dataBulk assestato = (V_cons_ass_comp_per_dataBulk)bp.getModel();
		bp.fillModel(context); 
		bp.valorizzaDate(context,assestato);

		
		ConsAssCompPerDataDettagliBP DettBP = (ConsAssCompPerDataDettagliBP) context.createBusinessProcess("ConsAssCompPerDataDettagliBP");
		it.cnr.jada.util.RemoteIterator ri = DettBP.createComponentSession().findVariazioni(context.getUserContext(),assestato);
		
		ri = it.cnr.jada.util.ejb.EJBCommonServices.openRemoteIterator(context,ri);
		if (ri.countElements() == 0) {
			it.cnr.jada.util.ejb.EJBCommonServices.closeRemoteIterator(context,ri);
			throw new it.cnr.jada.comp.ApplicationException("Attenzione: Nessun dato disponibile");
		}
			
		DettBP.setIterator(context,ri);
		DettBP.setMultiSelection(true);
		return context.addBusinessProcess(DettBP);			
		
	} catch (Exception e) {
			return handleException(context,e); 
	}
	
}


public Forward doCambiaGestione(ActionContext context){
	try {
		fillModel(context);
		ConsAssCompPerDataBP bp = (ConsAssCompPerDataBP)context.getBusinessProcess();
	
			V_cons_ass_comp_per_dataBulk bulk = (V_cons_ass_comp_per_dataBulk)bp.getModel();
//			bp.getParametriLivelli(context);
		
		return context.findDefaultForward();
	} catch(Throwable e) {
		return handleException(context,e);
	}
}

}