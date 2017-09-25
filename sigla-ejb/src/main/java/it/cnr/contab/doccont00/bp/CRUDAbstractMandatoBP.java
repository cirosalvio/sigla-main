package it.cnr.contab.doccont00.bp;

import it.cnr.contab.doccont00.core.bulk.CompensoOptionRequestParameter;
import it.cnr.contab.doccont00.core.bulk.MandatoBulk;
import it.cnr.contab.doccont00.core.bulk.Numerazione_doc_contBulk;
import it.cnr.contab.doccont00.core.bulk.SospesoBulk;
import it.cnr.contab.doccont00.core.bulk.Sospeso_det_uscBulk;
import it.cnr.contab.doccont00.core.bulk.V_ass_doc_contabiliBulk;
import it.cnr.contab.doccont00.ejb.MandatoComponentSession;
import it.cnr.contab.doccont00.intcass.bulk.StatoTrasmissione;
import it.cnr.contab.doccont00.intcass.bulk.V_mandato_reversaleBulk;
import it.cnr.contab.doccont00.service.ContabiliService;
import it.cnr.contab.doccont00.service.DocumentiContabiliService;
import it.cnr.contab.reports.bp.OfflineReportPrintBP;
import it.cnr.contab.reports.bulk.Print_spooler_paramBulk;
import it.cnr.contab.service.SpringUtil;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.HookForward;
import it.cnr.jada.action.HttpActionContext;
import it.cnr.jada.comp.ApplicationException;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.ejb.CRUDComponentSession;
import it.cnr.jada.util.action.AbstractPrintBP;
import it.cnr.jada.util.action.SimpleDetailCRUDController;
import it.cnr.jada.util.ejb.EJBCommonServices;

import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Business Process che gestisce le attivit� di CRUD per l'entita' Mandato
 */

public abstract class CRUDAbstractMandatoBP extends it.cnr.jada.util.action.SimpleCRUDBP {

	private final CRUDSospesoController sospesiSelezionati = new CRUDSospesoController("SospesiSelezionati",Sospeso_det_uscBulk.class,"sospeso_det_uscColl",this);
	private final SimpleDetailCRUDController reversaliMan = new SimpleDetailCRUDController("Reversali",V_ass_doc_contabiliBulk.class,"doc_contabili_collColl",this);			
	private ContabiliService contabiliService;
	private DocumentiContabiliService documentiContabiliService;
	private String nodeRefDocumento;
	
	public CRUDAbstractMandatoBP() {}
	public CRUDAbstractMandatoBP( String function ) 
	{
		super(function);
	}
	/**
	 * Aggiunge un nuovo sospeso alla lista dei Sospeso associati ad un Mandato
	 * @param context contesto dell'azione
	 * @return it.cnr.jada.action.Forward forward successivo  
	 */
	public void aggiungiSospesi(ActionContext context) throws it.cnr.jada.action.BusinessProcessException 
	{
		try
		{
			MandatoBulk mandato = (MandatoBulk)getModel();
			HookForward caller = (HookForward)context.getCaller();
			List sospesi = (List) caller.getParameter("selectedElements");

			if ( sospesi == null )
				return;

			mandato.validaSospesi( sospesi );

			SospesoBulk sospeso;
			Sospeso_det_uscBulk sdu;
			for ( Iterator i = sospesi.iterator() ;i.hasNext() ;) 
			{
				sospeso = (SospesoBulk) i.next();
				sdu = mandato.addToSospeso_det_uscColl( sospeso );
				sdu.setToBeCreated();
				sdu.setUser(context.getUserInfo().getUserid());
			}
			setModel( context, mandato );
			resyncChildren( context );
		} catch(Exception e) {
			throw handleException(e);
		}		
	}
	protected CRUDComponentSession getComponentSession() {
		return (CRUDComponentSession) EJBCommonServices.createEJB("JADAEJB_CRUDComponentSession");
	}
	/**
	 *	Metodo per disabilitare tutti i campi, nel caso il mandato sia stato annullato ( come se fosse in stato di visualizzazione )
	 */
	public void basicEdit(it.cnr.jada.action.ActionContext context,it.cnr.jada.bulk.OggettoBulk bulk, boolean doInitializeForEdit) throws it.cnr.jada.action.BusinessProcessException {

		super.basicEdit(context, bulk, doInitializeForEdit);
		MandatoBulk mandato = (MandatoBulk)getModel();
		try {
			Optional.ofNullable(documentiContabiliService.getDocumentKey(
			(StatoTrasmissione) getComponentSession().findByPrimaryKey(context.getUserContext(),
					new V_mandato_reversaleBulk(mandato.getEsercizio(), Numerazione_doc_contBulk.TIPO_MAN, mandato.getCd_cds(), mandato.getPg_mandato()))
			)).ifPresent(s -> this.nodeRefDocumento = s);
		} catch(Exception _ex) {
			throw handleException(_ex);
		}

		if (getStatus()!=VIEW){
			if ( mandato != null && !mandato.getCd_uo_origine().equals( it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa( context ).getCd_unita_organizzativa()))
			{
				setStatus(VIEW);
				setMessage("Mandato creato dall'Unit� Organizzativa " + mandato.getCd_uo_origine() + ". Non consentita la modifica.");
			}
			else if ( mandato != null && mandato.getStato().equals( mandato.STATO_MANDATO_ANNULLATO ) && (mandato.getFl_riemissione()==null || !mandato.getFl_riemissione()))
			{
				setStatus(VIEW);
				setMessage("Mandato annullato. Non consentita la modifica.");
			}
			else if ( mandato != null && !mandato.getStato_trasmissione().equals(MandatoBulk.STATO_TRASMISSIONE_NON_INSERITO) &&
					!mandato.isMandatoAccreditamento() && !mandato.getStato().equals( mandato.STATO_MANDATO_ANNULLATO ) ) {
				setStatus(VIEW);
				setMessage("Verificare lo stato di trasmissione del mandato. Non consentita la modifica.");
			}
			else if( mandato != null  && mandato.getStato().equals( mandato.STATO_MANDATO_ANNULLATO ) && mandato.getFl_riemissione()!=null && mandato.getFl_riemissione() && !mandato.getStato_trasmissione_annullo().equals(MandatoBulk.STATO_TRASMISSIONE_NON_INSERITO)){
				setStatus(VIEW);
				setMessage("Verificare lo stato di trasmissione del mandato annullato. Non consentita la modifica.");
			}
		} 
	}
	/**
	 * Metodo utilizzato per gestire il caricamento dei sospesi.
	 * @param context <code>ActionContext</code> in uso.
	 *
	 * @return <code>Forward</code>
	 *
	 * @exception <code>BusinessProcessException</code>
	 */

