package it.cnr.contab.doccont00.core.bulk;

import it.cnr.jada.bulk.*;

import java.util.*;

public class V_obbligazioneBulk extends V_obbligazioneBase {
	protected java.math.BigDecimal im_da_trasferire = new java.math.BigDecimal(0);
	protected Integer priorita;
	protected int nrImpegni;

	public final static Dictionary competenzaResiduoKeys = MandatoBulk.competenzaResiduoKeys;
	

public V_obbligazioneBulk() {
	super();
}
public V_obbligazioneBulk( String cd_cds, Integer esercizio, Integer esercizio_originale, Long pg_obbligazione, Long pg_obbligazione_scadenzario ) 
{	super( cd_cds, esercizio, esercizio_originale, pg_obbligazione, pg_obbligazione_scadenzario);
	
}
/**
 * @return java.util.Dictionary
 */
public java.util.Dictionary getCompetenzaResiduoKeys() {
	return competenzaResiduoKeys;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'im_da_trasferire'
 *
 * @return Il valore della proprietÓ 'im_da_trasferire'
 */
public java.math.BigDecimal getIm_da_trasferire() {
	return im_da_trasferire;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'im_disponibile'
 *
 * @return Il valore della proprietÓ 'im_disponibile'
 */
public java.math.BigDecimal getIm_disponibile() {
	if ( getIm_scadenza() != null && getIm_associato_doc_contabile() != null )
		return getIm_scadenza().subtract(getIm_associato_doc_contabile());
	return null;	
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'nrImpegni'
 *
 * @return Il valore della proprietÓ 'nrImpegni'
 */
public int getNrImpegni() {
	return nrImpegni;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'priorita'
 *
 * @return Il valore della proprietÓ 'priorita'
 */
public java.lang.Integer getPriorita() {
	return priorita;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'prioritaKeys'
 *
 * @return Il valore della proprietÓ 'prioritaKeys'
 */
public java.util.Dictionary getPrioritaKeys() {
	it.cnr.jada.util.OrderedHashtable prioritaKeys = new it.cnr.jada.util.OrderedHashtable();
	for ( int i = 1; i <= nrImpegni; i ++ )
		prioritaKeys.put( new Integer( i ),new Integer( i ) );
	return prioritaKeys;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'ti_competenza_residuo'
 *
 * @return Il valore della proprietÓ 'ti_competenza_residuo'
 */
public String getTi_competenza_residuo() 
{
	if ( isCompetenza() )
		return MandatoBulk.TIPO_COMPETENZA;
	if ( getCd_tipo_documento_cont().equals( Numerazione_doc_contBulk.TIPO_IMP_RES ) ||
	 	 getCd_tipo_documento_cont().equals( Numerazione_doc_contBulk.TIPO_OBB_RES ) ||
	 	 getCd_tipo_documento_cont().equals( Numerazione_doc_contBulk.TIPO_OBB_RES_IMPROPRIA ) )
		return MandatoBulk.TIPO_RESIDUO;		
	return null;
}
/**
 * <!-- @TODO: da completare -->
 * Restituisce il valore della proprietÓ 'competenza'
 *
 * @return Il valore della proprietÓ 'competenza'
 */
public boolean isCompetenza()
{
	return  getCd_tipo_documento_cont().equals( Numerazione_doc_contBulk.TIPO_IMP ) ||
	        getCd_tipo_documento_cont().equals( Numerazione_doc_contBulk.TIPO_OBB );
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param impegniColl	
 * @return 
 */
public List ordinaPerPriorita( List impegniColl ) 
{
	// riordino la lista degli impegni per priorita
	
	Collections.sort(impegniColl,new Comparator() {	

		public int compare(Object o1, Object o2) 
		{
			V_obbligazioneBulk i1 = (V_obbligazioneBulk) o1;
			V_obbligazioneBulk i2 = (V_obbligazioneBulk) o2;
			
			return i1.getPriorita().compareTo( i2.getPriorita());
		}
		public boolean equals(Object o)  
		{
			return (getPriorita() == ((V_obbligazioneBulk)o).getPriorita());
		}
	});

	return impegniColl;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della proprietÓ 'im_da_trasferire'
 *
 * @param newIm_da_trasferire	Il valore da assegnare a 'im_da_trasferire'
 */
public void setIm_da_trasferire(java.math.BigDecimal newIm_da_trasferire) {
	im_da_trasferire = newIm_da_trasferire;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della proprietÓ 'nrImpegni'
 *
 * @param newNrImpegni	Il valore da assegnare a 'nrImpegni'
 */
public void setNrImpegni(int newNrImpegni) {
	nrImpegni = newNrImpegni;
}
/**
 * <!-- @TODO: da completare -->
 * Imposta il valore della proprietÓ 'priorita'
 *
 * @param newPriorita	Il valore da assegnare a 'priorita'
 */
public void setPriorita(java.lang.Integer newPriorita) {
	priorita = newPriorita;
}
/**
 * Metodo con cui si verifica la validitÓ di alcuni campi, mediante un 
 * controllo sintattico o contestuale.
 */
public void validate() throws ValidationException {
	super.validate();
	if ( im_da_trasferire == null )
		throw new ValidationException( "Impegno " + getEsercizio_originale() + "/" + getPg_obbligazione() + ":  e' necessario specificare un importo per ogni impegno selezionato!" );
	if ( im_da_trasferire.compareTo( new java.math.BigDecimal(0) ) <= 0 )
		throw new ValidationException( "Impegno " + getEsercizio_originale() + "/" + getPg_obbligazione() + ":  e' necessario specificare un importo maggiore di zero per ogni impegno selezionato!" );	
	if ( getIm_disponibile().compareTo( im_da_trasferire ) < 0 )
			throw new ValidationException( "Impegno " + getEsercizio_originale() + "/" + getPg_obbligazione() + ": non e' possibile trasferire un importo superiore all'importo disponibile!" );
}
}
