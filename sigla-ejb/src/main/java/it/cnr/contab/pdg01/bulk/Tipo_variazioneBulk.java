/*
* Created by Generator 1.0
* Date 14/06/2006
*/
package it.cnr.contab.pdg01.bulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.util.action.CRUDBP;
public class Tipo_variazioneBulk extends Tipo_variazioneBase {
	public final static java.util.Dictionary TIPI_VARIAZIONE;
	public final static java.util.Dictionary TIPI_APPROVAZIONE;

	final public static String STORNO_SPESA_STESSO_ISTITUTO = "STO_S_CDS";
	final public static String STORNO_ENTRATA_STESSO_ISTITUTO = "STO_E_CDS";
	final public static String STORNO_SPESA_ISTITUTI_DIVERSI = "STO_S_TOT";
	final public static String STORNO_ENTRATA_ISTITUTI_DIVERSI = "STO_E_TOT";
	final public static String VARIAZIONE_POSITIVA_STESSO_ISTITUTO = "VAR_PIU_CDS";
	final public static String VARIAZIONE_NEGATIVA_STESSO_ISTITUTO = "VAR_MENO_CDS";
	final public static String VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI = "VAR_PIU_TOT";
	final public static String VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI = "VAR_MENO_TOT";
	final public static String VARIAZIONE_STESSO_ISTITUTO = "VAR_CDS";
	final public static String VARIAZIONE_ISTITUTI_DIVERSI = "VAR_TOT";
	final public static String PRELIEVO_FONDI = "PREL_FON";
	final public static String RESTITUZIONE_FONDI = "REST_FON";
    final public static String VARIAZIONE_POSITIVA_FONDI = "VAR_PIU_FON";
	final public static String VARIAZIONE_NEGATIVA_FONDI = "VAR_MENO_FON";
	final public static String NON_DEFINITO = "NO_TIPO";

	public final static String APPROVAZIONE_CDS = "C";
	public final static String APPROVAZIONE_ENTE = "E";

	static{
		TIPI_VARIAZIONE = new it.cnr.jada.util.OrderedHashtable();
		TIPI_VARIAZIONE.put(STORNO_SPESA_STESSO_ISTITUTO,"Storno di Spesa all'interno dello stesso Istituto/SAC/Area");
		TIPI_VARIAZIONE.put(STORNO_ENTRATA_STESSO_ISTITUTO,"Storno di Entrata all'interno dello stesso Istituto/SAC/Area");
		TIPI_VARIAZIONE.put(STORNO_SPESA_ISTITUTI_DIVERSI,"Storno di Spesa tra diversi Istituti/SAC");
		TIPI_VARIAZIONE.put(STORNO_ENTRATA_ISTITUTI_DIVERSI,"Storno di Entrata tra diversi Istituti/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_POSITIVA_STESSO_ISTITUTO,"Variazione per maggiori Entrate/Spese all'interno dello stesso Istituto(Area)/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_NEGATIVA_STESSO_ISTITUTO,"Variazione per minori Entrate/Spese all'interno dello stesso Istituto(Area)/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI,"Variazione per maggiori Entrate/Spese tra diversi Istituti/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI,"Variazione per minori Entrate/Spese tra diversi Istituti/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_STESSO_ISTITUTO,"Variazione all'interno dello stesso Istituto/SAC");
		TIPI_VARIAZIONE.put(VARIAZIONE_ISTITUTI_DIVERSI,"Variazione tra diversi Istituti/SAC");
		TIPI_VARIAZIONE.put(PRELIEVO_FONDI,"Prelievo dai Fondi (non a quadratura)");
		TIPI_VARIAZIONE.put(RESTITUZIONE_FONDI,"Restituzione ai Fondi (non a quadratura)");
		TIPI_VARIAZIONE.put(VARIAZIONE_POSITIVA_FONDI,"Variazione per maggiori Entrate su Fondi");
		TIPI_VARIAZIONE.put(VARIAZIONE_NEGATIVA_FONDI,"Variazione per minori Entrate su Fondi");
		TIPI_VARIAZIONE.put(NON_DEFINITO,"Tipologia non definita");

		TIPI_APPROVAZIONE = new it.cnr.jada.util.OrderedHashtable();
		TIPI_APPROVAZIONE.put(APPROVAZIONE_CDS ,"Centro di Spesa");
		TIPI_APPROVAZIONE.put(APPROVAZIONE_ENTE,"Ente");
	}

