package it.cnr.contab.doccont00.core.bulk;

import java.math.*;
import it.cnr.contab.docamm00.docs.bulk.Nota_di_credito_rigaBulk;
import it.cnr.contab.utenze00.bp.CNRUserContext;
import java.util.*;

import it.cnr.contab.config00.contratto.bulk.ContrattoBulk;
import it.cnr.contab.config00.latt.bulk.WorkpackageBulk;
import it.cnr.contab.config00.sto.bulk.CdrBulk;
import it.cnr.contab.config00.sto.bulk.CdsBulk;
import it.cnr.jada.UserContext;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;
import it.cnr.jada.util.*;

public class AccertamentoBulk extends AccertamentoBase implements IDocumentoContabileBulk {
	
	private it.cnr.jada.util.OrderedHashtable anniResidui = new it.cnr.jada.util.OrderedHashtable();
	private it.cnr.contab.anagraf00.core.bulk.TerzoBulk debitore = new it.cnr.contab.anagraf00.core.bulk.TerzoBulk();
	private it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk capitolo = new it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk();
	private ContrattoBulk contratto = new ContrattoBulk();
	private it.cnr.contab.config00.sto.bulk.CdsBulk cds = new CdsBulk();

	private it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk unita_organizzativa = new it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk();
	
	private BulkList accertamento_scadenzarioColl = new BulkList();
	//private Vector linee_attivitaColl = new Vector();
	private List cdrColl = new Vector();				// Cdr dell'unita' organizzativa dell'accertamento

	private java.math.BigDecimal im_reversali;
	private java.math.BigDecimal im_parz_scadenze;

	//valori salvati alla rilettura da db
	private java.math.BigDecimal im_iniziale_accertamento;
	private Integer cd_terzo_iniziale;
	private boolean checkDisponibilitaContrattoEseguito = false;
	
	//indica se si arriva dal BP dei Docuemnti Amministrativi
	private boolean fromDocAmm = false;

	private Collection cdrSelezionatiColl = Collections.EMPTY_LIST;	
	private Collection capitoliDiEntrataCdsColl = Collections.EMPTY_LIST;
	private Collection capitoliDiEntrataCdsSelezionatiColl = Collections.EMPTY_LIST;
	private Collection lineeAttivitaColl = Collections.EMPTY_LIST;	
	private Collection lineeAttivitaSelezionateColl = Collections.EMPTY_LIST;

	private BulkList nuoveLineeAttivitaColl = new BulkList();

	public final static int INT_STATO_UNDEFINED				= 0;	
	public final static int INT_STATO_TESTATA_CONFERMATA	= 1;
	public final static int INT_STATO_CAPITOLI_CONFERMATI	= 2;
	public final static int INT_STATO_CDR_CONFERMATI	    = 3;	
	public final static int INT_STATO_LATT_CONFERMATE		= 4;

