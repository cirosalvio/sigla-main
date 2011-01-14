package it.cnr.contab.docamm00.docs.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Fattura_passiva_rigaKey extends OggettoBulk implements KeyedPersistent {
	// CD_CDS VARCHAR(30) NOT NULL (PK)
	private java.lang.String cd_cds;

	// CD_UNITA_ORGANIZZATIVA VARCHAR(30) NOT NULL (PK)
	private java.lang.String cd_unita_organizzativa;

	// ESERCIZIO DECIMAL(4,0) NOT NULL (PK)
	private java.lang.Integer esercizio;

	// PG_FATTURA_PASSIVA DECIMAL(10,0) NOT NULL (PK)
	private java.lang.Long pg_fattura_passiva;

	// PROGRESSIVO_RIGA DECIMAL(10,0) NOT NULL (PK)
	private java.lang.Long progressivo_riga;

public Fattura_passiva_rigaKey() {
	super();
}
public Fattura_passiva_rigaKey(java.lang.String cd_cds,java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.Long pg_fattura_passiva,java.lang.Long progressivo_riga) {
	this.cd_cds = cd_cds;
	this.cd_unita_organizzativa = cd_unita_organizzativa;
	this.esercizio = esercizio;
	this.pg_fattura_passiva = pg_fattura_passiva;
	this.progressivo_riga = progressivo_riga;
}
public boolean equalsByPrimaryKey(Object o) {
	if (this == o) return true;
	if (!(o instanceof Fattura_passiva_rigaKey)) return false;
	Fattura_passiva_rigaKey k = (Fattura_passiva_rigaKey)o;
	if(!compareKey(getCd_cds(),k.getCd_cds())) return false;
	if(!compareKey(getCd_unita_organizzativa(),k.getCd_unita_organizzativa())) return false;
	if(!compareKey(getEsercizio(),k.getEsercizio())) return false;
	if(!compareKey(getPg_fattura_passiva(),k.getPg_fattura_passiva())) return false;
	if(!compareKey(getProgressivo_riga(),k.getProgressivo_riga())) return false;
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
 * Getter dell'attributo pg_fattura_passiva
 */
public java.lang.Long getPg_fattura_passiva() {
	return pg_fattura_passiva;
}
/* 
 * Getter dell'attributo progressivo_riga
 */
public java.lang.Long getProgressivo_riga() {
	return progressivo_riga;
}
public int primaryKeyHashCode() {
	return
		calculateKeyHashCode(getCd_cds())+
		calculateKeyHashCode(getCd_unita_organizzativa())+
		calculateKeyHashCode(getEsercizio())+
		calculateKeyHashCode(getPg_fattura_passiva())+
		calculateKeyHashCode(getProgressivo_riga());
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
 * Setter dell'attributo pg_fattura_passiva
 */
public void setPg_fattura_passiva(java.lang.Long pg_fattura_passiva) {
	this.pg_fattura_passiva = pg_fattura_passiva;
}
/* 
 * Setter dell'attributo progressivo_riga
 */
public void setProgressivo_riga(java.lang.Long progressivo_riga) {
	this.progressivo_riga = progressivo_riga;
}
}
