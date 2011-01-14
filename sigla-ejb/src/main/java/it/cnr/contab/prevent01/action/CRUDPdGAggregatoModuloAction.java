/*
 * Created on Sep 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.prevent01.action;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import it.cnr.contab.config00.sto.bulk.CdrBulk;
import it.cnr.contab.prevent01.bp.CRUDPdGAggregatoModuloBP;
import it.cnr.contab.prevent01.bp.CRUDStatoCdrPdGPBP;
import it.cnr.contab.prevent01.bulk.Pdg_moduloBulk;
import it.cnr.contab.prevent01.ejb.PdgAggregatoModuloComponentSession;
import it.cnr.contab.progettiric00.bp.ProgettoAlberoLABP;
import it.cnr.contab.progettiric00.bp.TestataProgettiRicercaBP;
import it.cnr.contab.progettiric00.core.bulk.Progetto_sipBulk;
import it.cnr.contab.utenze00.bp.CNRUserContext;
import it.cnr.contab.utenze00.bulk.UtenteBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcess;
import it.cnr.jada.action.Forward;
import it.cnr.jada.action.HookForward;
import it.cnr.jada.bulk.BulkInfo;
import it.cnr.jada.bulk.BulkList;
import it.cnr.jada.bulk.FieldProperty;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;
import it.cnr.jada.util.action.BulkBP;
import it.cnr.jada.util.action.CRUDAction;
import it.cnr.jada.util.action.CRUDBP;
import it.cnr.jada.util.action.ConsultazioniBP;
import it.cnr.jada.util.action.OptionBP;
import it.cnr.jada.util.action.Selection;
import it.cnr.jada.util.action.SelezionatoreListaBP;
import it.cnr.jada.util.action.SimpleDetailCRUDController;

/**
 * @author mincarnato
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CRUDPdGAggregatoModuloAction extends CRUDAction  {

	public Forward doFreeSearchSearchtool_progetto(ActionContext context) {
		try{
			Progetto_sipBulk progetto = new Progetto_sipBulk();
			progetto.setProgettopadre(new Progetto_sipBulk());	
			return freeSearch(context, getFormField(context, "main.Dettagli.searchtool_progetto"), progetto);
		} catch(Throwable e){
			return handleException(context, e);
		}		
	}

	public it.cnr.jada.action.Forward doSearchSearchtool_progetto(ActionContext context) {

		try{
			
			BulkBP bpmod = (BulkBP)context.getBusinessProcess();
			Pdg_moduloBulk dettaglio = (Pdg_moduloBulk) ((CRUDPdGAggregatoModuloBP)bpmod).getCrudDettagli().getModel();

			String cd = null;

			if (dettaglio != null)
				cd = dettaglio.getCd_progetto();
			if (cd != null){
				// L'utente ha indicato un codice da cercare: esegue una ricerca mirata.
				return search(context, getFormField(context, "main.Dettagli.searchtool_progetto"),null);
			}

			TestataProgettiRicercaBP bp = (TestataProgettiRicercaBP)context.createBusinessProcess("TestataProgettiRicercaBP");
			context.addBusinessProcess(bp);
		
			it.cnr.jada.util.RemoteIterator roots = bp.getProgetti_sipTree(context).getChildren(context,null);
			// Non ci sono Commesse disponibili
			if (roots.countElements()==0){
				context.closeBusinessProcess();
				it.cnr.jada.util.ejb.EJBCommonServices.closeRemoteIterator(roots);
				setErrorMessage(context,"Attenzione: non sono state trovati progetti disponibili");
				return context.findDefaultForward();
			}else {
				context.closeBusinessProcess();
				// Apre un Selezionatore ad Albero per cercare i Progetti selezionando i vari livelli
				ProgettoAlberoLABP slaBP = (ProgettoAlberoLABP)context.createBusinessProcess("ProgettoAlberoLABP");
				slaBP.setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(Progetto_sipBulk.class));
				slaBP.setRemoteBulkTree(context,bp.getProgetti_sipTree(context),roots);
                slaBP.setColumns( slaBP.getBulkInfo().getColumnFieldPropertyDictionary("progetti_sip"));
				HookForward hook = (HookForward)context.addHookForward("seleziona",this,"doBringBackSearchResult");
				hook.addParameter("field",getFormField(context,"main.Dettagli.searchtool_progetto"));
				context.addBusinessProcess(slaBP);
				return slaBP;
			}
		} catch(Throwable e){
			return handleException(context, e);
		}
	}

	public it.cnr.jada.action.Forward doBringBackSearchSearchtool_progetto(ActionContext context, Pdg_moduloBulk linea, Progetto_sipBulk progetto) throws java.rmi.RemoteException {

		// valore di default nel caso non fose valorizzato
		String columnDescription="Codice Modulo di Attivit�";
		// nome del campo nel file xml
		final String propName="cd_progetto";
		FieldProperty property = BulkInfo.getBulkInfo(linea.getClass()).getFieldProperty(propName);
		if (property != null)
			columnDescription = property.getLabel();


		if (progetto!=null) {
			if (progetto.getLivello()==null || !progetto.getLivello().equals(new Integer("3"))) {
				setErrorMessage(context,"Attenzione: il valore immesso in "+columnDescription+" non � valido!");
				return context.findDefaultForward();
			}
		}
		linea.setProgetto(progetto);
		return context.findDefaultForward();
	}

	public Forward doFilterCRUDMain_Dettagli(ActionContext context) {
		return context.findDefaultForward();
	}

	public it.cnr.jada.action.Forward doCambiaStato(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {

		try {
			fillModel(context);
		} catch(Throwable e) {
			return handleException(context,e);
		}
		String message = "Lo stato del Piano di Gestione Preliminare per il modulo di attivit� verr� cambiato.\n"
						+ "Vuoi continuare?";
		return openConfirm(context, message, it.cnr.jada.util.action.OptionBP.CONFIRM_YES_NO, "doCambiaStatoConfermato");
	}
	
	public Forward doCambiaStatoConfermato(ActionContext context, int opt) throws it.cnr.jada.action.BusinessProcessException {

		if (opt == OptionBP.YES_BUTTON) {
			try {
				CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)context.getBusinessProcess();

				fillModel(context);

				// controlliamo che gli stati delle righe selezionate siano tra loro congruenti
				List listaSel = bp.getCrudDettagli().getSelectedModels(context);
				String oldStato = ((Pdg_moduloBulk)(bp.getCrudDettagli().getModel())).getStato();
				String newStato = ((Pdg_moduloBulk)(bp.getCrudDettagli().getModel())).getCambia_stato();
				if (!listaSel.isEmpty()) {
					for (Iterator it=listaSel.iterator();it.hasNext();) {
						Pdg_moduloBulk mod = (Pdg_moduloBulk) it.next();
						if (!mod.getStato().equals(oldStato)) {
							setErrorMessage(context,"Attenzione: le righe selezionate devono avere lo stesso stato attuale!");
							return context.findDefaultForward();
						}
					}

					Selection sel = bp.getCrudDettagli().getSelection();
					for (Iterator it=sel.iterator();it.hasNext();) {
						Integer iSel=(Integer)it.next();
						Pdg_moduloBulk mod = (Pdg_moduloBulk) bp.getCrudDettagli().getDetails().get(iSel.intValue());
						mod.setCambia_stato(newStato);
						try {
							((PdgAggregatoModuloComponentSession)bp.createComponentSession()).modificaStatoPdg_aggregato(context.getUserContext(),mod);
							sel.removeFromSelection(iSel);
							bp.getCrudDettagli().setSelection(context, sel);	
						} catch(Throwable e) {
							setErrorMessage(context,"Modulo di attivit� "+mod.getCd_progetto()+". "+e.getMessage());
							return context.findDefaultForward();
						}
					}
				}
				else {
					Pdg_moduloBulk pdg_mod = (Pdg_moduloBulk) ((PdgAggregatoModuloComponentSession)bp.createComponentSession()).modificaStatoPdg_aggregato(context.getUserContext(),(Pdg_moduloBulk)bp.getCrudDettagli().getModel());
					bp.cerca(context);
				}
				return context.findDefaultForward();
			} catch(Throwable e) {
				return handleException(context,e);
			}
		}
		return context.findDefaultForward();
	}

	public it.cnr.jada.action.Forward doContrattazioneEntrate(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)context.getBusinessProcess();
		Pdg_moduloBulk pdg_mod = (Pdg_moduloBulk)bp.getCrudDettagli().getModel();
		try {
			fillModel(context);
			if (bp.isDirty()) {
				setErrorMessage(context,"Attenzione: � necessario salvare le modifiche effettuate!");
				return context.findDefaultForward();
			}
			BulkBP nbp = (BulkBP)context.getUserInfo().createBusinessProcess(
							context,
							"CRUDPdg_Modulo_EntrateBP",
							new Object[] {
								bp.isEditable() && !bp.isROModuloEntrate() ? "M" : "V",
								pdg_mod.getEsercizio(),
								pdg_mod.getCdr(),
								pdg_mod.getProgetto()
							}
						);
			return context.addBusinessProcess(nbp);
		} catch(Throwable e) {
			return handleException(context,e);
		}
	}

	public it.cnr.jada.action.Forward doContrattazioneSpese(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)context.getBusinessProcess();
		Pdg_moduloBulk pdg_mod = (Pdg_moduloBulk)bp.getCrudDettagli().getModel();
		try {
			fillModel(context);
			if (bp.isDirty()) {
				setErrorMessage(context,"Attenzione: � necessario salvare le modifiche effettuate!");
				return context.findDefaultForward();
			}
			BulkBP nbp = (BulkBP)context.getUserInfo().createBusinessProcess(
							context,
							"CRUDDettagliModuloCostiBP",
							new Object[] {
								bp.isEditable() && !bp.isROContrattazioni() ? "M" : "V",
								pdg_mod
							}
						);
			return context.addBusinessProcess(nbp);
		} catch(Throwable e) {
			return handleException(context,e);
		}
	}
	
	public Forward doInserisciModuli(ActionContext context) {
		try {
			CRUDBP bp = getBusinessProcess(context);
			if (bp.getStatus() == bp.INSERT || bp.getStatus() == bp.EDIT) {
				Pdg_moduloBulk pdg_modulo = new Pdg_moduloBulk();
				CompoundFindClause compoundfindclause = new CompoundFindClause();
				
				CdrBulk cdr = (CdrBulk) bp.getModel();
				BulkList dettagli = cdr.getDettagli();
	
				Iterator itr = dettagli.iterator();
				while(itr.hasNext()) {
					compoundfindclause.addClause("AND","pg_progetto",SQLBuilder.NOT_EQUALS,((Pdg_moduloBulk)itr.next()).getPg_progetto());
				}
			
				Progetto_sipBulk progetto = new Progetto_sipBulk();
				it.cnr.jada.util.RemoteIterator ri = bp.find(context, compoundfindclause, progetto, pdg_modulo, "progetto");
				if (ri == null || ri.countElements() == 0) {
					it.cnr.jada.util.ejb.EJBCommonServices.closeRemoteIterator(ri);
					bp.setMessage("La ricerca non ha fornito alcun risultato.");
					return context.findDefaultForward();
				} else {
					SelezionatoreListaBP nbp = (SelezionatoreListaBP)context.createBusinessProcess("Selezionatore");
					nbp.setIterator(context,ri);
					nbp.setMultiSelection(true);
					nbp.setBulkInfo(progetto.getBulkInfo());
					nbp.setColumns( nbp.getBulkInfo().getColumnFieldPropertyDictionary("moduli_sip"));
					context.addHookForward("seleziona",this,"doRiportaSelezioneModuli");
					return context.addBusinessProcess(nbp);
				}
			}
		} catch(Throwable e) {
			return handleException(context,e);
		} 
		return context.findDefaultForward();
	}

	public Forward doRiportaSelezioneModuli(ActionContext context)  throws java.rmi.RemoteException {

		try {
			HookForward caller = (HookForward)context.getCaller();
			CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
			CdrBulk cdr = (CdrBulk) bp.getModel();
			SimpleDetailCRUDController controller = bp.getCrudDettagli();

			java.util.List l = (java.util.List)caller.getParameter("selectedElements");
			if (l!=null && !l.isEmpty()){
				Iterator it = l.iterator();
				while(it.hasNext()) {
					Pdg_moduloBulk mod = new Pdg_moduloBulk();
					mod.initializeForInsert(bp,context);
					mod.setCdr(cdr);
					mod.setProgetto((Progetto_sipBulk)it.next());
					if (!cdr.getDettagli().containsByPrimaryKey(mod))
						controller.add(context, mod);
				}
			}

			return context.findDefaultForward();
		} catch(Exception e) {
			return handleException(context,e);
		}
	}
	/**
	 * Gestione della richiesta di consultazione del Piano di riparto delle spese accentrate
	 *
	 * @param context	L'ActionContext della richiesta
	 * @return Il Forward alla pagina di risposta
	 */
	public Forward doConsultaPianoRiparto(ActionContext context) {
		try {
			fillModel(context);
			CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
			CdrBulk cdr = (CdrBulk)bp.getModel();

			CompoundFindClause clause = new CompoundFindClause();
			clause.addClause("AND","esercizio",SQLBuilder.EQUALS,CNRUserContext.getEsercizio(context.getUserContext()));

			if (cdr!=null && cdr.getCd_centro_responsabilita()!=null) {
				clause.addClause("AND","cd_centro_responsabilita",SQLBuilder.EQUALS,cdr.getCd_centro_responsabilita());
			}

			ConsultazioniBP ricercaLiberaBP = (ConsultazioniBP)context.createBusinessProcess("ConsPdgPianoRipartoBP");
			
			ricercaLiberaBP.addToBaseclause(clause);
			ricercaLiberaBP.openIterator(context);
			
			context.addHookForward("close",this,"doDefault");
			return context.addBusinessProcess(ricercaLiberaBP);
		}catch(Throwable ex){
			return handleException(context, ex);
		}
	}

	public Forward doScaricaCostiPersonale(ActionContext context) {
		try {
			fillModel(context);
			return openConfirm(context, "Attenzione! Confermi che tutto il personale � stato ripartito sui GAE associati ai moduli di carattere scientifico, evitando di utilizzare GAE associati ai moduli di carattere gestionale?", OptionBP.CONFIRM_YES_NO, "doConfirmScaricaCostiPersonale");
		} catch(Exception e) {
			return handleException(context,e);
		}
	}

	public Forward doConfirmScaricaCostiPersonale(ActionContext context,int option) {
		try {
			if ( option == OptionBP.YES_BUTTON) 
			{
				CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
				fillModel(context);
				CdrBulk cdr = (CdrBulk) ((PdgAggregatoModuloComponentSession)bp.createComponentSession()).scaricaDipendentiSuPdGP(context.getUserContext(),(CdrBulk)bp.getModel());
				bp.setMessage("Scarico effettuato correttamente.");
			}
			return context.findDefaultForward();
		} catch(Exception e) {
			return handleException(context,e);
		}
	}

	public Forward doAnnullaScaricaCostiPersonale(ActionContext context) {
		try {
			CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
			fillModel(context);
			CdrBulk cdr = (CdrBulk) ((PdgAggregatoModuloComponentSession)bp.createComponentSession()).annullaScaricaDipendentiSuPdGP(context.getUserContext(),(CdrBulk)bp.getModel());
			bp.setMessage("Annullamento scarico effettuato correttamente.");

			return context.findDefaultForward();
		} catch(Exception e) {
			return handleException(context,e);
		}
	}

	public Forward doRiportaSelezione(ActionContext context, OggettoBulk oggettobulk) {
			try {
				CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
	
				Forward forward = super.doRiportaSelezione(context, oggettobulk);
				if (oggettobulk!=null) {
					bp.caricaCdrPdGP(context);
					if (!bp.isCdrPdGPUtilizzabile()) {
						bp.setStatus(bp.VIEW);
						bp.setEditable(false);
						setErrorMessage(context,"Lo stato del PdGP - CDR per il CdR "+((CdrBulk)oggettobulk).getCd_centro_responsabilita()+" risulta non impostato oppure\n� chiusa la fase previsionale per l'esercizio "+CNRUserContext.getEsercizio(context.getUserContext())+". Non consentita la modifica.");
					}
				}
				return forward;
			} catch(Exception e) {
				return handleException(context,e);
			}
	}
	
	public Forward doStatoCdRPdGR(ActionContext context) {
		try 
		{
			CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)getBusinessProcess(context);
			CRUDStatoCdrPdGPBP bpDett;
	 
			if(bp.isEditable())
				bpDett = (CRUDStatoCdrPdGPBP)context.createBusinessProcess("CRUDStatoCdrPdGPBP", new Object[] { "M" });
			else
				bpDett = (CRUDStatoCdrPdGPBP)context.createBusinessProcess("CRUDStatoCdrPdGPBP", new Object[] { "V" });				
			return context.addBusinessProcess(bpDett);
		} 
		catch(Throwable e) 
		{ 
			return handleException(context,e);
		}
	}
	
	public it.cnr.jada.action.Forward doGestionaleEntrate(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)context.getBusinessProcess();
		Pdg_moduloBulk pdg_mod = (Pdg_moduloBulk)bp.getCrudDettagli().getModel();
		try {
			fillModel(context);
			if (bp.isDirty()) {
				setErrorMessage(context,"Attenzione: � necessario salvare le modifiche effettuate!");
				return context.findDefaultForward();
			}

			CompoundFindClause clause = new CompoundFindClause();
			clause.addClause("AND","esercizio",SQLBuilder.EQUALS,pdg_mod.getEsercizio());
			clause.addClause("AND","cd_centro_responsabilita",SQLBuilder.EQUALS,pdg_mod.getCd_centro_responsabilita());
			clause.addClause("AND","pg_progetto",SQLBuilder.EQUALS,pdg_mod.getPg_progetto());

			ConsultazioniBP ricercaLiberaBP = (ConsultazioniBP)context.createBusinessProcess("ConsPdgpPdggEtrBP",
					new Object[] {
						bp.isEditable() && bp.isGestionaleOperabile() ? "M" : "V",
					}
				);
			
			ricercaLiberaBP.addToBaseclause(clause);
			ricercaLiberaBP.openIterator(context);
			ricercaLiberaBP.setSearchResultColumnSet("pdgModuloEntrateGest");
			ricercaLiberaBP.setFreeSearchSet("pdgModuloEntrateGest");
			
			return context.addBusinessProcess(ricercaLiberaBP);
		} catch(Throwable e) {
			return handleException(context,e);
		}
	}
	public it.cnr.jada.action.Forward doGestionaleSpese(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		CRUDPdGAggregatoModuloBP bp = (CRUDPdGAggregatoModuloBP)context.getBusinessProcess();
		Pdg_moduloBulk pdg_mod = (Pdg_moduloBulk)bp.getCrudDettagli().getModel();
		try {
			fillModel(context);
			if (bp.isDirty()) {
				setErrorMessage(context,"Attenzione: � necessario salvare le modifiche effettuate!");
				return context.findDefaultForward();
			}

			if (pdg_mod==null) {
				setErrorMessage(context,"Attenzione: � necessario selezionare una riga valida!");
				return context.findDefaultForward();
			}

			CompoundFindClause clause = new CompoundFindClause();
			clause.addClause("AND","esercizio",SQLBuilder.EQUALS,pdg_mod.getEsercizio());
			clause.addClause("AND","cd_centro_responsabilita",SQLBuilder.EQUALS,pdg_mod.getCd_centro_responsabilita());
			clause.addClause("AND","pg_progetto",SQLBuilder.EQUALS,pdg_mod.getPg_progetto());

			ConsultazioniBP ricercaLiberaBP = (ConsultazioniBP)context.createBusinessProcess("ConsPdgpPdggSpeBP",
					new Object[] {
						bp.isEditable() && bp.isGestionaleOperabile() ? "M" : "V",
					}
				);
			
			ricercaLiberaBP.addToBaseclause(clause);
			ricercaLiberaBP.openIterator(context);
			ricercaLiberaBP.setSearchResultColumnSet("pdgModuloSpeseGest");
			ricercaLiberaBP.setFreeSearchSet("pdgModuloSpeseGest");
			
			return context.addBusinessProcess(ricercaLiberaBP);
		} catch(Throwable e) {
			return handleException(context,e);
		}
	}
}
