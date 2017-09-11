package it.cnr.contab.ordmag.richieste.action;

import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.contab.doccont00.bp.*;
import it.cnr.contab.doccont00.ordine.bulk.OrdineBulk;
import it.cnr.contab.ordmag.richieste.bp.GenerazioneOrdineDaRichiesteBP;
import it.cnr.contab.ordmag.richieste.bulk.VRichiestaPerOrdiniBulk;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.comp.ApplicationException;
import it.cnr.jada.util.action.*;
/**
 * Azione che gestisce le richieste relative alla Gestione Documenti Contabili
 * (Lista di Obbligazioni)
 */
public class ListaRichiestePerOrdiniAction extends it.cnr.jada.util.action.SelezionatoreListaAction {
public ListaRichiestePerOrdiniAction() {
	super();
}
/**
 * Gestisce un comando "Conferma"
   	 * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
	 *
	 * @exception <code>RemoteException</code>
 */

public Forward doConfirm(ActionContext context) throws java.rmi.RemoteException {
	try {
		GenerazioneOrdineDaRichiesteBP bp = (GenerazioneOrdineDaRichiesteBP)context.getBusinessProcess();
		bp.setSelection(context);
		bp.confirm(context);
		bp.setMessage("Conferma effettuata");
		return context.findDefaultForward();
	} catch(Throwable e) {
		return handleException(context,e);
	}
}
/**
 * Gestisce un comando "Edit".
     * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
 */
//public Forward doEdit(ActionContext context) {
//	
//	try 
//	{
//		SelezionatoreListaBP listaBp = (SelezionatoreListaBP) context.getBusinessProcess();
//		VRichiestaPerOrdiniBulk richiesta = (VRichiestaPerOrdiniBulk) listaBp.getFocusedElement(context);
//			
//		String status = listaBp.isViewing() ? "V" : "M";
//
//		it.cnr.contab.doccont00.bp.CRUDVirtualObbligazioneBP bp= it.cnr.contab.doccont00.bp.CRUDVirtualObbligazioneBP.getBusinessProcessFor(context, obbligazione, status + "RSWTh");
//
//		bp.edit( context, obbligazione );
//		context.addHookForward("close",this,"doRefreshLista");				
//		return context.addBusinessProcess(bp);
//	} catch(Throwable e) {
//		return handleException(context,e);
//	}
//}
/**
 * Gestisce un comando "Elimina".
     * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
 */

//public Forward doElimina(ActionContext context) throws java.rmi.RemoteException {
//	try {
//		ListaObbligazioniBP bp = (ListaObbligazioniBP)context.getBusinessProcess();
//		bp.setSelection(context);
//		bp.delete(context);
//		bp.setMessage("Cancellazione effettuata");
//		return context.findDefaultForward();
//	} catch(Throwable e) {
//		return handleException(context,e);
//	}
//}
/**
 * Metodo utilizzato per gestire l'aggiunta di un nuovo ordine.
 */
public Forward doEmettiOrdine(ActionContext context) {

	try {

		ListaObbligazioniBP loBP = (ListaObbligazioniBP)context.getBusinessProcess();
		ObbligazioneBulk obblig = (ObbligazioneBulk)loBP.getFocusedElement(context);
		CRUDOrdineBP bp;

		if (obblig==null)
			setErrorMessage(context, "Selezionare una Obbligazione");
		else{
			if (!loBP.isEditable())
				bp = (CRUDOrdineBP)context.createBusinessProcess("CRUDOrdineBP",new Object[] { "V" });
			else
				bp = (CRUDOrdineBP)context.createBusinessProcess("CRUDOrdineBP",new Object[] { "M" });

			OrdineBulk ordine = bp.generaOrdinePer(context,obblig);
			if(ordine == null){
				setErrorMessage(context, "L'impegno selezionato non ha associato nessun ordine");
				return context.findDefaultForward();
			}
			return context.addBusinessProcess(bp);
		}
		return context.findDefaultForward();

	} catch(Exception e) {
		return handleException(context,e);
	}
}
public Forward doRefreshLista(ActionContext context)
{
	try 
	{
		if ( context.getBusinessProcess() instanceof ListaObbligazioniBP )
		{
			ListaObbligazioniBP bp = (ListaObbligazioniBP) context.getBusinessProcess();
			bp.refreshList( context );
			return context.findDefaultForward();
		}
		return context.findDefaultForward();		
	} catch(Exception e) {
		return handleException(context,e);
	}
}
/**
 * Gestisce un comando "ricerca libera".
 */
public Forward doRicercaLibera(ActionContext context) {
	try {
		RicercaLiberaBP bp = (RicercaLiberaBP)context.createBusinessProcess("RicercaLibera");
		bp.setFindbp( (ListaObbligazioniBP)context.getBusinessProcess());
		bp.setPrototype( ((ListaObbligazioniBP)context.getBusinessProcess()).createEmptyModelForFreeSearch(context));
		context.addHookForward("seleziona",this,"doRiportaSelezione");
		return context.addBusinessProcess(bp);
	} catch(Throwable e) {
		return handleException(context,e);
	}
}
/**
 * Gestisce un HookForward di ritorno da un risultato di una ricerca (SelezionatoreBP)
     * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
 */
public Forward doRiportaSelezione(ActionContext context)  throws java.rmi.RemoteException {
	try {
		HookForward caller = (HookForward)context.getCaller();
		OggettoBulk selezione = (OggettoBulk)caller.getParameter("focusedElement");
		if (selezione != null)
		{	
			CRUDObbligazioneBP bp = (CRUDObbligazioneBP)context.createBusinessProcess("CRUDObbligazioneResBP");
			bp.setEditable( true );
			bp.edit( context, selezione);
			return context.addBusinessProcess(bp);
		}
		return context.findDefaultForward();
	} catch(Exception e) {
		return handleException(context,e);
	}
}
public Forward doSelection(ActionContext context,String name) {
	try 
	{
//		fillModel(context);
		AbstractSelezionatoreBP bp = (AbstractSelezionatoreBP)context.getBusinessProcess();
		bp.setFocus(context);
		bp.setSelection(context);
		return context.findDefaultForward();
	}
	catch(Exception e) 
	{
		return handleException(context,e);
	}
}

public it.cnr.jada.action.Forward doSelectAll(it.cnr.jada.action.ActionContext context) {
	try {
		ListaObbligazioniBP bp = (ListaObbligazioniBP)context.getBusinessProcess();
		bp.refresh(context);
		bp.selectAll(context);
        
		return context.findDefaultForward();
	} catch(Throwable e) {
		return handleException(context,e);
	}
}

public it.cnr.jada.action.Forward doDeselectAll(it.cnr.jada.action.ActionContext context) {
	try {
		ListaObbligazioniBP bp = (ListaObbligazioniBP)context.getBusinessProcess();
		bp.refresh(context);
		bp.deselectAll(context);
		return context.findDefaultForward();
	} catch(Throwable e) {
		return handleException(context,e);
	}
}

}
