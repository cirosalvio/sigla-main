package it.cnr.contab.doccont00.action;

import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.contab.doccont00.bp.*;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.util.action.*;
/**
 * Azione che gestisce le richieste relative alla Gestione Documenti Contabili
 * (Lista di Reversali)
 */
public class ListaReversaliAction extends it.cnr.jada.util.action.SelezionatoreListaAction {
public ListaReversaliAction() {
	super();
}
/**
 * Gestisce un comando "Edit".
     * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
 */
public Forward doEdit(ActionContext context) {
	
	try 
	{
		ListaReversaliBP listaBp = (ListaReversaliBP) context.getBusinessProcess();
		ReversaleIBulk reversale = (ReversaleIBulk) listaBp.getFocusedElement(context);
			
		CRUDReversaleBP bp = (CRUDReversaleBP)context.createBusinessProcess("CRUDReversaleBP");
		// bp.setEditable( true );
		bp.setEditable( listaBp.isEditable() );
		bp.edit( context, reversale );
		context.addHookForward("close",this,"doRefreshLista");		
		return context.addBusinessProcess(bp);
	} catch(Throwable e) {
		return handleException(context,e);
	}
}
public Forward doRefreshLista(ActionContext context)
{
	try 
	{
		if ( context.getBusinessProcess() instanceof ListaReversaliBP )
		{
			ListaReversaliBP bp = (ListaReversaliBP) context.getBusinessProcess();
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
		bp.setFindbp( (ListaReversaliBP)context.getBusinessProcess());
		bp.setPrototype( ((ListaReversaliBP)context.getBusinessProcess()).createEmptyModelForFreeSearch(context));
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
			CRUDReversaleBP bp = (CRUDReversaleBP)context.createBusinessProcess("CRUDReversaleBP");
			bp.setEditable( true );
			bp.edit( context, selezione );
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
}