	public it.cnr.jada.util.RemoteIterator cercaSospesi(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException 
	{
		try 
		{
			MandatoComponentSession session = (MandatoComponentSession) createComponentSession();
			return session.cercaSospesi( context.getUserContext(), null, (MandatoBulk) getModel() );

		} catch (it.cnr.jada.comp.ComponentException e) {
			throw handleException(e);
		} catch (java.rmi.RemoteException e) {
			throw handleException(e);
		}

		/*
	try 
	{
		MandatoComponentSession session = (MandatoComponentSession) createComponentSession();
		MandatoBulk mandato = session.listaSospesi( context.getUserContext(), (MandatoBulk) getModel() );
		setModel( context, mandato );
		resyncChildren( context );
	} catch(Exception e) {
		throw handleException(e);
	}
		 */

	}
	/**
	 * Gestisce l'annullamento di un Mandato.
	 * @param context contesto dell'azione
	 */
	public void delete(ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		delete( context, null );
	}
	/**
	 * Gestisce l'annullamento di un Mandato.
	 * @param context contesto dell'azione
	 * @param param il parametro che indica se il controllo sul compenso e' necessario
	 */
	public void delete(ActionContext context, CompensoOptionRequestParameter param) throws it.cnr.jada.action.BusinessProcessException {
		int crudStatus = getModel().getCrudStatus();
		try {
			validate(context);
			getModel().setToBeUpdated();
			setModel( context, ((MandatoComponentSession) createComponentSession()).annullaMandato(context.getUserContext(),(MandatoBulk)getModel(), param));
			setStatus(VIEW);			
		} catch(Exception e) {
			getModel().setCrudStatus(crudStatus);
			throw handleException(e);
		}
	}

	/**
	 * Metodo con cui si ottiene il valore della variabile <code>reversaliMan</code>
	 * di tipo <code>SimpleDetailCRUDController</code>.
	 * @return it.cnr.jada.util.action.SimpleDetailCRUDController
	 */
	public final it.cnr.jada.util.action.SimpleDetailCRUDController getReversaliMan() {
		return reversaliMan;
	}
	/**
	 * Metodo con cui si ottiene il valore della variabile <code>sospesiSelezionati</code>
	 * di tipo <code>CRUDSospesoController</code>.
	 * @return it.cnr.contab.doccont00.bp.CRUDSospesoController
	 */
	public final CRUDSospesoController getSospesiSelezionati() {
		return sospesiSelezionati;
	}
	/* inizializza il BP delle stampe impostando il nome del report da stampare e i suoi parametri */
	protected void initializePrintBP(AbstractPrintBP bp) 
	{
		OfflineReportPrintBP printbp = (OfflineReportPrintBP) bp;
		printbp.setReportName("/doccont/doccont/vpg_man_rev_ass.jasper");
		MandatoBulk mandato = (MandatoBulk)getModel();
		Print_spooler_paramBulk param;

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aCd_cds");
		param.setValoreParam(mandato.getCd_cds());
		param.setParamType("java.lang.String");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aEs");
		param.setValoreParam(mandato.getEsercizio().toString());
		param.setParamType("java.lang.Integer");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aPg_da");
		param.setValoreParam(mandato.getPg_mandato().toString());
		param.setParamType("java.lang.Long");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aPg_a");
		param.setValoreParam(mandato.getPg_mandato().toString());
		param.setParamType("java.lang.Long");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aDt_da");
		param.setValoreParam(OfflineReportPrintBP.DATE_FORMAT.format(mandato.getDt_emissione()));
		param.setParamType("java.util.Date");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aDt_a");
		param.setValoreParam(OfflineReportPrintBP.DATE_FORMAT.format(mandato.getDt_emissione()));
		param.setParamType("java.util.Date");
		printbp.addToPrintSpoolerParam(param);

		param = new Print_spooler_paramBulk();
		param.setNomeParam("aCd_terzo");
		param.setValoreParam("%");
		param.setParamType("java.lang.String");
		printbp.addToPrintSpoolerParam(param);
	}
	/**
	 *	Abilito il bottone di caricamento dei sospesi solo se il mandato non � stato
	 *  pagato o annullato e se e' a regolamento sospeso.
	 *
	 *	return boolean	= FALSE se il mandato e' stato pagato o annullato oppure se e' di regolarizzazione 
	 *					= TRUE se il mandato non e' stato pagato o annullato e se non e' di regolarizzazione 
	 */
	public boolean isCaricaSospesiButtonEnabled() {
		return

				MandatoBulk.TIPO_REGOLAM_SOSPESO.equals(((MandatoBulk)getModel()).getTi_mandato()) &&	
				/*!((MandatoBulk)getModel()).isPagato() && */
				!((MandatoBulk)getModel()).isAnnullato() ;
	}
	/**
	 *	Abilito il bottone di cancellazione documento solo se non � stato pagato o
	 *  annullato.
	 */
	public boolean isDeleteButtonEnabled() {

		if(!isEditable()) 
			return super.isDeleteButtonEnabled();			  
		else
			return (super.isDeleteButtonEnabled() ||
					(!isEditing() && 
							getModel()!=null && 
							getModel() instanceof MandatoBulk && 
							((MandatoBulk)getModel()).getStato_trasmissione() !=null && 
							!((MandatoBulk)getModel()).getStato_trasmissione().equals(MandatoBulk.STATO_TRASMISSIONE_NON_INSERITO))) &&
							//!((MandatoBulk)getModel()).isPagato() && 
							!((MandatoBulk)getModel()).isAnnullato() ;
	}
	
	@Override
	public boolean isPrintButtonHidden() {
		boolean hidden = super.isPrintButtonHidden() || isInserting() || isSearching();		
		return hidden || this.nodeRefDocumento != null;
	}

	public boolean isPrintpdfButtonHidden() {
		boolean hidden = super.isPrintButtonHidden() || isInserting() || isSearching();		
		return hidden || this.nodeRefDocumento == null;
	}	

	/**
	 *	Abilito il bottone di rimozione dei sospesi solo se il mandato non � stato
	 *  pagato o annullato.
	 *
	 *	isEditable 	= FALSE se il mandato e' stato pagato o annullato
	 *				= TRUE se il mandato non e' stato pagato o annullato
	 */
	public boolean isRimuoviSospesiButtonEnabled() {
		return /*!((MandatoBulk)getModel()).isPagato() && */
				!((MandatoBulk)getModel()).isAnnullato() ;
	}
	/**
	 *	Abilito il bottone di salvataggio solo se il mandato non � stato annullato.
	 *
	 *	isEditable 	= FALSE se il mandato e' stato pagato o annullato
	 *				= TRUE se il mandato non e' stato pagato o annullato
	 */
	public boolean isSaveButtonEnabled() {
		return super.isSaveButtonEnabled();// && !((MandatoBulk)getModel()).isAnnullato() ;
	}
	public void esistonoPiuModalitaPagamento(ActionContext context)throws it.cnr.jada.action.BusinessProcessException {
		try {
			((MandatoComponentSession) createComponentSession()).esistonoPiuModalitaPagamento(context.getUserContext(),(MandatoBulk)getModel());
		} catch (ComponentException e) {
			throw new BusinessProcessException(e);
		} catch (RemoteException e) {
			throw new BusinessProcessException(e);
		}
	}
	public boolean isDipendenteDaConguaglio(it.cnr.jada.action.ActionContext context,MandatoBulk mandato) throws it.cnr.jada.action.BusinessProcessException {
		try {
			return ((MandatoComponentSession) createComponentSession()).isDipendenteDaConguaglio(context.getUserContext(),mandato);
		} catch (it.cnr.jada.comp.ComponentException e) {
			throw handleException(e);
		} catch (java.rmi.RemoteException e) {
			throw handleException(e);
		}

	}
	protected void initialize(ActionContext actioncontext) throws BusinessProcessException {
		super.initialize(actioncontext);
		contabiliService = SpringUtil.getBean("contabiliService",
				ContabiliService.class);	
		documentiContabiliService = SpringUtil.getBean("documentiContabiliService",
				DocumentiContabiliService.class);
	}

	public boolean isContabileButtonHidden() throws ApplicationException{
		Boolean hidden = Boolean.TRUE;
		if (getStatus() == SEARCH)
			return hidden;
		MandatoBulk mandato = (MandatoBulk)getModel();
		if (mandato != null && mandato.getPg_mandato() != null && mandato.getStato() != null && 
				mandato.getStato().equalsIgnoreCase(MandatoBulk.STATO_MANDATO_PAGATO) &&
				mandato.getStato_trasmissione() != null && 
				mandato.getStato_trasmissione().equalsIgnoreCase(MandatoBulk.STATO_TRASMISSIONE_TRASMESSO))
			return contabiliService.getNodeRefContabile(mandato) == null;
		return hidden;
	}

	public String getContabileFileName(){
		MandatoBulk mandato = (MandatoBulk)getModel();
		if (mandato != null){
			return "Contabile ".
					concat(String.valueOf(mandato.getEsercizio())).
					concat("-").concat(mandato.getCd_cds()==null?"":mandato.getCd_cds()).
					concat("-").concat(String.valueOf(mandato.getPg_mandato())).
					concat(" .pdf");
		}
		return null;
	}

	public String getMandatoFileName(){
		MandatoBulk mandato = (MandatoBulk)getModel();
		if (mandato != null){
			return "Mandato n. ".
					concat(String.valueOf(mandato.getEsercizio())).
					concat("-").concat(String.valueOf(mandato.getPg_mandato())).
					concat(" .pdf");
		}
		return null;
	}
 
	public void scaricaContabile(ActionContext actioncontext) throws Exception {
		MandatoBulk mandato = (MandatoBulk)getModel();
		InputStream is = contabiliService.getStreamContabile(mandato);
		if (is != null){
			((HttpActionContext)actioncontext).getResponse().setContentType("application/pdf");
			OutputStream os = ((HttpActionContext)actioncontext).getResponse().getOutputStream();
			((HttpActionContext)actioncontext).getResponse().setDateHeader("Expires", 0);
			byte[] buffer = new byte[((HttpActionContext)actioncontext).getResponse().getBufferSize()];
			int buflength;
			while ((buflength = is.read(buffer)) > 0) {
				os.write(buffer,0,buflength);
			}
			is.close();
			os.flush();
		}
	}

	public void scaricaMandato(ActionContext actioncontext) throws Exception {
		MandatoBulk mandato = (MandatoBulk)getModel();
		InputStream is = documentiContabiliService.getStreamDocumento(
                (StatoTrasmissione) getComponentSession().findByPrimaryKey(actioncontext.getUserContext(),
		        new V_mandato_reversaleBulk(mandato.getEsercizio(), Numerazione_doc_contBulk.TIPO_MAN, mandato.getCd_cds(), mandato.getPg_mandato()))
		);
		if (is != null){
			((HttpActionContext)actioncontext).getResponse().setContentType("application/pdf");
			OutputStream os = ((HttpActionContext)actioncontext).getResponse().getOutputStream();
			((HttpActionContext)actioncontext).getResponse().setDateHeader("Expires", 0);
			byte[] buffer = new byte[((HttpActionContext)actioncontext).getResponse().getBufferSize()];
			int buflength;
			while ((buflength = is.read(buffer)) > 0) {
				os.write(buffer,0,buflength);
			}
			is.close();
			os.flush();
		}
	}
	public boolean isAnnullabileEnte(it.cnr.jada.action.ActionContext context,MandatoBulk mandato) throws it.cnr.jada.action.BusinessProcessException {
		try { 
			return  (((MandatoComponentSession) createComponentSession()).isAnnullabile(context.getUserContext(),mandato).compareTo("F")==0);
		} catch (it.cnr.jada.comp.ComponentException e) {
			throw handleException(e);
		} catch (java.rmi.RemoteException e) {
			throw handleException(e);
		}

	}
	public void deleteRiemissione(ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		int crudStatus = getModel().getCrudStatus();
		try {
			validate(context);
			getModel().setToBeUpdated();
			setModel( context, ((MandatoComponentSession) createComponentSession()).annullaMandato(context.getUserContext(),(MandatoBulk)getModel(), true));
			setStatus(VIEW);			
		} catch(Exception e) {
			getModel().setCrudStatus(crudStatus);
			throw handleException(e);
		}
	}

}