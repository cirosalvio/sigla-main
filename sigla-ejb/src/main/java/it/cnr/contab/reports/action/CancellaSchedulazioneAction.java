package it.cnr.contab.reports.action;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import it.cnr.contab.reports.bulk.Print_spoolerBulk;
import it.cnr.contab.reports.ejb.OfflineReportComponentSession;
import it.cnr.jada.UserContext;
import it.cnr.jada.action.AbstractAction;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.AdminUserContext;
import it.cnr.jada.action.BusinessProcess;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Forward;
import it.cnr.jada.action.HttpActionContext;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.util.action.FormAction;
import it.cnr.jada.util.action.OptionBP;
import it.cnr.jada.util.ejb.EJBCommonServices;

public class CancellaSchedulazioneAction extends FormAction{
	@Override
	@SuppressWarnings("unused")
	public Forward doDefault(ActionContext actioncontext) throws RemoteException {
		if(((HttpActionContext)actioncontext).getParameter("pgStampa")== null)
			return super.doDefault(actioncontext);
		Long pgStampa = new Long(((HttpActionContext)actioncontext).getParameter("pgStampa").substring(2));
		String indirizzoEMail = ((HttpActionContext)actioncontext).getParameter("indirizzoEMail");
		BusinessProcess bp = actioncontext.getBusinessProcess();
		bp.setResource("pgStampa", String.valueOf(pgStampa));
		bp.setResource("indirizzoEMail", indirizzoEMail);
		UserContext userContext = AdminUserContext.getInstance(actioncontext.getSessionId());
		try {
			Print_spoolerBulk printSpooler = geComponent(actioncontext).findPrintSpooler(userContext, pgStampa);
			if (printSpooler == null){
				openMessage(actioncontext, "La lista di distribuzione della stampa, � stata eliminata!");
				return super.doDefault(actioncontext);
			}
			String msg = "Si conferma la cancellazione dell'indirizzo "+indirizzoEMail+"<BR>dalla lista di distribuzione della stampa \""+printSpooler.getDsStampa()+"\"?";
			OptionBP option = openConfirm( actioncontext, msg, it.cnr.jada.util.action.OptionBP.CONFIRM_YES_NO, "doConfirmCancellaSchedulazione");
			return option;
		} catch (ComponentException e) {
			handleException(actioncontext, e);
		} catch (BusinessProcessException e) {
			handleException(actioncontext, e);
		}
		return super.doDefault(actioncontext);
	}
	public Forward doConfirmCancellaSchedulazione(ActionContext actioncontext,it.cnr.jada.util.action.OptionBP option) {
		UserContext userContext = AdminUserContext.getInstance(actioncontext.getSessionId());
		BusinessProcess bp = actioncontext.getBusinessProcess();
		if (option.getOption() == it.cnr.jada.util.action.OptionBP.YES_BUTTON) {
			try {
				geComponent(actioncontext).cancellaSchedulazione(userContext, new Long(bp.getResource("pgStampa")), bp.getResource("indirizzoEMail"));
				openMessage(actioncontext, "La cancellazione � stata effettuata.");
			} catch (Exception e) {
				handleException(actioncontext, e);
			}
		}
		return actioncontext.findDefaultForward();
	}
	private OfflineReportComponentSession geComponent(ActionContext actioncontext) throws EJBException, RemoteException{
		return (it.cnr.contab.reports.ejb.OfflineReportComponentSession)EJBCommonServices.createEJB("BREPORTS_EJB_OfflineReportComponentSession",it.cnr.contab.reports.ejb.OfflineReportComponentSession.class);
	}
}
