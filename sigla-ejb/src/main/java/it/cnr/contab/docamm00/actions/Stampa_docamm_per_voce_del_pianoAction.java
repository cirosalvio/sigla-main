package it.cnr.contab.docamm00.actions;

import it.cnr.contab.config00.pdcfin.bulk.Elemento_voceBulk;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
/**
 * Insert the type's description here.
 * Creation date: (26/08/2004 9.50.38)
 * @author: Gennaro Borriello
 */
public class Stampa_docamm_per_voce_del_pianoAction extends it.cnr.contab.reports.action.ParametricPrintAction {
/**
 * Stampa_docamm_per_voce_del_pianoAction constructor comment.
 */
public Stampa_docamm_per_voce_del_pianoAction() {
	super();
}
/**
 * Nuova ricerca di una voce del piano
 *	Quando l'utente azzera il search_tool relativo alla voce del piano, all'UO, il sistema
 *	ripulisce anche il campo relativo alla voce del piano.
 *
 *	@param context, l'<code>ActionContext</code> che ha generato la richiesta.
 *	@param stampa, la <code>Stampa_docamm_per_voce_del_pianoVBulk</code> stampa.
 *
 *	@return <code>it.cnr.jada.action.Forward</code>

 */
public it.cnr.jada.action.Forward doBlankSearchFindUOForPrint(it.cnr.jada.action.ActionContext context, it.cnr.contab.docamm00.docs.bulk.Stampa_compensi_per_vpVBulk stampa) {

	//stampa.setVoce_del_piano("");
	stampa.setVocedpForPrint(new Elemento_voceBulk());
	stampa.setUoForPrint(new Unita_organizzativaBulk());
	
	return context.findDefaultForward();
}
/**
 * Nuova ricerca di una voce del piano
 *	Quando l'utente azzera il search_tool relativo alla voce del piano, all'UO, il sistema
 *	ripulisce anche il campo relativo alla voce del piano.
 *
 *	@param context, l'<code>ActionContext</code> che ha generato la richiesta.
 *	@param stampa, la <code>Stampa_docamm_per_voce_del_pianoVBulk</code> stampa.
 *
 *	@return <code>it.cnr.jada.action.Forward</code>

 */
public it.cnr.jada.action.Forward doBlankSearchFindUOForPrint(it.cnr.jada.action.ActionContext context, it.cnr.contab.docamm00.docs.bulk.Stampa_doc_gen_per_vpVBulk stampa) {

	//stampa.setVoce_del_piano("");
	stampa.setVocedpForPrint(new Elemento_voceBulk());
	stampa.setUoForPrint(new Unita_organizzativaBulk());

	
	return context.findDefaultForward();
}
/**
 * Nuova ricerca di una voce del piano
 *	Quando l'utente azzera il search_tool relativo alla voce del piano, all'UO, il sistema
 *	ripulisce anche il campo relativo alla voce del piano.
 *
 *	@param context, l'<code>ActionContext</code> che ha generato la richiesta.
 *	@param stampa, la <code>Stampa_docamm_per_voce_del_pianoVBulk</code> stampa.
 *
 *	@return <code>it.cnr.jada.action.Forward</code>

 */
public it.cnr.jada.action.Forward doBlankSearchFindUOForPrint(it.cnr.jada.action.ActionContext context, it.cnr.contab.docamm00.docs.bulk.Stampa_fat_pas_per_vpVBulk stampa) {

	//stampa.setVoce_del_piano("");
	stampa.setVocedpForPrint(new Elemento_voceBulk());
	stampa.setUoForPrint(new Unita_organizzativaBulk());

	
	return context.findDefaultForward();
}
}
