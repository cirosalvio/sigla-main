package it.cnr.contab.prevent00.action;
import it.cnr.contab.reports.bp.*;
import it.cnr.jada.action.*;
import it.cnr.jada.util.action.*;

/**
 * Action per il controllo delle azioni di stampa dei documenti dei piani di gestione
 */

public class DocumentoPdGAction extends FormAction {
public DocumentoPdGAction() {
	super();
}

/**
 * Stampa documento piani di gestione per capitolo
 */
public Forward doStampaDocPdgCapitolo(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgcdrprimo_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"1");
		printbp.setReportParameter(1,"C");
		printbp.setReportParameter(2,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}

/**
 * Stampa documento piani di gestione ENTE per capitolo
 */
public Forward doStampaDocPdgEnteCapitolo(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgente_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"C");
		printbp.setReportParameter(1,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}

/**
 * Stampa documento piani di gestione ENTE per funzione
 */
public Forward doStampaDocPdgEnteFunzione(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgente_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"F");
		printbp.setReportParameter(1,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}

/**
 * Stampa documento piani di gestione per natura
 */
public Forward doStampaDocPdgEnteNatura(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgente_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"N");
		printbp.setReportParameter(1,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}

/**
 * Stampa documento piani di gestione per funzione
 */
public Forward doStampaDocPdgFunzione(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgcdrprimo_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"1");
		printbp.setReportParameter(1,"F");
		printbp.setReportParameter(2,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}

/**
 * Stampa documento piani di gestione per natura
 */
public Forward doStampaDocPdgNatura(ActionContext context) {
	try {
		it.cnr.contab.prevent00.bp.DocumentoPdGBP bp = (it.cnr.contab.prevent00.bp.DocumentoPdGBP)context.getBusinessProcess();
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess(bp.getPrintbp());
		printbp.setReportName("/preventivo/pdg/docpdgcdrprimo_voce_funzione_natura.rpt");
		Integer aEs = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
		printbp.setReportParameter(0,"1");
		printbp.setReportParameter(1,"N");
		printbp.setReportParameter(2,aEs.toString());
   	    return context.addBusinessProcess(printbp);
	} catch(BusinessProcessException e) {
		throw new ActionPerformingError(e);
	}
}
}