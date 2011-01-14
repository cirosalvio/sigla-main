package it.cnr.contab.pdg00.bp;

import java.rmi.RemoteException;

import it.cnr.contab.config00.bulk.Parametri_cnrBulk;
import it.cnr.contab.config00.ejb.Parametri_cnrComponentSession;
import it.cnr.contab.config00.sto.bulk.DipartimentoBulk;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
import it.cnr.contab.doccont00.bp.CRUDObbligazioneBP;
import it.cnr.contab.pdg00.bulk.ArchiviaStampaPdgVariazioneBulk;
import it.cnr.contab.pdg00.bulk.Pdg_variazioneBulk;
import it.cnr.contab.pdg00.bulk.Pdg_variazione_archivioBulk;
import it.cnr.contab.pdg00.bulk.V_pdg_variazione_riepilogoBulk;
import it.cnr.contab.pdg00.cdip.bulk.Ass_pdg_variazione_cdrBulk;
import it.cnr.contab.pdg00.ejb.PdGVariazioniComponentSession;
import it.cnr.contab.pdg01.bp.CRUDPdgVariazioneGestionaleBP;
import it.cnr.contab.utenze00.bp.CNRUserContext;
import it.cnr.contab.utenze00.bulk.CNRUserInfo;
import it.cnr.contab.utenze00.bulk.UtenteBulk;
import it.cnr.contab.varstanz00.bulk.Var_stanz_resBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.bulk.ValidationException;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.util.RemoteIterator;
import it.cnr.jada.util.action.*;
import it.cnr.jada.util.ejb.EJBCommonServices;

/**
 * Business Process per la gestione della testata delle variazioni al PDG
 */

public class PdGVariazioneBP extends it.cnr.jada.util.action.SimpleCRUDBP {
	
	private SimpleDetailCRUDController riepilogoEntrate = new SimpleDetailCRUDController( "RiepilogoEntrate", V_pdg_variazione_riepilogoBulk.class, "riepilogoEntrate", this) {
	};
	
	private SimpleDetailCRUDController riepilogoSpese = new SimpleDetailCRUDController( "RiepilogoSpese", V_pdg_variazione_riepilogoBulk.class, "riepilogoSpese", this) {
	};

	private SimpleDetailCRUDController crudAssCDR = new SimpleDetailCRUDController( "AssociazioneCDR", Ass_pdg_variazione_cdrBulk.class, "associazioneCDR", this) {
		public void validateForDelete(ActionContext context, OggettoBulk detail) throws ValidationException {
			if (!detail.isToBeCreated())
				validaAssociazioneCDRPerCancellazione(context, (Ass_pdg_variazione_cdrBulk)detail);
   		}
		public void add(ActionContext actioncontext) throws BusinessProcessException {
			if (getParentController() instanceof CRUDPdgVariazioneGestionaleBP) {
				if (((Pdg_variazioneBulk)getParentModel()).getTipologia()==null)
					((SimpleCRUDBP)getParentController()).setMessage("Occorre valorizzare la tipologia della variazione prima di associare i CDR");
				else if (((Pdg_variazioneBulk)getParentModel()).getTipologia_fin()==null)
					((SimpleCRUDBP)getParentController()).setMessage("Occorre valorizzare l'origine delle fonti della variazione prima di associare i CDR");
				else
					super.add(actioncontext);
			}
			else
				super.add(actioncontext);
		}
		/**
		 * Metodo per aggiungere alla toolbar del Controller un tasto necessario per apporre 
		 * il visto da parte del dipartimento.
		 * @param context Il contesto dell'azione
		 */
		public void writeHTMLToolbar(
				javax.servlet.jsp.PageContext context,
				boolean reset,
				boolean find,
				boolean delete) throws java.io.IOException, javax.servlet.ServletException {

				super.writeHTMLToolbar(context, reset, find, delete);

				if (getParentController() != null && 
				    ((Pdg_variazioneBulk)getParentController().getModel()).isApprovata() &&
				    getDipartimentoSrivania()!=null && 
				    getDipartimentoSrivania().getCd_dipartimento()!=null ) {
					String command = "javascript:submitForm('doApponiVistoDipartimento')";
					it.cnr.jada.util.jsp.JSPUtils.toolbarButton(
						context,
						"img/properties16.gif",
						!(getDetails().isEmpty() || ((CRUDBP)getParentController()).isSearching())? command : null,
						true,"Apponi Visto");
				}
		}
	};
	
