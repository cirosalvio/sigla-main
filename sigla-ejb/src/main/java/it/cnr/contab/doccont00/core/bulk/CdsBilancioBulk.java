package it.cnr.contab.doccont00.core.bulk;

import java.math.*;

import it.cnr.jada.bulk.*;

import java.util.*;
/**
 * Gestione dei dati relativi alla situazione del bilancio delle entrate
 * dei centri di spesa.
 */
public class CdsBilancioBulk extends it.cnr.jada.bulk.OggettoBulk {
	private String cd_cds;
	private String ds_cds;
	private String tipoGestione;
	private Collection vociBilancioColl = Collections.EMPTY_LIST;

/**
 * Metodo con cui si ottiene il valore della variabile <code>cd_cds</code>.
 * @return cd_cds Il codice del centro di spesa
 */
public java.lang.String getCd_cds() {
	return cd_cds;
}
/**
 * Metodo con cui si ottiene il valore della variabile <code>ds_cds</code>.
 * @return ds_cds La descrizione del centro di spesa
 */
public java.lang.String getDs_cds() {
	return ds_cds;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'tipoGestione'
 *
 * @return Il valore della proprietÓ 'tipoGestione'
 */
public java.lang.String getTipoGestione() {
	return tipoGestione;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'totAccertatoMenoAccreditato'
 *
 * @return Il valore della proprietÓ 'totAccertatoMenoAccreditato'
 */
public BigDecimal getTotAccertatoMenoAccreditato() 
{
	java.math.BigDecimal tot = new java.math.BigDecimal(0);
	for (Iterator i = vociBilancioColl.iterator(); i.hasNext(); )
		tot = tot.add( ((V_sit_bil_cds_cnrBulk)i.next()).getIm_accertato_accreditato());
	return tot;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'totAccreditato'
 *
 * @return Il valore della proprietÓ 'totAccreditato'
 */
public BigDecimal getTotAccreditato() 
{
	java.math.BigDecimal tot = new java.math.BigDecimal(0);
	for (Iterator i = vociBilancioColl.iterator(); i.hasNext(); )
		tot = tot.add( ((V_sit_bil_cds_cnrBulk)i.next()).getIm_accreditato());
	return tot;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'totIncassatoMenoAccreditato'
 *
 * @return Il valore della proprietÓ 'totIncassatoMenoAccreditato'
 */
public BigDecimal getTotIncassatoMenoAccreditato() 
{
	java.math.BigDecimal tot = new java.math.BigDecimal(0);
	for (Iterator i = vociBilancioColl.iterator(); i.hasNext(); )
		tot = tot.add( ((V_sit_bil_cds_cnrBulk)i.next()).getIm_incassato_accreditato());
	return tot;
}
/**
 * Metodo con cui si ottiene il valore della variabile <code>vociBilancioColl</code>.
 * @return vociBilancioColl java.util.Collection
 */
public java.util.Collection getVociBilancioColl() {
	return vociBilancioColl;
}
/**
 * Inizializza l'Oggetto Bulk per la ricerca.
 * @param bp Il Business Process in uso
 * @param context Il contesto dell'azione
 * @return OggettoBulk L'oggetto bulk inizializzato
 */
public OggettoBulk initializeForSearch(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) 
{
	super.initializeForSearch( bp, context);
	tipoGestione = ((it.cnr.contab.doccont00.bp.ViewBilancioCdSBP)bp).getTipoGestione();
	setCd_cds(((it.cnr.contab.doccont00.bp.ViewBilancioCdSBP)bp).getCd_cds());
	return this;
}
/**
 * Metodo con cui si definisce il valore della variabile <code>cd_cds</code>.
 * @param newCd_cds Il codice del centro di spesa
 */
public void setCd_cds(java.lang.String newCd_cds) {
	cd_cds = newCd_cds;
}
/**
 * Metodo con cui si definisce il valore della variabile <code>ds_cds</code>.
 * @param newDs_cds La descrizione del centro di spesa
 */
public void setDs_cds(java.lang.String newDs_cds) {
	ds_cds = newDs_cds;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della proprietÓ 'tipoGestione'
 *
 * @param newTipoGestione	Il valore da assegnare a 'tipoGestione'
 */
public void setTipoGestione(java.lang.String newTipoGestione) {
	tipoGestione = newTipoGestione;
}
/**
 * Metodo con cui si definisce il valore della variabile <code>vociBilancioColl</code>.
 * @param newVociBilancioColl java.util.Collection
 */
public void setVociBilancioColl(java.util.Collection newVociBilancioColl) {
	vociBilancioColl = newVociBilancioColl;
}
}