	private int internalStatus = INT_STATO_UNDEFINED;

public AccertamentoBulk() {
	super();
}
public AccertamentoBulk(java.lang.String cd_cds,java.lang.Integer esercizio,java.lang.Integer esercizio_originale,java.lang.Long pg_accertamento) {
	super(cd_cds,esercizio,esercizio_originale,pg_accertamento);
	setCds(new it.cnr.contab.config00.sto.bulk.CdsBulk(cd_cds));
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param scadenza	
 * @return 
 */
public int addToAccertamento_scadenzarioColl( Accertamento_scadenzarioBulk scadenza ) 
{
	accertamento_scadenzarioColl.add(scadenza);
	scadenza.setAccertamento(this);
	scadenza.setIm_scadenza( new java.math.BigDecimal(0));
	scadenza.setIm_associato_doc_amm( new java.math.BigDecimal(0));
	scadenza.setIm_associato_doc_contabile( new java.math.BigDecimal(0));	
	scadenza.setUser(this.getUser());
	scadenza.setDt_scadenza_emissione_fattura( getDt_registrazione() );
	if ( scadenza.getPg_accertamento_scadenzario() == null )
		scadenza.setPg_accertamento_scadenzario( getNextPgScadenza());
	
	return accertamento_scadenzarioColl.size()-1;
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param context	L'ActionContext della richiesta
 * @param notaDiCredito	
 * @param dettagli	
 */
public void completeFrom(
	it.cnr.jada.action.ActionContext context,
	it.cnr.contab.docamm00.docs.bulk.Nota_di_creditoBulk notaDiCredito,
	List dettagli) {

	setEsercizio( notaDiCredito.getEsercizio() );
	setEsercizio_competenza( getEsercizio());
	unita_organizzativa = it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa(context);

	setCd_unita_organizzativa(notaDiCredito.getCd_unita_organizzativa());
	setCd_cds(notaDiCredito.getCd_cds());

	setCd_uo_origine(notaDiCredito.getCd_uo_origine());
	setCd_cds_origine(notaDiCredito.getCd_cds_origine());
	
//	setStato_contabile("A" );
	setDebitore(notaDiCredito.getFornitore());
	setDs_accertamento("Accertamento per " +
						(notaDiCredito.getDs_fattura_passiva() == null ?
							"nota di credito" :
							notaDiCredito.getDs_fattura_passiva()));

	setDt_registrazione(notaDiCredito.getDt_registrazione());
	setDt_scadenza_contratto(notaDiCredito.getDt_scadenza());
	
	setFl_calcolo_automatico(Boolean.TRUE);

	java.math.BigDecimal importo = new java.math.BigDecimal(0);
	if (dettagli != null) {
		for (Iterator i = dettagli.iterator(); i.hasNext();) {
			Nota_di_credito_rigaBulk dettaglio = (Nota_di_credito_rigaBulk) i.next();
			//Accertamento_scadenzarioBulk scadenza = new Accertamento_scadenzarioBulk(this);
			//scadenza.setToBeCreated();
			//addToAccertamento_scadenzarioColl(scadenza);
			//scadenza.completeFrom(dettaglio);
			java.math.BigDecimal imTotaleDettaglio = dettaglio.getIm_imponibile().add(dettaglio.getIm_iva());
			importo = importo.add(imTotaleDettaglio);
		}
	}

	setIm_accertamento(importo.setScale(2, java.math.BigDecimal.ROUND_HALF_EVEN));
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'accertamento_scadenzarioColl'
 *
 * @return Il valore della propriet� 'accertamento_scadenzarioColl'
 */
public it.cnr.jada.bulk.BulkList getAccertamento_scadenzarioColl() {
	return accertamento_scadenzarioColl;
}
public BulkCollection[] getBulkLists() {

	// Metti solo le liste di oggetti che devono essere resi persistenti
	
	 return new it.cnr.jada.bulk.BulkCollection[] { 
			accertamento_scadenzarioColl };
}
/**
 * Insert the method's description here.
 * Creation date: (10/09/2002 11.16.55)
 * @return it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk
 */
public it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk getCapitolo() {
	return capitolo;
}
public java.lang.Integer getCd_terzo() {
	it.cnr.contab.anagraf00.core.bulk.TerzoBulk debitore = this.getDebitore();
	if (debitore == null)
		return null;
	return debitore.getCd_terzo();
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'internalStatus'
 *
 * @param newInternalStatus	Il valore da assegnare a 'internalStatus'
 */
public void setInternalStatus(int newInternalStatus) {
	internalStatus = newInternalStatus;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'cd_terzo_iniziale'
 *
 * @return Il valore della propriet� 'cd_terzo_iniziale'
 */
public java.lang.Integer getCd_terzo_iniziale() {
	return cd_terzo_iniziale;
}
public java.lang.String getCd_unita_organizzativa() {
	it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk unita_organizzativa = this.getUnita_organizzativa();
	if (unita_organizzativa == null)
		return null;
	return unita_organizzativa.getCd_unita_organizzativa();
}
public java.lang.String getCd_voce() {
	it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk capitolo = this.getCapitolo();
	if (capitolo == null)
		return null;
	return capitolo.getCd_voce();
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'cdrColl'
 *
 * @return Il valore della propriet� 'cdrColl'
 */
public java.util.List getCdrColl() {
	return cdrColl;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'debitore'
 *
 * @return Il valore della propriet� 'debitore'
 */
public it.cnr.contab.anagraf00.core.bulk.TerzoBulk getDebitore() {
	return debitore;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'ds_debitore'
 *
 * @return Il valore della propriet� 'ds_debitore'
 */
public java.lang.String getDs_debitore() 
{
	if( debitore != null && debitore.getDenominazione_sede() != null )
		return debitore.getDenominazione_sede();
	return "";	
		
	/*
	if( debitore != null && debitore.getAnagrafico() != null )
	{
		if((debitore.getAnagrafico().getCognome() != null) && (debitore.getAnagrafico().getNome() != null))
			return debitore.getAnagrafico().getCognome() + " " + debitore.getAnagrafico().getNome();
		if(debitore.getAnagrafico().getCognome() != null)
			return debitore.getAnagrafico().getCognome();
		if(debitore.getAnagrafico().getNome() != null)
			return debitore.getAnagrafico().getNome();					
	}	
	return "";
	*/
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'im_iniziale_accertamento'
 *
 * @return Il valore della propriet� 'im_iniziale_accertamento'
 */
public java.math.BigDecimal getIm_iniziale_accertamento() {
	return im_iniziale_accertamento;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'im_parz_scadenze'
 *
 * @return Il valore della propriet� 'im_parz_scadenze'
 */
public java.math.BigDecimal getIm_parz_scadenze() 
{
	im_parz_scadenze = new java.math.BigDecimal( 0 );

	Accertamento_scadenzarioBulk scadenza;
	
	for ( Iterator i = getAccertamento_scadenzarioColl().iterator(); i.hasNext(); )
	{
		scadenza = (Accertamento_scadenzarioBulk)i.next();
		if(scadenza.getIm_scadenza() != null)
			im_parz_scadenze = im_parz_scadenze.add( scadenza.getIm_scadenza());
	}	
	return im_parz_scadenze;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'im_reversali'
 *
 * @return Il valore della propriet� 'im_reversali'
 */
public java.math.BigDecimal getIm_reversali() {
	return im_reversali;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'linee_attivitaColl'
 *
 * @return Il valore della propriet� 'linee_attivitaColl'
 */
/*public java.util.Vector getLinee_attivitaColl() {
	return linee_attivitaColl;
}
*/
public String getManagerName() {

	return "CRUDAccertamentoBP";
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'nextPgScadenza'
 *
 * @return Il valore della propriet� 'nextPgScadenza'
 */
public Long getNextPgScadenza() 
{
	long max = 0;
	Accertamento_scadenzarioBulk scadenza;
	for ( Iterator i = getAccertamento_scadenzarioColl().iterator(); i.hasNext(); )
	{
		scadenza = (Accertamento_scadenzarioBulk)i.next();
		if ( scadenza.getPg_accertamento_scadenzario() != null && scadenza.getPg_accertamento_scadenzario().longValue() > max )
			max = scadenza.getPg_accertamento_scadenzario().longValue();
	}
	return new Long( max + 1);
}
public java.lang.Long getPg_doc_contabile() {
	return getPg_accertamento();
}
public Map getSaldiInfo() 
{
	HashMap values = new HashMap();
	values.put("pg_ver_rec", getPg_ver_rec());
	return values;
}
public java.lang.String getTi_appartenenza() {
	it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk capitolo = this.getCapitolo();
	if (capitolo == null)
		return null;
	return capitolo.getTi_appartenenza();
}
public java.lang.String getTi_competenza_residuo() 
{
	if ( Numerazione_doc_contBulk.TIPO_ACR_RES.equals( getCd_tipo_documento_cont() ))
		return MandatoBulk.TIPO_RESIDUO;
	return MandatoBulk.TIPO_COMPETENZA;		
}
public java.lang.String getTi_entrata_spesa() 
{
	return TI_ENTRATA;
}
public java.lang.String getTi_gestione() {
	it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk capitolo = this.getCapitolo();
	if (capitolo == null)
		return null;
	return capitolo.getTi_gestione();
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'unita_organizzativa'
 *
 * @return Il valore della propriet� 'unita_organizzativa'
 */
public it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk getUnita_organizzativa() {
	return unita_organizzativa;
}
/**
 * Inizializza l'Oggetto Bulk.
 * @param bp Il Business Process in uso
 * @param context Il contesto dell'azione
 * @return OggettoBulk L'oggetto bulk inizializzato
 */
public OggettoBulk initialize(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context)
{
	setEsercizio( it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context) );
	setEsercizio_competenza( getEsercizio());

	// Imposto l'Unita Organizzativa e il Cds di scrivania (uguale per tutti gli accertametni)
	/* SPOSTATO NEI SOTTOMETODI 
	it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk aUoScrivania = it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa(context);	
	setCd_uo_origine(aUoScrivania.getCd_unita_organizzativa());		
	setCd_cds_origine(aUoScrivania.getUnita_padre().getCd_unita_organizzativa());
	*/
	return this;
}
/**
 * Inizializza l'Oggetto Bulk per l'inserimento.
 * @param bp Il Business Process in uso
 * @param context Il contesto dell'azione
 * @return OggettoBulk L'oggetto bulk inizializzato
 */
public OggettoBulk initializeForInsert(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) 
{
	super.initializeForInsert(bp,context);
	setEsercizio_originale( it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context) );
	setFl_calcolo_automatico( new Boolean(true) );
	setRiportato("N");
	getDebitore().setAnagrafico( new it.cnr.contab.anagraf00.core.bulk.AnagraficoBulk());
	return this;
}
/**
 * Inizializza l'Oggetto Bulk per ricerca
 * @param bp Il Business Process in uso
 * @param context Il contesto dell'azione
 * @return OggettoBulk L'oggetto bulk inizializzato
 */
public OggettoBulk initializeForSearch(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) 
{
	super.initializeForSearch(bp,context);
	getDebitore().setAnagrafico( new it.cnr.contab.anagraf00.core.bulk.AnagraficoBulk());	
	return this;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'associataADocAmm'
 *
 * @return Il valore della propriet� 'associataADocAmm'
 */
public boolean isAssociataADocAmm() 
{
	for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
//		if ( ((Accertamento_scadenzarioBulk) i.next()).getIm_associato_doc_amm().compareTo( new BigDecimal(0)) != 0 )
		if ( ((Accertamento_scadenzarioBulk) i.next()).getPg_doc_attivo() != null  )
			return true;
	return false;	
}
public boolean isControparteRiportatata()
{
	return false;
}
public boolean isDocRiportato()
{
	
	if ( "Y".equals( getRiportato()))
		return true;
	return false;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'fromDocAmm'
 *
 * @return Il valore della propriet� 'fromDocAmm'
 */
public boolean isFromDocAmm() {
	return fromDocAmm;
}
public boolean isInitialized()
{
	return false;
}
public boolean isResiduo() {

	return getCd_tipo_documento_cont() != null && Numerazione_doc_contBulk.TIPO_ACR_RES.equals(getCd_tipo_documento_cont());
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'rOCapitolo'
 *
 * @return Il valore della propriet� 'rOCapitolo'
 */
public boolean isROCapitolo() 
{
	return getDt_cancellazione() != null || capitolo == null || capitolo.getCrudStatus() == NORMAL || isAssociataADocCont();
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'rODebitore'
 *
 * @return Il valore della propriet� 'rODebitore'
 */
public boolean isRODebitore() 
{
	return getDt_cancellazione() != null || debitore == null || debitore.getCrudStatus() == NORMAL;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'rOContratto'
 *
 * @return Il valore della propriet� 'rOContratto'
 */
public boolean isROContratto() {
	return contratto == null || contratto.getCrudStatus() == NORMAL;
}
public boolean isTemporaneo() {

	if (getPg_accertamento() == null)
		return false;
	return getPg_accertamento().longValue() < 0;
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param collLineeAttivita	
 * @return 
 */
public Vector ordinaLineeAttivitaPerCodice( Vector collLineeAttivita ) 
{
	// riordino la lista delle linee di attivita' per codice 
	
	Collections.sort( collLineeAttivita,new Comparator() {	

		public int compare(Object o1, Object o2) 
		{
			WorkpackageBulk os1 = (WorkpackageBulk) o1;
			WorkpackageBulk os2 = (WorkpackageBulk) o2;

			return os1.getCd_linea_attivita().compareTo( os2.getCd_linea_attivita());
		}
	});

	return collLineeAttivita;
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param index	
 * @return 
 */
public Accertamento_scadenzarioBulk removeFromAccertamento_scadenzarioColl(int index) 
{
	// Gestisce la selezione del bottone cancella scadenza
	return (Accertamento_scadenzarioBulk)accertamento_scadenzarioColl.remove(index);
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'accertamento_scadenzarioColl'
 *
 * @param newAccertamento_scadenzarioColl	Il valore da assegnare a 'accertamento_scadenzarioColl'
 */
public void setAccertamento_scadenzarioColl(it.cnr.jada.bulk.BulkList newAccertamento_scadenzarioColl) {
	accertamento_scadenzarioColl = newAccertamento_scadenzarioColl;
}
/**
 * Insert the method's description here.
 * Creation date: (10/09/2002 11.16.55)
 * @param newCapitolo it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk
 */
public void setCapitolo(it.cnr.contab.config00.pdcfin.bulk.V_voce_f_partita_giroBulk newCapitolo) {
	capitolo = newCapitolo;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'cd_linea_attivita'
 *
 * @param cd_linea_attivita	Il valore da assegnare a 'cd_linea_attivita'
 */
public void setCd_terzo(java.lang.Integer cd_terzo) {
	this.getDebitore().setCd_terzo(cd_terzo);
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'cd_terzo_iniziale'
 *
 * @param newCd_terzo_iniziale	Il valore da assegnare a 'cd_terzo_iniziale'
 */
public void setCd_terzo_iniziale(java.lang.Integer newCd_terzo_iniziale) {
	cd_terzo_iniziale = newCd_terzo_iniziale;
}
public void setCd_unita_organizzativa(java.lang.String cd_unita_organizzativa) {
	this.getUnita_organizzativa().setCd_unita_organizzativa(cd_unita_organizzativa);
}
public void setCd_voce(java.lang.String cd_voce) {
	this.getCapitolo().setCd_voce(cd_voce);
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'cdrColl'
 *
 * @param newCdrColl	Il valore da assegnare a 'cdrColl'
 */
public void setCdrColl(java.util.List newCdrColl) {
	cdrColl = newCdrColl;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'debitore'
 *
 * @param newDebitore	Il valore da assegnare a 'debitore'
 */
public void setDebitore(it.cnr.contab.anagraf00.core.bulk.TerzoBulk newDebitore) {
	debitore = newDebitore;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'fromDocAmm'
 *
 * @param newFromDocAmm	Il valore da assegnare a 'fromDocAmm'
 */
public void setFromDocAmm(boolean newFromDocAmm) {
	fromDocAmm = newFromDocAmm;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'im_iniziale_accertamento'
 *
 * @param newIm_iniziale_accertamento	Il valore da assegnare a 'im_iniziale_accertamento'
 */
public void setIm_iniziale_accertamento(java.math.BigDecimal newIm_iniziale_accertamento) {
	im_iniziale_accertamento = newIm_iniziale_accertamento;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'im_reversali'
 *
 * @param newIm_reversali	Il valore da assegnare a 'im_reversali'
 */
public void setIm_reversali(java.math.BigDecimal newIm_reversali) {
	im_reversali = newIm_reversali;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'linee_attivitaColl'
 *
 * @param newLinee_attivitaColl	Il valore da assegnare a 'linee_attivitaColl'
 */
/*
public void setLinee_attivitaColl(java.util.Vector newLinee_attivitaColl) 
{
	Vector coll = new Vector();
	coll = newLinee_attivitaColl;
	
	if((coll != null) && (coll.size() > 0))
		coll = ordinaLineeAttivitaPerCodice(coll);
		
	linee_attivitaColl = coll;
}
*/
public void setTi_appartenenza(java.lang.String ti_appartenenza) {
	this.getCapitolo().setTi_appartenenza(ti_appartenenza);
}
public void setTi_gestione(java.lang.String ti_gestione) {
	this.getCapitolo().setTi_gestione(ti_gestione);
}
public void setToBeDeleted() 
{
	super.setToBeDeleted();
	/*
	for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
		((Accertamento_scadenzarioBulk) i.next()).setToBeDeleted();
	for ( Iterator i = accertamento_scadenzarioColl.deleteIterator(); i.hasNext(); )
		((Accertamento_scadenzarioBulk) i.next()).setToBeDeleted();
	*/	
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della propriet� 'unita_organizzativa'
 *
 * @param newUnita_organizzativa	Il valore da assegnare a 'unita_organizzativa'
 */
public void setUnita_organizzativa(it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk newUnita_organizzativa) {
	unita_organizzativa = newUnita_organizzativa;
}
/**
 *	Imposto tutti gli importi dell'accertamento a 0
 *	(importo accertamento, importo scadenze e importo relativi dettagli)
 * 
 *
 */
public void storna() 
{
	setIm_accertamento( new java.math.BigDecimal( 0 ));
	setToBeUpdated();
	
	for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
		((Accertamento_scadenzarioBulk) i.next()).storna();
	for ( Iterator i = accertamento_scadenzarioColl.deleteIterator(); i.hasNext(); )
		((Accertamento_scadenzarioBulk) i.next()).storna();		
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param accTable	
 */
public void  updateScadenzeFromDocAmm( it.cnr.contab.docamm00.docs.bulk.AccertamentiTable accTable )
{
	Accertamento_scadenzarioBulk scadenza;

	if((accertamento_scadenzarioColl == null) || (accTable == null))
		return;
		
	for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
	{
		scadenza = (Accertamento_scadenzarioBulk) i.next();
		if(accTable.get( scadenza ) != null)
			scadenza.setFromDocAmm( true );
		else
			scadenza.setFromDocAmm( false );		
	}	
}
/**
 * Se modifico la data dell'accertamento posticipandola e se ho gia' inserito 
 * delle scadenze devo controllare che queste ultime abbiamo le date posteriori 
 * a quella dell'accertamento.
 *
 * @return 
 * @throws ValidationException	
 */
public boolean validaDataRegistrazione() throws ValidationException 
{
	int c=0;
	Accertamento_scadenzarioBulk scadenza;

	if(getAccertamento_scadenzarioColl() == null)
		return true;
		
	while(c < getAccertamento_scadenzarioColl().size())
	{
		scadenza = (Accertamento_scadenzarioBulk) getAccertamento_scadenzarioColl().get(c);
		if(getDt_registrazione().after(scadenza.getDt_scadenza_incasso()))
			return false;
		c++;
	}
	return true;	
}
/**
 * Metodo con cui si verifica la validit� di alcuni campi, mediante un 
 * controllo sintattico o contestuale.
 */
public void validate() throws ValidationException {
	
	super.validate();
		
	// controllo su campo ESERCIZIO COMPETENZA
	if ( getEsercizio_competenza() == null )
		throw new ValidationException( "Il campo ESERCIZIO � obbligatorio." );

	if ( getEsercizio_competenza().intValue() < getEsercizio().intValue() )
		throw new ValidationException("Non � possibile creare un accertamento con esercizio antecedente a quello di scrivania.");		

	// controllo su campo DATA REGISTRAZIONE
	if ( getDt_registrazione() == null )
		throw new ValidationException( "Il campo DATA � obbligatorio." );

	java.sql.Timestamp dataRegistrazione = getDt_registrazione();
//	java.sql.Timestamp dataSistema = new java.sql.Timestamp(System.currentTimeMillis());
	java.sql.Timestamp dataSistema;
	try
	{
		dataSistema = it.cnr.jada.util.ejb.EJBCommonServices.getServerDate();
	}
	catch ( Exception e )
	{
		throw new ValidationException( "Impossibile recuperare la data di sistema!");
	}		
	if (dataRegistrazione.after(dataSistema))
		throw new ValidationException( "Non � possibile inserire una data di registrazione posteriore a quella di sistema." );
			
	// controllo su campo DESCRIZIONE
	if ( getDs_accertamento() == null || getDs_accertamento().equals("") )
		throw new ValidationException( "Il campo DESCRIZIONE � obbligatorio." );

	// controllo su campo DEBITORE
	if ( debitore == null || debitore.getCd_terzo() == null || debitore.getCd_terzo().equals("") )
		throw new ValidationException( "Il campo DEBITORE � obbligatorio." );
	
	// controllo su campo IMPORTO
	if ( getIm_accertamento() == null)
		setIm_accertamento(new BigDecimal(0));
		/* 15/03/2003 simona - rimosso il vincolo che la testata dell'accertamento deve avere importo diverso da 0 ; 
		infatti nel caso di accertamento con 1 sola scadenza legata a doc amm che viene messa a 0, anche la testata diventa 0
		*/
	if (getIm_accertamento().compareTo(new java.math.BigDecimal(0)) < 0)
		throw new ValidationException( "L' IMPORTO dell'accertamento deve essere maggiore o uguale a 0." );
		
	// controllo su campo CAPITOLO
	if ( capitolo == null || capitolo.getCd_voce() == null || capitolo.getCd_voce().equals("") )
		throw new ValidationException( "Il campo CAPITOLO � obbligatorio." );

	// controllo su selezione LINEA di ATTIVITA'
	//if ( getLinea_attivita() == null || getLinea_attivita().equals("") || getLinea_attivita().getCd_linea_attivita() == null)
	//	throw new ValidationException( "Selezionare il GAE." );

	for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
		((Accertamento_scadenzarioBulk) i.next()).validate();	
}
/**
 * Metodo con cui si verifica la validit� di una nuovo terzo
 */
public void validateTerzo( it.cnr.contab.anagraf00.core.bulk.TerzoBulk terzo ) throws ValidationException 
{
//	java.sql.Timestamp dataSistema = new java.sql.Timestamp(System.currentTimeMillis());
	java.sql.Timestamp dataSistema;
	try
	{
		dataSistema = it.cnr.jada.util.ejb.EJBCommonServices.getServerDate();
	}
	catch ( Exception e )
	{
		throw new ValidationException( "Impossibile recuperare la data di sistema!");
	}		


	if ( terzo.getDt_fine_rapporto() != null && terzo.getDt_fine_rapporto().before(dataSistema))
	 	 throw new ValidationException( "Il terzo non � pi� abilitato." );
	if ( !terzo.getTi_terzo().equals( terzo.DEBITORE) && !terzo.getTi_terzo().equals( terzo.ENTRAMBI) )
	 	 throw new ValidationException( "Il terzo deve essere di tipo DEBITORE o ENTRAMBI" );
	if ( isAssociataADocAmm() && !terzo.getCd_terzo().equals( getCd_terzo_iniziale()) && terzo.getAnagrafico() != null && !terzo.getAnagrafico().getTi_entita().equals( it.cnr.contab.anagraf00.core.bulk.AnagraficoBulk.DIVERSI ))
	 	 throw new ValidationException( "Accertamento gi� associato a documenti amministrativi: Il terzo deve essere di tipo DIVERSI" );
}
	/**
	 * @return
	 */
	public ContrattoBulk getContratto() {
		return contratto;
	}

	/**
	 * @param bulk
	 */
	public void setContratto(ContrattoBulk bulk) {
		contratto = bulk;
	}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#getEsercizio_contratto()
	 */
	public java.lang.Integer getEsercizio_contratto() {
		if (getContratto()==null)
		  return null;
		return getContratto().getEsercizio();
	}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#getPg_contratto()
	 */
	public java.lang.Long getPg_contratto() {
		if (getContratto()==null)
		  return null;
		return getContratto().getPg_contratto();
	}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#setEsercizio_contratto(java.lang.Integer)
	 */
	public void setEsercizio_contratto(java.lang.Integer esercizio) {
		getContratto().setEsercizio(esercizio);
	}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#setPg_contratto(java.lang.Long)
	 */
	public void setPg_contratto(java.lang.Long pg_contratto) {
		getContratto().setPg_contratto(pg_contratto);
	}
/**
 * Verifica se il bottone di Aggiungi delle Latt e' abilitato
 */
public boolean isAddNuoveLattEnabled()
{
	return getCapitoliDiEntrataCdsSelezionatiColl() != null && getCapitoliDiEntrataCdsSelezionatiColl().size() > 0;
}	

/**
 * @return java.util.Collection
 */
public java.util.Collection getCapitoliDiEntrataCdsColl() {
	return capitoliDiEntrataCdsColl;
}
/**
 * @return java.util.Collection
 */
public java.util.Collection getCapitoliDiEntrataCdsSelezionatiColl() {
	return capitoliDiEntrataCdsSelezionatiColl;
}
/**
 * Verifica se il bottone di Conferma dei Centri di Responsabilit� � abilitato.
 * @return 				TRUE 	Il bottone di Conferma dei Centri di Responsabilit� � abilitato
 *						FALSE 	Il bottone di Conferma dei Centri di Responsabilit� non � abilitato
 */
public boolean isConfermaCentriDiResponsabilitaEnabled()
{
	return (getInternalStatus() >= INT_STATO_CAPITOLI_CONFERMATI) && getCdrColl().size() > 0 ;
}	
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della propriet� 'internalStatus'
 *
 * @return Il valore della propriet� 'internalStatus'
 */
public int getInternalStatus() {
	return internalStatus;
}
/**
 * Verifica se il bottone di Conferma delle Linee di Attivit� � abilitato.
 * @return 				TRUE 	Il bottone di Conferma delle Linee di Attivit� � abilitato
 *						FALSE 	Il bottone di Conferma delle Linee di Attivit� non � abilitato
 */
public boolean isConfermaLineeAttivitaEnabled()
{
	return (getInternalStatus() >= INT_STATO_CDR_CONFERMATI ) || 
		   (getNuoveLineeAttivitaColl() != null && getNuoveLineeAttivitaColl().size() > 0 );
}	
/**
 * @return java.util.Collection
 */
public BulkList getNuoveLineeAttivitaColl() {
	return nuoveLineeAttivitaColl;
}
/**
 * @param newNuoveLineeAttivitaColl java.util.Collection
 */
public void setNuoveLineeAttivitaColl(BulkList newNuoveLineeAttivitaColl) {
	nuoveLineeAttivitaColl = newNuoveLineeAttivitaColl;
}
/**
 * @return java.util.Collection
 */
public Collection getLineeAttivitaColl() {
	return lineeAttivitaColl;
}
/**
 * @param newLineeAttivitaColl java.util.Collection
 */
public void setLineeAttivitaColl(Collection newLineeAttivitaColl) {
	lineeAttivitaColl = newLineeAttivitaColl;
}
/**
 * @return java.util.Collection
 */
public java.util.Collection getCdrSelezionatiColl() {
	return cdrSelezionatiColl;
}
/**
 * @param newCapitoliDiEntrataCdsSelezionatiColl java.util.Collection
 */
public void setCapitoliDiEntrataCdsSelezionatiColl(java.util.Collection newCapitoliDiEntrataCdsSelezionatiColl) {
	capitoliDiEntrataCdsSelezionatiColl = newCapitoliDiEntrataCdsSelezionatiColl;
}
/**
 * @param newCdrSelezionatiColl java.util.Collection
 */
public void setCdrSelezionatiColl(java.util.Collection newCdrSelezionatiColl) {
	cdrSelezionatiColl = newCdrSelezionatiColl;
}
/**
 * @param newLineeAttivitaSelezionateColl java.util.Collection
 */
public void setLineeAttivitaSelezionateColl(java.util.Collection newLineeAttivitaSelezionateColl) {
	lineeAttivitaSelezionateColl = newLineeAttivitaSelezionateColl;
}
/**
 * @param newCapitoliDiEntrataCdsColl java.util.Collection
 */
public void setCapitoliDiEntrataCdsColl(java.util.Collection newCapitoliDiEntrataCdsColl) {
	capitoliDiEntrataCdsColl = newCapitoliDiEntrataCdsColl;
}
public boolean hasDettagli()
{
	return  accertamento_scadenzarioColl != null &&
			accertamento_scadenzarioColl.size() > 0  &&
			((Accertamento_scadenzarioBulk) accertamento_scadenzarioColl.get(0)).getAccertamento_scad_voceColl() != null &&
			((Accertamento_scadenzarioBulk) accertamento_scadenzarioColl.get(0)).getAccertamento_scad_voceColl().size() > 0 ;
}
public int addToNuoveLineeAttivitaColl( Linea_attivitaBulk latt ) 
{
	latt.getLinea_att().setCentro_responsabilita( new CdrBulk());
//	latt.getLinea_att().setEsercizio( getEsercizio_competenza() );
	latt.setAccertamento( this );
	nuoveLineeAttivitaColl.add(latt);
	latt.setAccertamento(this);
	return nuoveLineeAttivitaColl.size()-1;
}
public Linea_attivitaBulk removeFromNuoveLineeAttivitaColl(int index) 
{
	return (Linea_attivitaBulk)nuoveLineeAttivitaColl.remove( index );
}
public java.util.Collection getLineeAttivitaSelezionateColl() {
	return lineeAttivitaSelezionateColl;
}
/**
 * Metodo con cui si verifica la validit� di una nuova linea di attivita
 */
public void validateNuovaLineaAttivita( it.cnr.contab.doccont00.core.bulk.Linea_attivitaBulk nuovaLatt, it.cnr.contab.config00.latt.bulk.WorkpackageBulk latt) throws ValidationException 
{
	// verifico che la linea di attivit� sia di tipo ENTRATE
	if ( ! it.cnr.contab.config00.latt.bulk.WorkpackageBulk.TI_GESTIONE_ENTRATE.equals(latt.getTi_gestione()))
		 throw new ValidationException( "Il nuovo GAE deve avere tipo gestione = ENTRATE");						 	 
	
	// verifico che la linea di attivit� non sia gi� stata inserita
	for ( Iterator i = getNuoveLineeAttivitaColl().iterator(); i.hasNext(); )
	{
		it.cnr.contab.doccont00.core.bulk.Linea_attivitaBulk la = (it.cnr.contab.doccont00.core.bulk.Linea_attivitaBulk) i.next();
		if ( !la.equals( nuovaLatt ) && latt.getCentro_responsabilita().getCd_centro_responsabilita().equals( la.getLinea_att().getCentro_responsabilita().getCd_centro_responsabilita()) &&
			 latt.getCd_linea_attivita().equals( la.getLinea_att().getCd_linea_attivita() ))
				 throw new ValidationException( "Il nuovo GAE e' gi� presente");
	}

	// verifico che la funzione della nuova linea di attivit� sia fra quelle dei capitoli selezionati
	boolean found = false;
	for ( Iterator i = getCapitoliDiEntrataCdsSelezionatiColl().iterator(); i.hasNext(); )
	{
		if ( ((it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk) i.next()).getCd_funzione().equals( latt.getFunzione().getCd_funzione()))
		{
			found = true;
			break;
		}
	}
	if ( !found)
		 throw new ValidationException( "La funzione del nuovo GAE non appartiene alla lista di funzioni selezionate");			

	// verifico che il cdr della linea di attivit� appartenga all'uo dell'Accertamento
//	if ( !getCd_unita_organizzativa().equals(latt.getCentro_responsabilita().getUnita_padre().getCd_unita_organizzativa()))
	if ( !latt.getCd_centro_responsabilita().startsWith(getCd_unita_organizzativa()))
		 throw new ValidationException( "Il Cdr del nuovo GAE non appartiene all'unita organizzativa dell'accertamento");					
	//per CDS SAC verifico che la coppia CDR/funzione sia presente negli articoli selezionati dall'utente
	/* simona 8.5.2002 : non pi� necessario dopo la modifica della gestione del cds sac per accertamento
	if ( getCds().getCd_tipo_unita().equals(Tipo_unita_organizzativaHome.TIPO_UO_SAC))
	{
		boolean ok = false;
		for (Iterator i = getCapitoliDiEntrataCdsSelezionatiColl().iterator(); i.hasNext(); )
		{
			Voce_fBulk voce = (Voce_fBulk)i.next();
			if ( voce.getCd_centro_responsabilita().equals( latt.getCd_centro_responsabilita()) &&
				 voce.getCd_funzione().equals(latt.getCd_funzione()))
			{
				ok = true; 
				 break;
			}	 
		}
		if ( !ok)
		 throw new ValidationException( "Il nuovo GAE non ha cdr e funzione uguale a uno di quelli degli articoli di spesa selezionati");
	 	 
	}
	*/
	// verifico che la linea di attivit� non sia presente nel piano di gestione //
	/* simona 7.5.2002: spostato il controllo sulla Component perch� e' necessario accedere al db 
	V_pdg_accertamento_etrBulk vpos;
	for ( Iterator i = lineeAttivitaColl.iterator(); i.hasNext(); )
	{
		vpos = (V_pdg_accertamento_etrBulk) i.next();
		if ( vpos.getCd_centro_responsabilita().equals(latt.getCd_centro_responsabilita()) &&
			 vpos.getCd_linea_attivita().equals( latt.getCd_linea_attivita()))
			throw new ValidationException( "Il nuovo GAE e' presente nel piano di gestione");							
	}
	*/
}
public java.lang.String getCd_cds() {
	it.cnr.contab.config00.sto.bulk.CdsBulk cds = this.getCds();
	if (cds == null)
		return null;
	return cds.getCd_unita_organizzativa();
}
public it.cnr.contab.config00.sto.bulk.CdsBulk getCds() {
	return cds;
}
public void setCds(it.cnr.contab.config00.sto.bulk.CdsBulk newCds) {
	cds = newCds;
}
public void refreshCdrSelezionatiColl() 
{
	Hashtable cdrTable = new Hashtable(); // hashtable per evitare i doppi
	
	//l'imputazione finanziaria e' sempre di testata: seleziono i dettagli di una qualsiasi scadenza per
	// individuare l'elenco dei cdr selezionati per l'intera accertamento
	for ( Iterator s = ((Accertamento_scadenzarioBulk) accertamento_scadenzarioColl.iterator().next()).getAccertamento_scad_voceColl().iterator(); s.hasNext(); )
	{
		Accertamento_scad_voceBulk osv = (Accertamento_scad_voceBulk) s.next();
		for ( Iterator c = cdrColl.iterator(); c.hasNext(); )
		{
			CdrBulk cdr = ( CdrBulk) c.next();
			if ( osv.getLinea_attivita().getCentro_responsabilita().getCd_centro_responsabilita().equals( cdr.getCd_centro_responsabilita() ))
				cdrTable.put ( osv.getLinea_attivita().getCentro_responsabilita().getCd_centro_responsabilita(), cdr );
		}
	}

	cdrSelezionatiColl = new Vector();
	for ( Enumeration e = cdrTable.keys(); e.hasMoreElements(); )
		cdrSelezionatiColl.add( cdrTable.get( e.nextElement() ));
}
public java.util.Collection getCdrDiScrivaniaSelezionatiColl( String cd_unita_organizzativa ) {
	List cdrColl = new LinkedList();
	cdrColl = (List) getCdrSelezionatiColl();
	return cdrColl;
}

public void refreshLineeAttivitaSelezionateColl() 
{
	Hashtable laTable = new Hashtable(); // hashtable per evitare i doppi
	
	//l'imputazione finanziaria e' sempre di testata: seleziono i dettagli di una qualsiasi scadenza per
	// individuare l'elenco delle linee di attivita selezionate per l'intera accertamento
	for ( Iterator s = ((Accertamento_scadenzarioBulk) accertamento_scadenzarioColl.iterator().next()).getAccertamento_scad_voceColl().iterator(); s.hasNext(); )
	{
		Accertamento_scad_voceBulk osv = (Accertamento_scad_voceBulk) s.next();
		for ( Iterator l = lineeAttivitaColl.iterator(); l.hasNext(); )
		{
			V_pdg_accertamento_etrBulk latt = (V_pdg_accertamento_etrBulk) l.next();
			if ( osv.getLinea_attivita().getCentro_responsabilita().getCd_centro_responsabilita().equals( latt.getCd_centro_responsabilita() ) &&
				 osv.getLinea_attivita().getCd_linea_attivita().equals( latt.getCd_linea_attivita() ) )
				laTable.put ( osv.getLinea_attivita().getCentro_responsabilita().getCd_centro_responsabilita() + osv.getLinea_attivita().getCd_linea_attivita(), latt );
		}
	}

	lineeAttivitaSelezionateColl = new Vector();
	for ( Enumeration e = laTable.keys(); e.hasMoreElements(); )
		lineeAttivitaSelezionateColl.add( laTable.get( e.nextElement() ));

}
public void refreshCapitoliDiEntrataCdsSelezionatiColl() 
{
	Hashtable capitoli = new Hashtable(); // hashtable per evitare i doppi
	
	//l'imputazione finanziaria e' sempre di testata: seleziono i dettagli di una qualsiasi scadenza per
	// individuare l'elenco dei capitoli di entrata selezionati per l'intero accertamento
	for ( Iterator s = ((Accertamento_scadenzarioBulk) accertamento_scadenzarioColl.iterator().next()).getAccertamento_scad_voceColl().iterator(); s.hasNext(); )
	{
		Accertamento_scad_voceBulk osv = (Accertamento_scad_voceBulk) s.next();
		for ( Iterator c = capitoliDiEntrataCdsColl.iterator(); c.hasNext(); )
		{
			it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk voce = ( it.cnr.contab.config00.pdcfin.bulk.Voce_fBulk) c.next();
			if ( osv.getCd_voce().equals( voce.getCd_voce() ))
				capitoli.put ( osv.getCd_voce(), voce );
		}
	}

	capitoliDiEntrataCdsSelezionatiColl = new Vector();
	for ( Enumeration e = capitoli.keys(); e.hasMoreElements(); )
		capitoliDiEntrataCdsSelezionatiColl.add( capitoli.get( e.nextElement() ));
		
}
public void setCd_cds(java.lang.String cd_cds) {
	this.getCds().setCd_unita_organizzativa(cd_cds);
}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#getPg_contratto()
	 */
	public java.lang.String getStato_contratto() {
		if (getContratto()==null)
		  return null;		
		return getContratto().getStato();
	}
	/*
	 *  (non-Javadoc)
	 * @see it.cnr.contab.doccont00.core.bulk.ObbligazioneBase#setEsercizio_contratto(java.lang.Integer)
	 */
	public void setStato_contratto(java.lang.String stato) {
		getContratto().setStato(stato);
	}	
	/**
	 * @return
	 */
	public boolean isCheckDisponibilitaContrattoEseguito() {
		return checkDisponibilitaContrattoEseguito;
	}

	/**
	 * @param b
	 */
	public void setCheckDisponibilitaContrattoEseguito(boolean b) {
		checkDisponibilitaContrattoEseguito = b;
	}
	public boolean isAccertamentoResiduo() {
		return getCd_tipo_documento_cont() != null && 
			   Numerazione_doc_contBulk.TIPO_ACR_RES.equals(getCd_tipo_documento_cont());
	}
	public it.cnr.jada.util.OrderedHashtable getAnniResidui() {
		return anniResidui;
	}
	public void setAnniResidui(it.cnr.jada.util.OrderedHashtable hashtable) {
		anniResidui = hashtable;
	}
	public void caricaAnniResidui(ActionContext actioncontext) { 
		for (int i=CNRUserContext.getEsercizio(actioncontext.getUserContext()).intValue()-1;i>=CNRUserContext.getEsercizio(actioncontext.getUserContext()).intValue()-10;i--)
			anniResidui.put(new Integer(i), new Integer(i));
	}
	/**
	 * <!-- @TODO: da completare -->
	 * Restituisce il valore della propriet� 'associataADocCont'
	 *
	 * @return Il valore della propriet� 'associataADocCont'
	 */
	public boolean isAssociataADocCont() 
	{
		for ( Iterator i = accertamento_scadenzarioColl.iterator(); i.hasNext(); )
			if ( ((Accertamento_scadenzarioBulk) i.next()).getPg_reversale() != null  )
				return true;
		return false;	
	}
	@Override
	public void insertingUsing(Persister persister, UserContext userContext) {
		if (getFl_netto_sospeso() == null)
			setFl_netto_sospeso(Boolean.FALSE);
		super.insertingUsing(persister, userContext);
	}
}