	private SimpleDetailCRUDController crudArchivioCons = new SimpleDetailCRUDController( "ArchivioConsultazioni", Pdg_variazione_archivioBulk.class, "archivioConsultazioni", this);
	private it.cnr.contab.config00.sto.bulk.CdrBulk centro_responsabilita_scrivania;
	private it.cnr.contab.config00.sto.bulk.CdsBulk centro_di_spesa_scrivania;	
	private Unita_organizzativaBulk uoSrivania;
	private DipartimentoBulk dipartimentoSrivania;	
	private Parametri_cnrBulk parametriCnr;
		
	public PdGVariazioneBP() {
		super();
	}

	public PdGVariazioneBP(String function) {
		super(function);
	}
	
	protected void resetTabs(it.cnr.jada.action.ActionContext context) {
		setTab("tab","tabTestata");
	}
	/**
	 * Crea la ProcedureComponentSession da usare per effettuare operazioni
	 */
	public it.cnr.contab.util00.ejb.ProcedureComponentSession createProcedureComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException {
		return (it.cnr.contab.util00.ejb.ProcedureComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRUTIL00_EJB_ProcedureComponentSession", it.cnr.contab.util00.ejb.ProcedureComponentSession.class);
	}
	/**
	 * Crea la PdGComponentSession da usare per effettuare operazioni
	 */
	public it.cnr.contab.pdg00.ejb.PdGPreventivoComponentSession createPdGPreventivoComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException {
		return (it.cnr.contab.pdg00.ejb.PdGPreventivoComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRPDG00_EJB_PdGPreventivoComponentSession", it.cnr.contab.pdg00.ejb.PdGPreventivoComponentSession.class);
	}
	/**
	 * Crea la CdrComponentSession da usare per effettuare operazioni
	 */
	public it.cnr.contab.config00.ejb.CDRComponentSession createCdrComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException {
		return (it.cnr.contab.config00.ejb.CDRComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_CDRComponentSession", it.cnr.contab.config00.ejb.CDRComponentSession.class);
	}
	/**
	 * Crea la ParametriCnrComponentSession da usare per effettuare operazioni
	 */
	public Parametri_cnrComponentSession createParametriCnrComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException {
		return (Parametri_cnrComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_Parametri_cnrComponentSession", Parametri_cnrComponentSession.class);
	}

	protected void initialize(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		super.initialize(context);
		try {
			setParametriCnr(createParametriCnrComponentSession().getParametriCnr(context.getUserContext(), CNRUserContext.getEsercizio(context.getUserContext())));
			setCentro_responsabilita_scrivania(createCdrComponentSession().cdrFromUserContext(context.getUserContext()));
			setAbilitatoModificaDescVariazioni(UtenteBulk.isAbilitatoModificaDescVariazioni(context.getUserContext()));
			((Pdg_variazioneBulk)getModel()).setCentro_responsabilita(getCentro_responsabilita_scrivania());
			setUoSrivania(it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa(context));
			if (it.cnr.contab.utenze00.bulk.CNRUserInfo.getDipartimento(context)!=null)
				setDipartimentoSrivania(it.cnr.contab.utenze00.bulk.CNRUserInfo.getDipartimento(context));
			validaAccessoBP(context);
		} catch (ComponentException e) {
			throw handleException(e);
		} catch (RemoteException e) {
			throw handleException(e);
		}
	}			
	
	protected void validaAccessoBP(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.comp.ApplicationException, it.cnr.jada.action.BusinessProcessException {
		try	{
			if (getParametriCnr().getFl_regolamento_2006())
				throw new it.cnr.jada.comp.ApplicationException("Utilizzo non consentito nel "+CNRUserContext.getEsercizio(context.getUserContext()));
	
			if(!isUoEnte()) {
				PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
				comp.controllaBilancioPreventivoCdsApprovato(context.getUserContext(), ((CNRUserInfo)context.getUserInfo()).getCdr());
			}
		}
		catch(Throwable e){
			throw handleException(e);
		}		
	}

	/**
	 * @return
	 */
	public SimpleDetailCRUDController getCrudAssCDR() {
		return crudAssCDR;
	}

	/**
	 * @param controller
	 */
	public void setCrudAssCDR(SimpleDetailCRUDController controller) {
		crudAssCDR = controller;
	}

	/**
	 * @return
	 */
	public SimpleDetailCRUDController getCrudArchivioCons() {
		return crudArchivioCons;
	}

	/**
	 * @param controller
	 */
	public void setCrudArchivioCons(SimpleDetailCRUDController controller) {
		crudArchivioCons = controller;
	}
	/**
	 * @return
	 */
	public it
		.cnr
		.contab
		.config00
		.sto
		.bulk
		.CdrBulk getCentro_responsabilita_scrivania() {
		return centro_responsabilita_scrivania;
	}

	/**
	 * @param bulk
	 */
	public void setCentro_responsabilita_scrivania(
		it.cnr.contab.config00.sto.bulk.CdrBulk bulk) {
		centro_responsabilita_scrivania = bulk;
	}
	public boolean isButtonDettagliEnabled() {
		try{
			Pdg_variazioneBulk pdg_variazione = (Pdg_variazioneBulk)getModel();
			Ass_pdg_variazione_cdrBulk ass_pdg_variazione = (Ass_pdg_variazione_cdrBulk)(pdg_variazione.getAssociazioneCDR().get(getCrudAssCDR().getSelection().getFocus()));
			if(ass_pdg_variazione.getCentro_responsabilita().equalsByPrimaryKey(getCentro_responsabilita_scrivania()))
			  return true;
			return false;
		}catch(NullPointerException ex){
			return false;
		}catch(java.lang.ArrayIndexOutOfBoundsException ex){
			return false;
		}
		
	}	
	/**
	 * Verifica che il CDR della variazione PDG sia uguale al CDR di scrivania 
	 */
	public boolean isCdrScrivania() {
	    if (getStatus() == SEARCH)
	      return true;
		try{
			Pdg_variazioneBulk pdg_variazione = (Pdg_variazioneBulk)getModel();
			if(pdg_variazione.getCentro_responsabilita().equalsByPrimaryKey(getCentro_responsabilita_scrivania()))
			  return true;
			return false;
		}catch(NullPointerException ex){
			return false;
		}catch(java.lang.ArrayIndexOutOfBoundsException ex){
			return false;
		}
	}	
	public boolean isAnnullabile() {
		if (getStatus() == SEARCH)
		  return true;
		return isApprovaButtonEnabled();
	}
	public boolean isSaveButtonEnabled()
	{
		if (!isAbilitatoModificaDescVariazioni() && ((Pdg_variazioneBulk)getModel()).isApprovata())
			return false;
		else
		return super.isSaveButtonEnabled() && (isCdrScrivania() || isUoEnte());
	}	
	public boolean isDeleteButtonEnabled()
	{
		return super.isDeleteButtonEnabled() && (isCdrScrivania() || isUoEnte()) && !((Pdg_variazioneBulk)getModel()).isApprovata();
	}
	/**
	 * Metodo utilizzato per creare una toolbar applicativa personalizzata.
	 * @return toolbar Toolbar in uso
	 */
	protected it.cnr.jada.util.jsp.Button[] createToolbar() {

		it.cnr.jada.util.jsp.Button[] toolbar = new it.cnr.jada.util.jsp.Button[14];
		int i = 0;
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.search");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.startSearch");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.freeSearch");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.new");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.save");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.delete");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.bringBack");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.print");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.undoBringBack");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.definitiveSave");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.approva");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.nonApprova");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.apponiVistoDipartimento");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.approvazioneFormale");
		return toolbar;
	}
	/**
	 * Restituisce il valore della propriet� 'salvaDefinitivoButtonEnabled'
	 * Il bottone di SalvaDefinitivo � disponibile solo se:
	 * - la proposta � provvisoria
	 * - il CDR � di 1� Livello
	 *
	 * @return Il valore della propriet� 'salvaDefinitivoButtonEnabled'
	 */
	public boolean isSalvaDefinitivoButtonEnabled() {
		return (isSaveButtonEnabled()||(super.isSaveButtonEnabled()&&((Pdg_variazioneBulk)getModel()).isPropostaProvvisoria()))&& 
		        ((Pdg_variazioneBulk)getModel()).isPropostaProvvisoria() && 
		        ((Pdg_variazioneBulk)getModel()).isNotNew() &&
	 		    (getCentro_responsabilita_scrivania().getLivello().intValue() == 1 || isUoArea())&&
		        ((Pdg_variazioneBulk)getModel()).getCentro_responsabilita().getCd_cds().equals(getCentro_responsabilita_scrivania().getCd_cds());
	}
	/**
	 * Restituisce il valore della propriet� 'approvaButtonEnabled'
	 * Il bottone di Approva � disponibile solo se:
	 * - � attivo il bottone di salvataggio
	 * - la proposta di variazione PDG � definitiva
	 * - la UO che sta effettuando l'operazione � di tipo ENTE
	 *
	 * @return Il valore della propriet� 'approvaButtonEnabled'
	 */
	public boolean isApprovaButtonEnabled() {

		return super.isSaveButtonEnabled() && ((Pdg_variazioneBulk)getModel()).isPropostaDefinitiva() && 
		   (isUoEnte()||
		    ((getCentro_responsabilita_scrivania().getLivello().intValue() == 1 || isUoArea())&&
		    ((Pdg_variazioneBulk)getModel()).getCentro_responsabilita().getCd_cds().equals(getCentro_responsabilita_scrivania().getCd_cds())&&
		    ((Pdg_variazioneBulk)getModel()).isCdsAbilitatoAdApprovare()));
	}
	/**
	 * Restituisce il valore della propriet� 'nonApprovaButtonEnabled'
	 * Il bottone di NonApprova � disponibile solo se:
	 * - � attivo il bottone di salvataggio
	 * - la proposta di variazione PDG � definitiva
	 * - la UO che sta effettuando l'operazione � di tipo ENTE
	 *
	 * @return Il valore della propriet� 'nonApprovaButtonEnabled'
	 */
	public boolean isNonApprovaButtonEnabled() {

		return isSaveButtonEnabled() && ((Pdg_variazioneBulk)getModel()).isPropostaDefinitiva() && isUoEnte();
	}
	/**
	 * Gestione del salvataggio come definitiva di una variazione
	 *
	 * @param context	L'ActionContext della richiesta
	 * @throws BusinessProcessException	
	 */
	public void salvaDefinitivo(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {

			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			Pdg_variazioneBulk pdg = comp.salvaDefinitivo(context.getUserContext(), (Pdg_variazioneBulk)getModel());
			edit(context,pdg);
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}
	/**
	 * Gestione del salvataggio come approvata di una variazione
	 *
	 * @param context	L'ActionContext della richiesta
	 * @throws BusinessProcessException	
	 */
	public void approva(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {

			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			Pdg_variazioneBulk pdg = comp.approva(context.getUserContext(), (Pdg_variazioneBulk)getModel());
			edit(context,pdg);
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}
	/**
	 * Gestione del salvataggio come respinta di una variazione
	 *
	 * @param context	L'ActionContext della richiesta
	 * @throws BusinessProcessException	
	 */
	public void respingi(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {

			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			Pdg_variazioneBulk pdg = comp.respingi(context.getUserContext(), (Pdg_variazioneBulk)getModel());
			edit(context,pdg);
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}	
	public void inizializzaSommeCdR(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {

			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			comp.inizializzaSommeCdR(context.getUserContext(), (Pdg_variazioneBulk)getModel());
			edit(context,getModel());			
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}		
	public void basicEdit(it.cnr.jada.action.ActionContext context,it.cnr.jada.bulk.OggettoBulk bulk, boolean doInitializeForEdit) throws it.cnr.jada.action.BusinessProcessException {
		aggiornaVariazioneDocumentale(context,(Pdg_variazioneBulk) bulk);
		super.basicEdit(context, bulk, doInitializeForEdit);
 
		if (getStatus()!=VIEW){
			Pdg_variazioneBulk pdg = (Pdg_variazioneBulk)getModel();
			if (pdg!=null && pdg.isCancellatoLogicamente() 
//					|| pdg.isApprovata() 
					|| pdg.isRespinta()|| pdg.isApprovazioneFormale()) {
				setStatus(VIEW);
			}			
		}
	}		
	/**
	 * @return
	 */
	public Unita_organizzativaBulk getUoSrivania() {
		return uoSrivania;
	}

	/**
	 * @param bulk
	 */
	public void setUoSrivania(Unita_organizzativaBulk bulk) {
		uoSrivania = bulk;
	}
    public boolean isUoEnte(){
    	return (getUoSrivania().getCd_tipo_unita().compareTo(it.cnr.contab.config00.sto.bulk.Tipo_unita_organizzativaHome.TIPO_UO_ENTE)==0);
    }
	public boolean isUoArea(){
		return (getUoSrivania().getCd_tipo_unita().compareTo(it.cnr.contab.config00.sto.bulk.Tipo_unita_organizzativaHome.TIPO_UO_AREA)==0);
	}
	/**
	 * @return
	 */
	public it
		.cnr
		.contab
		.config00
		.sto
		.bulk
		.CdsBulk getCentro_di_spesa_scrivania() {
		return centro_di_spesa_scrivania;
	}

	/**
	 * @param bulk
	 */
	public void setCentro_di_spesa_scrivania(
		it.cnr.contab.config00.sto.bulk.CdsBulk bulk) {
		centro_di_spesa_scrivania = bulk;
	}

	public void validaAssociazioneCDRPerCancellazione(ActionContext context, Ass_pdg_variazione_cdrBulk assBulk) throws ValidationException {
		try {
			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			comp.validaAssociazioneCDRPerCancellazione(context.getUserContext(), assBulk);
		} catch (Throwable e) {
			throw new ValidationException(e.getMessage());
		}
	}
	public String controllaTotPropostoEntrataSpesa(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {

			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			return comp.controllaTotPropostoEntrataSpesa(context.getUserContext(), (Pdg_variazioneBulk)getModel());
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}
	public boolean isAfButtonHidden()
	{
		return !isUoEnte()||
		       (getDipartimentoSrivania()!=null && 
		        getDipartimentoSrivania().getCd_dipartimento()!=null);
	}
	
	public boolean isAfButtonEnabled()
	{
		return true;
	}

	public boolean isAvdButtonHidden()
	{
		return !isUoEnte() ||
		       (getDipartimentoSrivania()==null ||
		        getDipartimentoSrivania().getCd_dipartimento()==null);
	}
	
	public boolean isAvdButtonEnabled()
	{
		return true;
	}

	/**
	 * @return
	 */
	public Parametri_cnrBulk getParametriCnr() {
		return parametriCnr;
	}

	/**
	 * @param bulk
	 */
	public void setParametriCnr(Parametri_cnrBulk bulk) {
		parametriCnr = bulk;
	}

	public void statoPrecedente(ActionContext context) throws it.cnr.jada.action.BusinessProcessException{
		try {
			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			edit(context,comp.statoPrecedente(context.getUserContext(), getModel()));
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}		
	public boolean isStatoPrecedenteButtonEnabled() {
		try{
			return (isSaveButtonEnabled()||(((Pdg_variazioneBulk)getModel()).isPropostaDefinitiva()))&& 
					((Pdg_variazioneBulk)getModel()).isPropostaDefinitiva() && 
					((Pdg_variazioneBulk)getModel()).isNotNew() &&
					(getCentro_responsabilita_scrivania().getLivello().intValue() == 1 || isUoArea())&&
					((Pdg_variazioneBulk)getModel()).getCentro_responsabilita().getCd_cds().equals(getCentro_responsabilita_scrivania().getCd_cds());
		}catch(NullPointerException e){
			return false;
		}
	}
	public boolean isAssestatoResiduoButtonHidden() {
		return true;
	}
	public boolean isEditableDettagliVariazione() {
		try {
			Pdg_variazioneBulk pdg_variazione = (Pdg_variazioneBulk)getModel();
			if (pdg_variazione!=null && getCrudAssCDR().getSelection().getFocus()==-1) return false;
			Ass_pdg_variazione_cdrBulk ass_pdg_variazione = (Ass_pdg_variazione_cdrBulk)(pdg_variazione.getAssociazioneCDR().get(getCrudAssCDR().getSelection().getFocus()));
			return ass_pdg_variazione.getCentro_responsabilita().equalsByPrimaryKey(getCentro_responsabilita_scrivania()) &&
				   pdg_variazione.isPropostaProvvisoria();
		}
		catch (NullPointerException e){
			return false;
		}
	}
    /*
	 * Serve per sapere se la variazione movimenta un Fondo di Spesa
	 */
    public boolean isMovimentoSuFondi(){
	  return getModel()!=null &&
	  	     ((Pdg_variazioneBulk)getModel()).getTipo_variazione()!=null&&
			 ((Pdg_variazioneBulk)getModel()).getTipo_variazione().isMovimentoSuFondi();
    }
    
	public void apponiVistoDipartimento(ActionContext context, Pdg_variazioneBulk pdg) throws it.cnr.jada.action.BusinessProcessException{
		try {
			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			pdg = (Pdg_variazioneBulk)comp.apponiVistoDipartimento(context.getUserContext(), pdg, CNRUserInfo.getDipartimento(context));
			edit(context,pdg);
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}

	public void apponiVistoDipartimento(ActionContext context, Ass_pdg_variazione_cdrBulk ass) throws it.cnr.jada.action.BusinessProcessException{
		try {
			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			ass = (Ass_pdg_variazione_cdrBulk)comp.apponiVistoDipartimento(context.getUserContext(), ass, CNRUserInfo.getDipartimento(context));
		}catch(it.cnr.jada.comp.ComponentException ex){
			throw handleException(ex);
		}catch(java.rmi.RemoteException ex){
			throw handleException(ex);
		}
	}

	public DipartimentoBulk getDipartimentoSrivania() {
		return dipartimentoSrivania;
	}

	private void setDipartimentoSrivania(
			DipartimentoBulk dipartimentoSrivania) {
		this.dipartimentoSrivania = dipartimentoSrivania;
	}		

	public RemoteIterator findVariazioniForApposizioneVisto(ActionContext actioncontext, CompoundFindClause compoundfindclause, OggettoBulk oggettobulk) throws BusinessProcessException {
		try	{
			return EJBCommonServices.openRemoteIterator(actioncontext, ((PdGVariazioniComponentSession)createComponentSession()).cercaVariazioniForApposizioneVisto(actioncontext.getUserContext(), compoundfindclause, oggettobulk));
		}catch(Exception exception)	{
			throw handleException(exception);
		}
	}

	public SimpleDetailCRUDController getRiepilogoEntrate() {
		return riepilogoEntrate;
	}

	public void setRiepilogoEntrate(SimpleDetailCRUDController riepilogoEntrate) {
		this.riepilogoEntrate = riepilogoEntrate;
	}

	public SimpleDetailCRUDController getRiepilogoSpese() {
		return riepilogoSpese;
	}

	public void setRiepilogoSpese(SimpleDetailCRUDController riepilogoSpese) {
		this.riepilogoSpese = riepilogoSpese;
	}
	
	private boolean abilitatoModificaDescVariazioni;
	
	public boolean isAbilitatoModificaDescVariazioni() {
		Pdg_variazioneBulk pdg = (Pdg_variazioneBulk)getModel();
		if(pdg!=null && pdg.getStatoDocumentale()!=null && pdg.getStatoDocumentale().compareTo(ArchiviaStampaPdgVariazioneBulk.VIEW_SIGNED)==0)
			return false;
		else
			return abilitatoModificaDescVariazioni;
	}
	public void setAbilitatoModificaDescVariazioni(boolean abilitatoModificaDescVariazioni) {
		this.abilitatoModificaDescVariazioni = abilitatoModificaDescVariazioni;
	}
	public void aggiornaVariazioneDocumentale(ActionContext context, Pdg_variazioneBulk bulk) throws BusinessProcessException{
		try {
			PdGVariazioniComponentSession comp = (PdGVariazioniComponentSession)createComponentSession();
			comp.archiviaVariazioneDocumentale(context.getUserContext(), bulk);
		} catch (Throwable e) {
			throw new BusinessProcessException(e.getMessage());
		}
	}
}