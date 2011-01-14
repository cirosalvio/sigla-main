/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 22/04/2010
 */
package it.cnr.contab.docamm00.docs.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class VIntrastatKey extends OggettoBulk implements KeyedPersistent {
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: V_INTRASTAT
	 **/
	public VIntrastatKey() {
		super();
	}
	
	// CD_CDS VARCHAR(30) NOT NULL (PK)
	private java.lang.String cd_cds;

	// CD_UNITA_ORGANIZZATIVA VARCHAR(30) NOT NULL (PK)
	private java.lang.String cd_unita_organizzativa;

	// ESERCIZIO DECIMAL(4,0) NOT NULL (PK)
	private java.lang.Integer esercizio;

	// PG_FATTURA DECIMAL(10,0) NOT NULL (PK)
	private java.lang.Long pg_fattura;

	private java.lang.Long pg_riga_intra;
	
	private java.lang.Long pgStorico;
	

public VIntrastatKey(java.lang.String cd_cds,java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.Long pg_fattura,java.lang.Long pg_riga_intra,java.lang.Long pgStorico) {
	super();
	this.cd_cds = cd_cds;
	this.cd_unita_organizzativa = cd_unita_organizzativa;
	this.esercizio = esercizio;
	this.pg_fattura = pg_fattura;
	this.pg_riga_intra =pg_riga_intra;
	this.pgStorico = pgStorico;
}
public boolean equalsByPrimaryKey(Object o) {
	if (this == o) return true;
	if (!(o instanceof VIntrastatKey)) return false;
	VIntrastatKey k = (VIntrastatKey)o;
	if(!compareKey(getCd_cds(),k.getCd_cds())) return false;
	if(!compareKey(getCd_unita_organizzativa(),k.getCd_unita_organizzativa())) return false;
	if(!compareKey(getEsercizio(),k.getEsercizio())) return false;
	if(!compareKey(getPg_fattura(),k.getPg_fattura())) return false;
	if(!compareKey(getPg_riga_intra(),k.getPg_riga_intra())) return false;
	if(!compareKey(getPgStorico(),k.getPgStorico())) return false;
	return true;
}
/* 
 * Getter dell'attributo cd_cds
 */
public java.lang.String getCd_cds() {
	return cd_cds;
}
/* 
 * Getter dell'attributo cd_unita_organizzativa
 */
public java.lang.String getCd_unita_organizzativa() {
	return cd_unita_organizzativa;
}
/* 
 * Getter dell'attributo esercizio
 */
public java.lang.Integer getEsercizio() {
	return esercizio;
}
/* 
 * Getter dell'attributo pg_fattura
 */
public java.lang.Long getPg_fattura() {
	return pg_fattura;
}
public int primaryKeyHashCode() {
	return
		calculateKeyHashCode(getCd_cds())+
		calculateKeyHashCode(getCd_unita_organizzativa())+
		calculateKeyHashCode(getEsercizio())+
		calculateKeyHashCode(getPg_fattura())+
		calculateKeyHashCode(getPg_riga_intra())+
		calculateKeyHashCode(getPgStorico());
}
/* 
 * Setter dell'attributo cd_cds
 */
public void setCd_cds(java.lang.String cd_cds) {
	this.cd_cds = cd_cds;
}
/* 
 * Setter dell'attributo cd_unita_organizzativa
 */
public void setCd_unita_organizzativa(java.lang.String cd_unita_organizzativa) {
	this.cd_unita_organizzativa = cd_unita_organizzativa;
}
/* 
 * Setter dell'attributo esercizio
 */
public void setEsercizio(java.lang.Integer esercizio) {
	this.esercizio = esercizio;
}
/* 
 * Setter dell'attributo pg_fattura
 */
public void setPg_fattura(java.lang.Long pg_fattura) {
	this.pg_fattura = pg_fattura;
}
public java.lang.Long getPg_riga_intra() {
	return pg_riga_intra;
}
public void setPg_riga_intra(java.lang.Long pg_riga_intra) {
	this.pg_riga_intra = pg_riga_intra;
}
public java.lang.Long getPgStorico() {
	return pgStorico;
}
public void setPgStorico(java.lang.Long pgStorico) {
	this.pgStorico = pgStorico;
}
}