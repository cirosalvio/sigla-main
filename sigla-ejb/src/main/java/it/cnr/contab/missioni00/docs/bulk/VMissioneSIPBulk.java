/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 12/06/2008
 */
package it.cnr.contab.missioni00.docs.bulk;
import it.cnr.contab.missioni00.docs.bulk.VMissioneSIPKey;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.Keyed;
import it.cnr.jada.util.action.CRUDBP;
public class VMissioneSIPBulk extends VMissioneSIPKey implements Keyed {
	public VMissioneSIPBulk() {
		super();
	}
	public VMissioneSIPBulk(java.lang.String cd_cds,java.lang.String cd_unita_organizzativa,java.lang.Integer esercizio,java.lang.Long pg_missione) {
		super(cd_cds,cd_unita_organizzativa,esercizio,pg_missione);
	}
//  ESERCIZIO DECIMAL(4,0) NOT NULL
	private java.lang.Integer esercizio;
 
//    CD_CDS VARCHAR(30) NOT NULL
	private java.lang.String cd_cds;
 
//    CD_UNITA_ORGANIZZATIVA VARCHAR(30) NOT NULL
	private java.lang.String cd_unita_organizzativa;
 
//    CD_TERZO DECIMAL(8,0) NOT NULL
	private java.lang.Integer cd_terzo;
 
//    DENOMINAZIONE VARCHAR(101)
	private java.lang.String denominazione;
 
//    PARTITA_IVA VARCHAR(20)
	private java.lang.String partita_iva;
 
//    CODICE_FISCALE VARCHAR(20)
	private java.lang.String codice_fiscale;
 
//    TIPO CHAR(1) NOT NULL
	private java.lang.String tipo;
 
//    MATRICOLA DECIMAL(22,0)
	private java.lang.Long matricola;
 
//    DS_MISSIONE VARCHAR(300) NOT NULL
	private java.lang.String ds_missione;
 
//    IM_TOTALE_MISSIONE DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_totale_missione;
 
//    PG_MISSIONE DECIMAL(10,0) NOT NULL
	private java.lang.Long pg_missione;
 
//    CD_ELEMENTO_VOCE VARCHAR(20) NOT NULL
	private java.lang.String cd_elemento_voce;
 
//    CD_CENTRO_RESPONSABILITA VARCHAR(30) NOT NULL
	private java.lang.String cd_centro_responsabilita;
 
//    GAE VARCHAR(10) NOT NULL
	private java.lang.String gae;
 
//    DATA_PAGAMENTO TIMESTAMP(7)
	private java.sql.Timestamp dt_pagamento;


	private java.sql.Timestamp dt_inizio_missione;
	
	private java.sql.Timestamp dt_fine_missione;
	
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.String getCd_cds() {
		return cd_cds;
	}
	public void setCd_cds(java.lang.String cd_cds)  {
		this.cd_cds=cd_cds;
	}
	public java.lang.String getCd_unita_organizzativa() {
		return cd_unita_organizzativa;
	}
	public void setCd_unita_organizzativa(java.lang.String cd_unita_organizzativa)  {
		this.cd_unita_organizzativa=cd_unita_organizzativa;
	}
	public java.lang.Integer getCd_terzo() {
		return cd_terzo;
	}
	public void setCd_terzo(java.lang.Integer cd_terzo)  {
		this.cd_terzo=cd_terzo;
	}
	public java.lang.String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(java.lang.String denominazione)  {
		this.denominazione=denominazione;
	}
	public java.lang.String getPartita_iva() {
		return partita_iva;
	}
	public void setPartita_iva(java.lang.String partita_iva)  {
		this.partita_iva=partita_iva;
	}
	public java.lang.String getCodice_fiscale() {
		return codice_fiscale;
	}
	public void setCodice_fiscale(java.lang.String codice_fiscale)  {
		this.codice_fiscale=codice_fiscale;
	}
	public java.lang.String getTipo() {
		return tipo;
	}
	public void setTipo(java.lang.String tipo)  {
		this.tipo=tipo;
	}
	public java.lang.Long getMatricola() {
		return matricola;
	}
	public void setMatricola(java.lang.Long matricola)  {
		this.matricola=matricola;
	}
	public java.lang.String getDs_missione() {
		return ds_missione;
	}
	public void setDs_missione(java.lang.String ds_missione)  {
		this.ds_missione=ds_missione;
	}
	public java.math.BigDecimal getIm_totale_missione() {
		return im_totale_missione;
	}
	public void setIm_totale_missione(java.math.BigDecimal im_totale_missione)  {
		this.im_totale_missione=im_totale_missione;
	}
	public java.lang.Long getPg_missione() {
		return pg_missione;
	}
	public void setPg_missione(java.lang.Long pg_missione)  {
		this.pg_missione=pg_missione;
	}
	public java.lang.String getCd_elemento_voce() {
		return cd_elemento_voce;
	}
	public void setCd_elemento_voce(java.lang.String cd_elemento_voce)  {
		this.cd_elemento_voce=cd_elemento_voce;
	}
	public java.lang.String getCd_centro_responsabilita() {
		return cd_centro_responsabilita;
	}
	public void setCd_centro_responsabilita(java.lang.String cd_centro_responsabilita)  {
		this.cd_centro_responsabilita=cd_centro_responsabilita;
	}
	public java.lang.String getGae() {
		return gae;
	}
	public void setGae(java.lang.String gae)  {
		this.gae=gae;
	}
	public java.sql.Timestamp getDt_pagamento() {
		return dt_pagamento;
	}
	public void setDt_pagamento(java.sql.Timestamp dt_pagamento)  {
		this.dt_pagamento=dt_pagamento;
	}
	public java.sql.Timestamp getDt_inizio_missione() {
		return dt_inizio_missione;
	}
	public void setDt_inizio_missione(java.sql.Timestamp dt_inizio_missione) {
		this.dt_inizio_missione = dt_inizio_missione;
	}
	public java.sql.Timestamp getDt_fine_missione() {
		return dt_fine_missione;
	}
	public void setDt_fine_missione(java.sql.Timestamp dt_fine_missione) {
		this.dt_fine_missione = dt_fine_missione;
	}
}