package it.cnr.contab.compensi00.tabrif.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Tipo_trattamentoBase extends Tipo_trattamentoKey implements Keyed {
	// DS_TI_TRATTAMENTO VARCHAR(100) NOT NULL
	private java.lang.String ds_ti_trattamento;

	// DT_FIN_VALIDITA TIMESTAMP
	private java.sql.Timestamp dt_fin_validita;

	// FL_DEFAULT_CONGUAGLIO CHAR(1) NOT NULL
	private java.lang.Boolean fl_default_conguaglio;

	// FL_DETRAZIONI_DIPENDENTE CHAR(1) NOT NULL
	private java.lang.Boolean fl_detrazioni_dipendente;

	// FL_DETRAZIONI_FAMILIARI CHAR(1) NOT NULL
	private java.lang.Boolean fl_detrazioni_familiari;

	// FL_DIARIA CHAR(1) NOT NULL
	private java.lang.Boolean fl_diaria;

	// FL_IRPEF_ANNUALIZZATA CHAR(1) NOT NULL
	private java.lang.Boolean fl_irpef_annualizzata;

	// FL_REGISTRA_FATTURA CHAR(1) NOT NULL
	private java.lang.Boolean fl_registra_fattura;

	// FL_SENZA_CALCOLI CHAR(1) NOT NULL
	private java.lang.Boolean fl_senza_calcoli;

	// FL_SOGGETTO_CONGUAGLIO CHAR(1) NOT NULL
	private java.lang.Boolean fl_soggetto_conguaglio;

	// FL_TASSAZIONE_SEPARATA CHAR(1) NOT NULL
	private java.lang.Boolean fl_tassazione_separata;

	// TI_ANAGRAFICO CHAR(1) NOT NULL
	private java.lang.String ti_anagrafico;

	// TI_COMMERCIALE CHAR(1) NOT NULL
	private java.lang.String ti_commerciale;

	// FL_AGEVOLAZIONI_CERVELLI CHAR(1) NOT NULL
	private java.lang.Boolean fl_agevolazioni_cervelli;

	// fl_utilizzabile_art35 CHAR(1) NOT NULL
	private java.lang.Boolean fl_utilizzabile_art35;
	
	// fl_anno_prec CHAR(1) NOT NULL
	private java.lang.Boolean fl_anno_prec;

	// FL_DETRAZIONI_ALTRE CHAR(1) NOT NULL
	private java.lang.Boolean fl_detrazioni_altre;

	// FL_INCARICO CHAR(1) NOT NULL
	private java.lang.Boolean fl_incarico;
	
	// TIPO_RAPP_IMPIEGO CHAR(3) NULL
	private java.lang.String tipo_rapp_impiego;
	
public Tipo_trattamentoBase() {
	super();
}
public Tipo_trattamentoBase(java.lang.String cd_trattamento,java.sql.Timestamp dt_ini_validita) {
	super(cd_trattamento,dt_ini_validita);
}
/* 
 * Getter dell'attributo ds_ti_trattamento
 */
public java.lang.String getDs_ti_trattamento() {
	return ds_ti_trattamento;
}
/* 
 * Getter dell'attributo dt_fin_validita
 */
public java.sql.Timestamp getDt_fin_validita() {
	return dt_fin_validita;
}
/* 
 * Getter dell'attributo fl_default_conguaglio
 */
public java.lang.Boolean getFl_default_conguaglio() {
	return fl_default_conguaglio;
}
/* 
 * Getter dell'attributo fl_detrazioni_dipendente
 */
public java.lang.Boolean getFl_detrazioni_dipendente() {
	return fl_detrazioni_dipendente;
}
/* 
 * Getter dell'attributo fl_detrazioni_familiari
 */
public java.lang.Boolean getFl_detrazioni_familiari() {
	return fl_detrazioni_familiari;
}
/* 
 * Getter dell'attributo fl_diaria
 */
public java.lang.Boolean getFl_diaria() {
	return fl_diaria;
}
/* 
 * Getter dell'attributo fl_irpef_annualizzata
 */
public java.lang.Boolean getFl_irpef_annualizzata() {
	return fl_irpef_annualizzata;
}
/* 
 * Getter dell'attributo fl_registra_fattura
 */
public java.lang.Boolean getFl_registra_fattura() {
	return fl_registra_fattura;
}
/* 
 * Getter dell'attributo fl_senza_calcoli
 */
public java.lang.Boolean getFl_senza_calcoli() {
	return fl_senza_calcoli;
}
/* 
 * Getter dell'attributo fl_soggetto_conguaglio
 */
public java.lang.Boolean getFl_soggetto_conguaglio() {
	return fl_soggetto_conguaglio;
}
/* 
 * Getter dell'attributo fl_tassazione_separata
 */
public java.lang.Boolean getFl_tassazione_separata() {
	return fl_tassazione_separata;
}
/* 
 * Getter dell'attributo ti_anagrafico
 */
public java.lang.String getTi_anagrafico() {
	return ti_anagrafico;
}
/* 
 * Getter dell'attributo ti_commerciale
 */
public java.lang.String getTi_commerciale() {
	return ti_commerciale;
}
/* 
 * Setter dell'attributo ds_ti_trattamento
 */
public void setDs_ti_trattamento(java.lang.String ds_ti_trattamento) {
	this.ds_ti_trattamento = ds_ti_trattamento;
}
/* 
 * Setter dell'attributo dt_fin_validita
 */
public void setDt_fin_validita(java.sql.Timestamp dt_fin_validita) {
	this.dt_fin_validita = dt_fin_validita;
}
/* 
 * Setter dell'attributo fl_default_conguaglio
 */
public void setFl_default_conguaglio(java.lang.Boolean fl_default_conguaglio) {
	this.fl_default_conguaglio = fl_default_conguaglio;
}
/* 
 * Setter dell'attributo fl_detrazioni_dipendente
 */
public void setFl_detrazioni_dipendente(java.lang.Boolean fl_detrazioni_dipendente) {
	this.fl_detrazioni_dipendente = fl_detrazioni_dipendente;
}
/* 
 * Setter dell'attributo fl_detrazioni_familiari
 */
public void setFl_detrazioni_familiari(java.lang.Boolean fl_detrazioni_familiari) {
	this.fl_detrazioni_familiari = fl_detrazioni_familiari;
}
/* 
 * Setter dell'attributo fl_diaria
 */
public void setFl_diaria(java.lang.Boolean fl_diaria) {
	this.fl_diaria = fl_diaria;
}
/* 
 * Setter dell'attributo fl_irpef_annualizzata
 */
public void setFl_irpef_annualizzata(java.lang.Boolean fl_irpef_annualizzata) {
	this.fl_irpef_annualizzata = fl_irpef_annualizzata;
}
/* 
 * Setter dell'attributo fl_registra_fattura
 */
public void setFl_registra_fattura(java.lang.Boolean fl_registra_fattura) {
	this.fl_registra_fattura = fl_registra_fattura;
}
/* 
 * Setter dell'attributo fl_senza_calcoli
 */
public void setFl_senza_calcoli(java.lang.Boolean fl_senza_calcoli) {
	this.fl_senza_calcoli = fl_senza_calcoli;
}
/* 
 * Setter dell'attributo fl_soggetto_conguaglio
 */
public void setFl_soggetto_conguaglio(java.lang.Boolean fl_soggetto_conguaglio) {
	this.fl_soggetto_conguaglio = fl_soggetto_conguaglio;
}
/* 
 * Setter dell'attributo fl_tassazione_separata
 */
public void setFl_tassazione_separata(java.lang.Boolean fl_tassazione_separata) {
	this.fl_tassazione_separata = fl_tassazione_separata;
}
/* 
 * Setter dell'attributo ti_anagrafico
 */
public void setTi_anagrafico(java.lang.String ti_anagrafico) {
	this.ti_anagrafico = ti_anagrafico;
}
/* 
 * Setter dell'attributo ti_commerciale
 */
public void setTi_commerciale(java.lang.String ti_commerciale) {
	this.ti_commerciale = ti_commerciale;
}
public java.lang.Boolean getFl_agevolazioni_cervelli() {
	return fl_agevolazioni_cervelli;
}
public void setFl_agevolazioni_cervelli(
		java.lang.Boolean fl_agevolazioni_cervelli) {
	this.fl_agevolazioni_cervelli = fl_agevolazioni_cervelli;
}
public java.lang.Boolean getFl_utilizzabile_art35() {
	return fl_utilizzabile_art35;
}
public void setFl_utilizzabile_art35(java.lang.Boolean fl_utilizzabile_art35) {
	this.fl_utilizzabile_art35 = fl_utilizzabile_art35;
}
public java.lang.Boolean getFl_anno_prec() {
	return fl_anno_prec;
}
public void setFl_anno_prec(java.lang.Boolean fl_anno_prec) {
	this.fl_anno_prec = fl_anno_prec;
}
public java.lang.Boolean getFl_detrazioni_altre() {
	return fl_detrazioni_altre;
}
public void setFl_detrazioni_altre(java.lang.Boolean fl_detrazioni_altre) {
	this.fl_detrazioni_altre = fl_detrazioni_altre;
}
public java.lang.Boolean getFl_incarico() {
	return fl_incarico;
}
public void setFl_incarico(java.lang.Boolean fl_incarico) {
	this.fl_incarico = fl_incarico;
}
public java.lang.String getTipo_rapp_impiego() {
	return tipo_rapp_impiego;
}
public void setTipo_rapp_impiego(java.lang.String tipo_rapp_impiego) {
	this.tipo_rapp_impiego = tipo_rapp_impiego;
}
}
