package it.cnr.contab.doccont00.core.bulk;

import it.cnr.jada.bulk.*;

public class Mandato_rigaIBulk extends Mandato_rigaBulk {
	protected MandatoIBulk mandatoI;
	protected boolean flCancellazione = false;
	protected Long pg_lettera;
	protected String cd_sospeso;

public Mandato_rigaIBulk() {
	super();
}
public Mandato_rigaIBulk(java.lang.String cd_cds,java.lang.String cd_cds_doc_amm,java.lang.String cd_tipo_documento_amm,java.lang.String cd_uo_doc_amm,java.lang.Integer esercizio,java.lang.Integer esercizio_doc_amm,java.lang.Integer esercizio_obbligazione,java.lang.Long pg_doc_amm,java.lang.Long pg_mandato,java.lang.Integer esercizio_ori_obbligazione,java.lang.Long pg_obbligazione,java.lang.Long pg_obbligazione_scadenzario) 
{
	super(cd_cds, cd_cds_doc_amm, cd_tipo_documento_amm, cd_uo_doc_amm, esercizio, esercizio_doc_amm, esercizio_obbligazione, pg_doc_amm, pg_mandato, esercizio_ori_obbligazione, pg_obbligazione, pg_obbligazione_scadenzario);
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @return 
 */
public OggettoBulk asDocumentoPassivoBulk() 
{
	if ( getCd_tipo_documento_amm().equals( it.cnr.contab.docamm00.docs.bulk.Numerazione_doc_ammBulk.TIPO_FATTURA_PASSIVA) )
		return new it.cnr.contab.docamm00.docs.bulk.Fattura_passiva_IBulk( 
				getCd_cds(), 
				getCd_uo_doc_amm(), 
				getEsercizio_doc_amm(),
				getPg_doc_amm());
	else if ( getCd_tipo_documento_amm().equals( it.cnr.contab.docamm00.docs.bulk.Numerazione_doc_ammBulk.TIPO_DOC_GENERICO_S) )
		return new it.cnr.contab.docamm00.docs.bulk.Documento_genericoBulk( 
				getCd_cds(),
				getCd_tipo_documento_amm(),
				getCd_uo_doc_amm(), 
				getEsercizio_doc_amm(),
				getPg_doc_amm());

	
	else
		return null;
}
/**
 * Insert the method's description here.
 * Creation date: (16/09/2002 12.25.18)
 * @return java.lang.String
 */
public java.lang.String getCd_sospeso() {
	return cd_sospeso;
}
public MandatoBulk getMandato() 
{
	return mandatoI;
}	
/**
 * @return it.cnr.contab.doccont00.core.bulk.MandatoIBulk
 */
public MandatoIBulk getMandatoI() {
	return mandatoI;
}
/**
 * Insert the method's description here.
 * Creation date: (16/09/2002 10.22.43)
 * @return java.lang.Long
 */
public java.lang.Long getPg_lettera() {
	return pg_lettera;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'flCancellazione'
 *
 * @return Il valore della proprietÓ 'flCancellazione'
 */
public boolean isFlCancellazione() {
	return flCancellazione;
}
/**
 * Insert the method's description here.
 * Creation date: (16/09/2002 12.25.18)
 * @param newCd_sospeso java.lang.String
 */
public void setCd_sospeso(java.lang.String newCd_sospeso) {
	cd_sospeso = newCd_sospeso;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della proprietÓ 'flCancellazione'
 *
 * @param newFlCancellazione	Il valore da assegnare a 'flCancellazione'
 */
public void setFlCancellazione(boolean newFlCancellazione) {
	flCancellazione = newFlCancellazione;
}
public void setMandato(MandatoBulk newMandato)
{
	setMandatoI( (MandatoIBulk) newMandato);
}	
/**
 * @param newMandatoI it.cnr.contab.doccont00.core.bulk.MandatoIBulk
 */
public void setMandatoI(MandatoIBulk newMandatoI) {
	mandatoI = newMandatoI;
}
/**
 * Insert the method's description here.
 * Creation date: (16/09/2002 10.22.43)
 * @param newPg_lettera java.lang.Long
 */
public void setPg_lettera(java.lang.Long newPg_lettera) {
	pg_lettera = newPg_lettera;
}
}
