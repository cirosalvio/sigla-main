package it.cnr.contab.doccont00.intcass.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Distinta_cassiereBase extends Distinta_cassiereKey implements Keyed {

	// DT_EMISSIONE TIMESTAMP
	private java.sql.Timestamp dt_emissione;

	// DT_INVIO TIMESTAMP
	private java.sql.Timestamp dt_invio;

	// IM_MAN_INI_ACC DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_man_ini_acc;

	// IM_MAN_INI_PAG DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_man_ini_pag;

	// IM_MAN_INI_SOS DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_man_ini_sos;

	// IM_REV_INI_SOS DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_rev_ini_sos;

	// IM_REV_INI_TRA DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_rev_ini_tra;

	// IM_REV_INI_RIT DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_rev_ini_rit;


	// PG_DISTINTA_DEF DECIMAL(10,0)
	private java.lang.Long pg_distinta_def;

public Distinta_cassiereBase() {
	super();
}
public Distinta_cassiereBase(java.lang.String cd_cds,java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.Long pg_distinta) {
	super(cd_cds,cd_unita_organizzativa,esercizio,pg_distinta);
}
/* 
 * Getter dell'attributo dt_emissione
 */
public java.sql.Timestamp getDt_emissione() {
	return dt_emissione;
}
/* 
 * Getter dell'attributo dt_invio
 */
public java.sql.Timestamp getDt_invio() {
	return dt_invio;
}
/* 
 * Getter dell'attributo im_man_ini_acc
 */
public java.math.BigDecimal getIm_man_ini_acc() {
	return im_man_ini_acc;
}
/* 
 * Getter dell'attributo im_man_ini_pag
 */
public java.math.BigDecimal getIm_man_ini_pag() {
	return im_man_ini_pag;
}
/* 
 * Getter dell'attributo im_man_ini_sos
 */
public java.math.BigDecimal getIm_man_ini_sos() {
	return im_man_ini_sos;
}
/* 
 * Getter dell'attributo im_rev_ini_rit
 */
public java.math.BigDecimal getIm_rev_ini_rit() {
	return im_rev_ini_rit;
}
/* 
 * Getter dell'attributo im_rev_ini_sos
 */
public java.math.BigDecimal getIm_rev_ini_sos() {
	return im_rev_ini_sos;
}
/* 
 * Getter dell'attributo im_rev_ini_tra
 */
public java.math.BigDecimal getIm_rev_ini_tra() {
	return im_rev_ini_tra;
}
/* 
 * Getter dell'attributo pg_distinta_def
 */
public java.lang.Long getPg_distinta_def() {
	return pg_distinta_def;
}
/* 
 * Setter dell'attributo dt_emissione
 */
public void setDt_emissione(java.sql.Timestamp dt_emissione) {
	this.dt_emissione = dt_emissione;
}
/* 
 * Setter dell'attributo dt_invio
 */
public void setDt_invio(java.sql.Timestamp dt_invio) {
	this.dt_invio = dt_invio;
}
/* 
 * Setter dell'attributo im_man_ini_acc
 */
public void setIm_man_ini_acc(java.math.BigDecimal im_man_ini_acc) {
	this.im_man_ini_acc = im_man_ini_acc;
}
/* 
 * Setter dell'attributo im_man_ini_pag
 */
public void setIm_man_ini_pag(java.math.BigDecimal im_man_ini_pag) {
	this.im_man_ini_pag = im_man_ini_pag;
}
/* 
 * Setter dell'attributo im_man_ini_sos
 */
public void setIm_man_ini_sos(java.math.BigDecimal im_man_ini_sos) {
	this.im_man_ini_sos = im_man_ini_sos;
}
/* 
 * Setter dell'attributo im_rev_ini_rit
 */
public void setIm_rev_ini_rit(java.math.BigDecimal newIm_rev_ini_rit) {
	im_rev_ini_rit = newIm_rev_ini_rit;
}
/* 
 * Setter dell'attributo im_rev_ini_sos
 */
public void setIm_rev_ini_sos(java.math.BigDecimal im_rev_ini_sos) {
	this.im_rev_ini_sos = im_rev_ini_sos;
}
/* 
 * Setter dell'attributo im_rev_ini_tra
 */
public void setIm_rev_ini_tra(java.math.BigDecimal im_rev_ini_tra) {
	this.im_rev_ini_tra = im_rev_ini_tra;
}
/* 
 * Setter dell'attributo pg_distinta_def
 */
public void setPg_distinta_def(java.lang.Long pg_distinta_def) {
	this.pg_distinta_def = pg_distinta_def;
}
}
