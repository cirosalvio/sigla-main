/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 30/11/2009
 */
package it.cnr.contab.doccont00.intcass.bulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.Persistent;
import it.cnr.jada.util.action.CRUDBP;
import it.cnr.jada.persistency.Persistent;
public class V_cons_sospesiBulk extends OggettoBulk implements Persistent{
	
//  ESERCIZIO DECIMAL(4,0)
	private java.lang.Integer esercizio;
 
//    TI_ENTRATA_SPESA CHAR(1)
	private java.lang.String ti_entrata_spesa;
 
//    CD_CDS VARCHAR(30)
	private java.lang.String cd_cds;
 
//    CD_CDS_ORIGINE VARCHAR(30)
	private java.lang.String cd_cds_origine;
 
//    CD_SOSPESO VARCHAR(24)
	private java.lang.String cd_sospeso;
 
//    LIVELLO DECIMAL(22,0)
	private java.lang.Long livello;
 
//    DT_REGISTRAZIONE TIMESTAMP(7)
	private java.sql.Timestamp dt_registrazione;
 
//    DS_ANAGRAFICO VARCHAR(200)
	private java.lang.String ds_anagrafico;
 
//    CAUSALE VARCHAR(300)
	private java.lang.String causale;
 
//    TI_CC_BI CHAR(1)
	private java.lang.String ti_cc_bi;
 
//    DES_TI_CC_BI VARCHAR(14)
	private java.lang.String des_ti_cc_bi;
 
//    STATO_VALIDITA VARCHAR(9)
	private java.lang.String stato_validita;
 
//    DT_STORNO TIMESTAMP(7)
	private java.sql.Timestamp dt_storno;
 
//    IM_SOSPESO DECIMAL(22,0)
	private java.math.BigDecimal im_sospeso;
 
//    IM_ASSOCIATO DECIMAL(22,0)
	private java.math.BigDecimal im_associato;
 
//    IM_DA_ASSOCIARE DECIMAL(22,0)
	private java.math.BigDecimal im_da_associare;
 
//    DS_STATO_SOSPESO VARCHAR(35)
	private java.lang.String ds_stato_sospeso;
 
//    IM_ASS_MOD_1210 DECIMAL(22,0)
	private java.math.BigDecimal im_ass_mod_1210;
 
//    CD_SOSPESO_PADRE VARCHAR(24)
	private java.lang.String cd_sospeso_padre;
 
//    PG_MAN_REV DECIMAL(22,0)
	private java.lang.Long pg_man_rev;
 
	public V_cons_sospesiBulk() {
		super();
	}
	
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.String getTi_entrata_spesa() {
		return ti_entrata_spesa;
	}
	public void setTi_entrata_spesa(java.lang.String ti_entrata_spesa)  {
		this.ti_entrata_spesa=ti_entrata_spesa;
	}
	public java.lang.String getCd_cds() {
		return cd_cds;
	}
	public void setCd_cds(java.lang.String cd_cds)  {
		this.cd_cds=cd_cds;
	}
	public java.lang.String getCd_cds_origine() {
		return cd_cds_origine;
	}
	public void setCd_cds_origine(java.lang.String cd_cds_origine)  {
		this.cd_cds_origine=cd_cds_origine;
	}
	public java.lang.String getCd_sospeso() {
		return cd_sospeso;
	}
	public void setCd_sospeso(java.lang.String cd_sospeso)  {
		this.cd_sospeso=cd_sospeso;
	}
	public java.lang.Long getLivello() {
		return livello;
	}
	public void setLivello(java.lang.Long livello)  {
		this.livello=livello;
	}
	public java.sql.Timestamp getDt_registrazione() {
		return dt_registrazione;
	}
	public void setDt_registrazione(java.sql.Timestamp dt_registrazione)  {
		this.dt_registrazione=dt_registrazione;
	}
	public java.lang.String getDs_anagrafico() {
		return ds_anagrafico;
	}
	public void setDs_anagrafico(java.lang.String ds_anagrafico)  {
		this.ds_anagrafico=ds_anagrafico;
	}
	public java.lang.String getCausale() {
		return causale;
	}
	public void setCausale(java.lang.String causale)  {
		this.causale=causale;
	}
	public java.lang.String getTi_cc_bi() {
		return ti_cc_bi;
	}
	public void setTi_cc_bi(java.lang.String ti_cc_bi)  {
		this.ti_cc_bi=ti_cc_bi;
	}
	public java.lang.String getDes_ti_cc_bi() {
		return des_ti_cc_bi;
	}
	public void setDes_ti_cc_bi(java.lang.String des_ti_cc_bi)  {
		this.des_ti_cc_bi=des_ti_cc_bi;
	}
	public java.lang.String getStato_validita() {
		return stato_validita;
	}
	public void setStato_validita(java.lang.String stato_validita)  {
		this.stato_validita=stato_validita;
	}
	public java.sql.Timestamp getDt_storno() {
		return dt_storno;
	}
	public void setDt_storno(java.sql.Timestamp dt_storno)  {
		this.dt_storno=dt_storno;
	}
	public java.math.BigDecimal getIm_sospeso() {
		return im_sospeso;
	}
	public void setIm_sospeso(java.math.BigDecimal im_sospeso)  {
		this.im_sospeso=im_sospeso;
	}
	public java.math.BigDecimal getIm_associato() {
		return im_associato;
	}
	public void setIm_associato(java.math.BigDecimal im_associato)  {
		this.im_associato=im_associato;
	}
	public java.math.BigDecimal getIm_da_associare() {
		return im_da_associare;
	}
	public void setIm_da_associare(java.math.BigDecimal im_da_associare)  {
		this.im_da_associare=im_da_associare;
	}
	public java.lang.String getDs_stato_sospeso() {
		return ds_stato_sospeso;
	}
	public void setDs_stato_sospeso(java.lang.String ds_stato_sospeso)  {
		this.ds_stato_sospeso=ds_stato_sospeso;
	}
	public java.math.BigDecimal getIm_ass_mod_1210() {
		return im_ass_mod_1210;
	}
	public void setIm_ass_mod_1210(java.math.BigDecimal im_ass_mod_1210)  {
		this.im_ass_mod_1210=im_ass_mod_1210;
	}
	public java.lang.String getCd_sospeso_padre() {
		return cd_sospeso_padre;
	}
	public void setCd_sospeso_padre(java.lang.String cd_sospeso_padre)  {
		this.cd_sospeso_padre=cd_sospeso_padre;
	}
	public java.lang.Long getPg_man_rev() {
		return pg_man_rev;
	}
	public void setPg_man_rev(java.lang.Long pg_man_rev)  {
		this.pg_man_rev=pg_man_rev;
	}
}