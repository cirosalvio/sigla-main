package it.cnr.contab.docamm00.consultazioni.action;
import it.cnr.contab.compensi00.ejb.ConsRiepilogoCompensiComponentSession;
import it.cnr.contab.docamm00.consultazioni.bp.ConsRiepilogoCompensiBP;
import it.cnr.contab.docamm00.consultazioni.bp.VisConsRiepilogoCompensiBP;
import it.cnr.contab.docamm00.consultazioni.bulk.VConsRiepCompensiBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Forward;
import it.cnr.jada.action.HookForward;
import it.cnr.jada.util.action.ConsultazioniAction;
import it.cnr.jada.util.action.SelezionatoreListaBP;

import java.rmi.RemoteException;

import javax.ejb.RemoveException;

/**
 * Action di gestione delle variazioni di bilancio preventivo
 */

public class ConsRiepilogoCompensiAction extends ConsultazioniAction {
public ConsRiepilogoCompensiAction() {
	super();
}



//public Forward doCambiaGestione(ActionContext context){
//	try {
//		fillModel(context);
//		ConsVarStanzCompetenzaBP bp = (ConsVarStanzCompetenzaBP)context.getBusinessProcess();
//	
//		VIncarichiAssRicBorseStBulk bulk = (VIncarichiAssRicBorseStBulk)bp.getModel();
//			bp.getParametriLivelli(context);
//		
//		return context.findDefaultForward();
//	} catch(Throwable e) {
//		return handleException(context,e);
//	}
//}
//
//	

public Forward doSeleziona(ActionContext context) {
	try{
		fillModel(context);
		ConsRiepilogoCompensiBP bp = (ConsRiepilogoCompensiBP)context.getBusinessProcess();
		VConsRiepCompensiBulk bulk = (VConsRiepCompensiBulk)bp.getModel();
//		bulk.selezionaRagruppamenti();
		return context.findDefaultForward();
	}catch(it.cnr.jada.bulk.FillException ex){
		return handleException(context, ex);
	}
}
public Forward doCerca(ActionContext context) throws RemoteException, InstantiationException, RemoveException{
	ConsRiepilogoCompensiBP bp= (ConsRiepilogoCompensiBP) context.getBusinessProcess();
	try {
		VConsRiepCompensiBulk incarichi = (VConsRiepCompensiBulk)bp.getModel();
		bp.fillModel(context); 
		bp.controlloSelezioni(context,incarichi);
//		bp.valorizzaTotVariazione(context,variazioni);
//		bp.valorizzaTi_Gestione(context,incarichi);
//		bp.valorizzaCd_livello1(context,variazioni);
		
	
			it.cnr.jada.util.RemoteIterator ri = ((ConsRiepilogoCompensiComponentSession)bp.createComponentSession()).findRiepilogoCompensi(context.getUserContext(),incarichi);
			ri = it.cnr.jada.util.ejb.EJBCommonServices.openRemoteIterator(context,ri);
			if (ri.countElements() == 0) {
					it.cnr.jada.util.ejb.EJBCommonServices.closeRemoteIterator(context,ri);
					throw new it.cnr.jada.comp.ApplicationException("Attenzione: Nessun dato disponibile.");
			}
			VisConsRiepilogoCompensiBP nbp = (VisConsRiepilogoCompensiBP)context.createBusinessProcess("VisConsRiepilogoCompensiBP");
			nbp.setIterator(context,ri);
			nbp.disableSelection();
			nbp.setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(VConsRiepCompensiBulk.class)); 
			nbp.setColumns(nbp.getBulkInfo().getColumnFieldPropertyDictionary("CONSULTAZIONE"));
			nbp.setFreeSearchSet("CONSULTAZIONE");
			HookForward hook = (HookForward)context.findForward("seleziona"); 
			return context.addBusinessProcess(nbp); 
				
		
	} catch (Exception e) {
			return handleException(context,e); 
	}
	
}
public Forward doCloseForm(ActionContext actioncontext)
		throws BusinessProcessException
	{
		try
		{
				return doConfirmCloseForm(actioncontext, 4);
		}
		catch(Throwable throwable)
		{
			return handleException(actioncontext, throwable);
		}
	}


}