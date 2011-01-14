package it.cnr.contab.pdg00.cdip.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Ass_cdp_uoBase extends Ass_cdp_uoKey implements Keyed {
	// PRC_UO_A1 DECIMAL(5,2)
	private java.math.BigDecimal prc_uo_a1;

	// PRC_UO_A2 DECIMAL(5,2)
	private java.math.BigDecimal prc_uo_a2;

	// PRC_UO_A3 DECIMAL(5,2)
	private java.math.BigDecimal prc_uo_a3;

	// STATO VARCHAR(5)
	private java.lang.String stato;

public Ass_cdp_uoBase() {
	super();
}
public Ass_cdp_uoBase(java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.String id_matricola,java.lang.Integer mese) {
	super(cd_unita_organizzativa,esercizio,id_matricola,mese);
}
/* 
 * Getter dell'attributo prc_uo_a1
 */
public java.math.BigDecimal getPrc_uo_a1() {
	return prc_uo_a1;
}
/* 
 * Getter dell'attributo prc_uo_a2
 */
public java.math.BigDecimal getPrc_uo_a2() {
	return prc_uo_a2;
}
/* 
 * Getter dell'attributo prc_uo_a3
 */
public java.math.BigDecimal getPrc_uo_a3() {
	return prc_uo_a3;
}
/* 
 * Getter dell'attributo stato
 */
public java.lang.String getStato() {
	return stato;
}
/* 
 * Setter dell'attributo prc_uo_a1
 */
public void setPrc_uo_a1(java.math.BigDecimal prc_uo_a1) {
	this.prc_uo_a1 = prc_uo_a1;
}
/* 
 * Setter dell'attributo prc_uo_a2
 */
public void setPrc_uo_a2(java.math.BigDecimal prc_uo_a2) {
	this.prc_uo_a2 = prc_uo_a2;
}
/* 
 * Setter dell'attributo prc_uo_a3
 */
public void setPrc_uo_a3(java.math.BigDecimal prc_uo_a3) {
	this.prc_uo_a3 = prc_uo_a3;
}
/* 
 * Setter dell'attributo stato
 */
public void setStato(java.lang.String stato) {
	this.stato = stato;
}
}
