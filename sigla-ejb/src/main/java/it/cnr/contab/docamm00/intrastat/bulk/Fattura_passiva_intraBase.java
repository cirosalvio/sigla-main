package it.cnr.contab.docamm00.intrastat.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Fattura_passiva_intraBase extends Fattura_passiva_intraKey implements Keyed {
	// AMMONTARE_DIVISA DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal ammontare_divisa;

	// AMMONTARE_EURO DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal ammontare_euro;

	private java.lang.Integer esercizio_cond_consegna;
	
	private java.lang.String cd_incoterm;

	private java.lang.Integer esercizio_mod_trasporto;
	
	private java.lang.String cd_modalita_trasporto;

	private java.lang.Long id_natura_transazione;

	private java.lang.Long id_nomenclatura_combinata;

	// CD_PROVINCIA_DESTINAZIONE VARCHAR(10) NOT NULL
	private java.lang.String cd_provincia_destinazione;

	// DS_BENE VARCHAR(300)
	private java.lang.String ds_bene;

	private java.math.BigDecimal massa_netta;

	// PG_NAZIONE_ORIGINE DECIMAL(10,0) NOT NULL
	private java.lang.Long pg_nazione_origine;

	// PG_NAZIONE_PROVENIENZA DECIMAL(10,0) NOT NULL
	private java.lang.Long pg_nazione_provenienza;

	// UNITA_SUPPLEMENTARI DECIMAL(10,0) NOT NULL
	private java.lang.Long unita_supplementari;

	// VALORE_STATISTICO DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal valore_statistico;

	private java.lang.Long id_cpa;
	
	private java.lang.Integer esercizio_mod_erogazione;
	
	private java.lang.String cd_modalita_erogazione;

	private java.lang.Integer esercizio_mod_incasso;
	
	private java.lang.String cd_modalita_incasso;
	
	private java.lang.Boolean fl_inviato;
	
	private java.lang.Integer nr_protocollo;
	private java.lang.Integer nr_progressivo;
	

public Fattura_passiva_intraBase() {
	super();
}
public Fattura_passiva_intraBase(java.lang.String cd_cds,java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.Long pg_fattura_passiva,java.lang.Long pg_riga_intra) {
	super(cd_cds,cd_unita_organizzativa,esercizio,pg_fattura_passiva,pg_riga_intra);
}
/* 
 * Getter dell'attributo ammontare_divisa
 */
public java.math.BigDecimal getAmmontare_divisa() {
	return ammontare_divisa;
}
/* 
 * Getter dell'attributo ammontare_euro
 */
public java.math.BigDecimal getAmmontare_euro() {
	return ammontare_euro;
}
/* 
 * Getter dell'attributo cd_provincia_destinazione
 */
public java.lang.String getCd_provincia_destinazione() {
	return cd_provincia_destinazione;
}
/* 
 * Getter dell'attributo ds_bene
 */
public java.lang.String getDs_bene() {
	return ds_bene;
}

/* 
 * Getter dell'attributo pg_nazione_origine
 */
public java.lang.Long getPg_nazione_origine() {
	return pg_nazione_origine;
}
/* 
 * Getter dell'attributo pg_nazione_provenienza
 */
public java.lang.Long getPg_nazione_provenienza() {
	return pg_nazione_provenienza;
}

/* 
 * Getter dell'attributo valore_statistico
 */
public java.math.BigDecimal getValore_statistico() {
	return valore_statistico;
}
/* 
 * Setter dell'attributo ammontare_divisa
 */
public void setAmmontare_divisa(java.math.BigDecimal ammontare_divisa) {
	this.ammontare_divisa = ammontare_divisa;
}
/* 
 * Setter dell'attributo ammontare_euro
 */
public void setAmmontare_euro(java.math.BigDecimal ammontare_euro) {
	this.ammontare_euro = ammontare_euro;
}
/* 
 * Setter dell'attributo cd_provincia_destinazione
 */
public void setCd_provincia_destinazione(java.lang.String cd_provincia_destinazione) {
	this.cd_provincia_destinazione = cd_provincia_destinazione;
}
/* 
 * Setter dell'attributo ds_bene
 */
public void setDs_bene(java.lang.String ds_bene) {
	this.ds_bene = ds_bene;
}

/* 
 * Setter dell'attributo pg_nazione_origine
 */
public void setPg_nazione_origine(java.lang.Long pg_nazione_origine) {
	this.pg_nazione_origine = pg_nazione_origine;
}
/* 
 * Setter dell'attributo pg_nazione_provenienza
 */
public void setPg_nazione_provenienza(java.lang.Long pg_nazione_provenienza) {
	this.pg_nazione_provenienza = pg_nazione_provenienza;
}

/* 
 * Setter dell'attributo valore_statistico
 */
public void setValore_statistico(java.math.BigDecimal valore_statistico) {
	this.valore_statistico = valore_statistico;
}
public java.lang.Integer getEsercizio_cond_consegna() {
	return esercizio_cond_consegna;
}
public void setEsercizio_cond_consegna(java.lang.Integer esercizio_cond_consegna) {
	this.esercizio_cond_consegna = esercizio_cond_consegna;
}
public java.lang.String getCd_incoterm() {
	return cd_incoterm;
}
public void setCd_incoterm(java.lang.String cd_incoterm) {
	this.cd_incoterm = cd_incoterm;
}
public java.lang.Integer getEsercizio_mod_trasporto() {
	return esercizio_mod_trasporto;
}
public void setEsercizio_mod_trasporto(java.lang.Integer esercizio_mod_trasporto) {
	this.esercizio_mod_trasporto = esercizio_mod_trasporto;
}
public java.lang.Long getId_natura_transazione() {
	return id_natura_transazione;
}
public void setId_natura_transazione(java.lang.Long id_natura_transazione) {
	this.id_natura_transazione = id_natura_transazione;
}
public java.lang.Long getId_nomenclatura_combinata() {
	return id_nomenclatura_combinata;
}
public void setId_nomenclatura_combinata(
		java.lang.Long id_nomenclatura_combinata) {
	this.id_nomenclatura_combinata = id_nomenclatura_combinata;
}
public java.math.BigDecimal getMassa_netta() {
	return massa_netta;
}
public void setMassa_netta(java.math.BigDecimal massa_netta) {
	this.massa_netta = massa_netta;
}
public java.lang.Long getUnita_supplementari() {
	return unita_supplementari;
}
public void setUnita_supplementari(java.lang.Long unita_supplementari) {
	this.unita_supplementari = unita_supplementari;
}
public java.lang.String getCd_modalita_trasporto() {
	return cd_modalita_trasporto;
}
public void setCd_modalita_trasporto(java.lang.String cd_modalita_trasporto) {
	this.cd_modalita_trasporto = cd_modalita_trasporto;
}

public java.lang.Integer getEsercizio_mod_erogazione() {
	return esercizio_mod_erogazione;
}
public void setEsercizio_mod_erogazione(
		java.lang.Integer esercizio_mod_erogazione) {
	this.esercizio_mod_erogazione = esercizio_mod_erogazione;
}
public java.lang.String getCd_modalita_erogazione() {
	return cd_modalita_erogazione;
}
public void setCd_modalita_erogazione(java.lang.String cd_modalita_erogazione) {
	this.cd_modalita_erogazione = cd_modalita_erogazione;
}
public java.lang.Integer getEsercizio_mod_incasso() {
	return esercizio_mod_incasso;
}
public void setEsercizio_mod_incasso(java.lang.Integer esercizio_mod_incasso) {
	this.esercizio_mod_incasso = esercizio_mod_incasso;
}
public java.lang.String getCd_modalita_incasso() {
	return cd_modalita_incasso;
}
public void setCd_modalita_incasso(java.lang.String cd_modalita_incasso) {
	this.cd_modalita_incasso = cd_modalita_incasso;
}
public java.lang.Long getId_cpa() {
	return id_cpa;
}
public void setId_cpa(java.lang.Long id_cpa) {
	this.id_cpa = id_cpa;
}
public java.lang.Boolean getFl_inviato() {
	return fl_inviato;
}
public void setFl_inviato(java.lang.Boolean fl_inviato) {
	this.fl_inviato = fl_inviato;
}
public java.lang.Integer getNr_protocollo() {
	return nr_protocollo;
}
public void setNr_protocollo(java.lang.Integer nr_protocollo) {
	this.nr_protocollo = nr_protocollo;
}
public java.lang.Integer getNr_progressivo() {
	return nr_progressivo;
}
public void setNr_progressivo(java.lang.Integer nr_progressivo) {
	this.nr_progressivo = nr_progressivo;
}
}