	public Tipo_variazioneBulk() {
		super();
	}
	public Tipo_variazioneBulk(java.lang.Integer esercizio, java.lang.String cd_tipo_variazione) {
		super(esercizio, cd_tipo_variazione);
	}
	/**
	 * Inizializza vari attributi della classe.
	 * @param bp business process corrente
	 * @param context contesto dell'Action che e' stata generata
	 * @return OggettoBulk tipo_variazione con parametri inizializzati
	 */
	public OggettoBulk initializeForInsert(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) {
		setEsercizio(it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context));
		setFl_utilizzabile_area( new Boolean( false ) );
		setFl_utilizzabile_cds( new Boolean( false ) );
		setFl_utilizzabile_ente(new Boolean( false ) );
		setTi_approvazione(this.APPROVAZIONE_CDS);
		return this;
	}
	/**
      * Restituisce il <code>Dictionary</code> per la gestione dei tipi variazione
      *
      * @return java.util.Dictionary
      */
   public java.util.Dictionary getTi_tipologieKeys() {
     	return TIPI_VARIAZIONE;
   }
   public java.util.Dictionary getTi_approvazioneKeys() {
       	return TIPI_APPROVAZIONE;
   }
   /*
	* Serve per sapere se la variazione � di tipo Prelievo da Fondi
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 		PRELIEVO_FONDI
	*/
   public boolean isPrelievoFondi(){
	  return getTi_tipo_variazione() != null && 
	         getTi_tipo_variazione().equals(PRELIEVO_FONDI);
   }
   /*
	* Serve per sapere se la variazione � di tipo Prelievo da Fondi
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 		RESTITUZIONE_FONDI
	*/
   public boolean isRestituzioneFondi(){
	  return getTi_tipo_variazione() != null && 
	         getTi_tipo_variazione().equals(RESTITUZIONE_FONDI);
   }
   /*
	* Serve per sapere se � una variazione positiva o negativa su 
	* un Fondo di Spesa
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 	    VARIAZIONE_POSITIVA_FONDI
	*       VARIAZIONE_NEGATIVA_FONDI
	*/
   public boolean isVariazioneSuFondi(){
	  return isVariazionePositivaSuFondi() ||
	         isVariazioneNegativaSuFondi();
   }
   public boolean isVariazionePositivaSuFondi(){
	  return getTi_tipo_variazione() != null && 
	         getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_FONDI);
   }
   public boolean isVariazioneNegativaSuFondi(){
	  return getTi_tipo_variazione() != null && 
	         getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_FONDI);
   }
   /*
	* Serve per sapere se la variazione movimenta un Fondo di Spesa
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 	    PRELIEVO_FONDI
	*       RESTITUZIONE_FONDI
	* 	    VARIAZIONE_POSITIVA_FONDI
	*       VARIAZIONE_NEGATIVA_FONDI
	*/
   public boolean isMovimentoSuFondi(){
	  return isPrelievoFondi() || isRestituzioneFondi() || isVariazioneSuFondi();
   }
   /*
	* Serve per sapere se la variazione � di tipo Storno
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 		STORNO_ENTRATA_ISTITUTI_DIVERSI
	* 		STORNO_ENTRATA_STESSO_ISTITUTO
	* 		STORNO_SPESA_ISTITUTI_DIVERSI
	* 		STORNO_SPESA_STESSO_ISTITUTO
	*/
   public boolean isStorno(){
	  return getTi_tipo_variazione() != null && 
	  	     (getTi_tipo_variazione().equals(STORNO_ENTRATA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(STORNO_ENTRATA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(STORNO_SPESA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(STORNO_SPESA_STESSO_ISTITUTO));
   }
   /*
	* Serve per sapere se la variazione � di tipo Variazione
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 		STORNO_ENTRATA_ISTITUTI_DIVERSI
	* 		STORNO_ENTRATA_STESSO_ISTITUTO
	* 		STORNO_SPESA_ISTITUTI_DIVERSI
	* 		STORNO_SPESA_STESSO_ISTITUTO
	*/
   public boolean isVariazione(){
	  return getTi_tipo_variazione() != null && 
	  	     (getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_STESSO_ISTITUTO)  ||
			  getTi_tipo_variazione().equals(VARIAZIONE_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_STESSO_ISTITUTO));
   }
   /*
	* Serve per sapere se la variazione � di tipo Variazione
	* Ritorna un boolean con valore true se la tipologia della variazione �:
	* 		STORNO_ENTRATA_ISTITUTI_DIVERSI
	* 		STORNO_ENTRATA_STESSO_ISTITUTO
	* 		STORNO_SPESA_ISTITUTI_DIVERSI
	* 		STORNO_SPESA_STESSO_ISTITUTO
	*/
   public boolean isVariazioneInternaIstituto(){
	  return getTi_tipo_variazione() != null && 
	  	     (getTi_tipo_variazione().equals(STORNO_SPESA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(STORNO_ENTRATA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_STESSO_ISTITUTO));
   }
   /*
    * Serve per sapere se la variazione consente di effettuare interventi sulle voci di entrata
	* Ritorna un boolean con valore true se la tipologia della variazione �:
    * 	    STORNO_ENTRATA_STESSO_ISTITUTO
	* 	    STORNO_ENTRATA_ISTITUTI_DIVERSI
	*		VARIAZIONE_POSITIVA_STESSO_ISTITUTO
	*		VARIAZIONE_NEGATIVA_STESSO_ISTITUTO
	*		VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI
	*		VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI
	*		VARIAZIONE_STESSO_ISTITUTO
	*		VARIAZIONE_ISTITUTI_DIVERSI
	*		VARIAZIONE_POSITIVA_FONDI
	*	    VARIAZIONE_NEGATIVA_FONDI
	*		NON_DEFINITO
	*/
   public boolean isTipoVariazioneEntrata(){
	  return getTi_tipo_variazione() != null && 
	  	     (getTi_tipo_variazione().equals(STORNO_ENTRATA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(STORNO_ENTRATA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_STESSO_ISTITUTO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_ISTITUTI_DIVERSI) ||
			  getTi_tipo_variazione().equals(NON_DEFINITO) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_FONDI) ||
			  getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_FONDI));
   }
   /*
    * Serve per sapere se la variazione consente di effettuare interventi sulle voci di entrata
	* Ritorna un boolean con valore true se la tipologia della variazione �:
    * 	    STORNO_SPESA_STESSO_ISTITUTO
	* 	    STORNO_SPESA_ISTITUTI_DIVERSI
	*		VARIAZIONE_POSITIVA_STESSO_ISTITUTO
	*		VARIAZIONE_NEGATIVA_STESSO_ISTITUTO
	*		VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI
	*		VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI
	*		VARIAZIONE_STESSO_ISTITUTO
	*		VARIAZIONE_ISTITUTI_DIVERSI
	*		NON_DEFINITO
	*		PRELIEVO_FONDI
	*/
    public boolean isTipoVariazioneSpesa(){
	   return getTi_tipo_variazione() != null && 
	  	      (getTi_tipo_variazione().equals(STORNO_SPESA_STESSO_ISTITUTO) ||
			   getTi_tipo_variazione().equals(STORNO_SPESA_ISTITUTI_DIVERSI) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_STESSO_ISTITUTO) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_STESSO_ISTITUTO) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_POSITIVA_ISTITUTI_DIVERSI) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_NEGATIVA_ISTITUTI_DIVERSI) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_STESSO_ISTITUTO) ||
			   getTi_tipo_variazione().equals(VARIAZIONE_ISTITUTI_DIVERSI) ||
			   getTi_tipo_variazione().equals(NON_DEFINITO) ||
			   getTi_tipo_variazione().equals(PRELIEVO_FONDI));
    }
}