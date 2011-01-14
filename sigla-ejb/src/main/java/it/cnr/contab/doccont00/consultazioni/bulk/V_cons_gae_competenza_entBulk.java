/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 12/10/2007
 */
package it.cnr.contab.doccont00.consultazioni.bulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.Persistent;
import it.cnr.jada.util.action.CRUDBP;
public class V_cons_gae_competenza_entBulk extends OggettoBulk  implements Persistent {
//  ESERCIZIO DECIMAL(4,0)
	private java.lang.Integer esercizio;
 
//    CD_DIPARTIMENTO VARCHAR(30)
	private java.lang.String cd_dipartimento;
 
//    PG_PROGETTO DECIMAL(10,0)
	private java.lang.Long pg_progetto;
 
//    CD_PROGETTO VARCHAR(30)
	private java.lang.String cd_progetto;
 
//    DS_PROGETTO VARCHAR(433)
	private java.lang.String ds_progetto;
 
//    PG_COMMESSA DECIMAL(10,0)
	private java.lang.Long pg_commessa;
 
//    CD_COMMESSA VARCHAR(30)
	private java.lang.String cd_commessa;
 
//    DS_COMMESSA VARCHAR(433)
	private java.lang.String ds_commessa;
 
//    PG_MODULO DECIMAL(10,0)
	private java.lang.Long pg_modulo;
 
//    CD_MODULO VARCHAR(30)
	private java.lang.String cd_modulo;
 
//    DS_MODULO VARCHAR(433)
	private java.lang.String ds_modulo;
 
//    CD_CENTRO_RESPONSABILITA VARCHAR(30)
	private java.lang.String cd_centro_responsabilita;
	
//  CD_CDS VARCHAR(15)
	private java.lang.String cd_cds;

//    CD_LINEA_ATTIVITA VARCHAR(10)
	private java.lang.String cd_linea_attivita;
 
//    DS_LINEA_ATTIVITA VARCHAR(300)
	private java.lang.String ds_linea_attivita;
 
//    TI_APPARTENENZA CHAR(1)
	private java.lang.String ti_appartenenza;
 
//    TI_GESTIONE CHAR(1)
	private java.lang.String ti_gestione;
 
//    CD_ELEMENTO_VOCE VARCHAR(20)
	private java.lang.String cd_elemento_voce;
 
//    DS_ELEMENTO_VOCE VARCHAR(100)
	private java.lang.String ds_elemento_voce;
 
//    IM_STANZ_INIZIALE_A1 DECIMAL(22,0)
	private java.math.BigDecimal im_stanz_iniziale_a1;
 
//    PG_VARIAZIONE_PDG DECIMAL(22,0)
	private java.lang.Long pg_variazione_pdg;
 
//    DS_VARIAZIONE VARCHAR(300)
	private java.lang.String ds_variazione;
 
//    VARIAZIONI_PIU DECIMAL(22,0)
	private java.math.BigDecimal variazioni_piu;
 
//    VARIAZIONI_MENO DECIMAL(22,0)
	private java.math.BigDecimal variazioni_meno;
 
//    CD_CDS_ACC VARCHAR(30)
	private java.lang.String cd_cds_acc;
 
//    PG_ACCERTAMENTO DECIMAL(22,0)
	private java.lang.Long pg_accertamento;
 
//    PG_ACCERTAMENTO_SCADENZARIO DECIMAL(22,0)
	private java.lang.Long pg_accertamento_scadenzario;
 
//    DS_SCADENZA VARCHAR(300)
	private java.lang.String ds_scadenza;
 
//    ACCERTAMENTI_COMP DECIMAL(22,0)
	private java.math.BigDecimal accertamenti_comp;
 
//    CD_CDS_REV VARCHAR(30)
	private java.lang.String cd_cds_rev;
 
//    PG_REVERSALE DECIMAL(22,0)
	private java.lang.Long pg_reversale;
 
//    DS_REVERSALE VARCHAR(300)
	private java.lang.String ds_reversale;
 
//    REVERSALI_COMP DECIMAL(22,0)
	private java.math.BigDecimal reversali_comp;
	
	private java.math.BigDecimal da_incassare;
	private java.math.BigDecimal disponibile;
	private java.math.BigDecimal assestato;
	private java.lang.String ds_natura;
	public V_cons_gae_competenza_entBulk() {
		super();
	}
	public java.math.BigDecimal getAccertamenti_comp() {
		return accertamenti_comp;
	}
	public void setAccertamenti_comp(java.math.BigDecimal accertamenti_comp) {
		this.accertamenti_comp = accertamenti_comp;
	}
	public java.lang.String getCd_cds_acc() {
		return cd_cds_acc;
	}
	public void setCd_cds_acc(java.lang.String cd_cds_acc) {
		this.cd_cds_acc = cd_cds_acc;
	}
	public java.lang.String getCd_cds_rev() {
		return cd_cds_rev;
	}
	public void setCd_cds_rev(java.lang.String cd_cds_rev) {
		this.cd_cds_rev = cd_cds_rev;
	}
	public java.lang.String getCd_centro_responsabilita() {
		return cd_centro_responsabilita;
	}
	public void setCd_centro_responsabilita(
			java.lang.String cd_centro_responsabilita) {
		this.cd_centro_responsabilita = cd_centro_responsabilita;
	}
	public java.lang.String getCd_commessa() {
		return cd_commessa;
	}
	public void setCd_commessa(java.lang.String cd_commessa) {
		this.cd_commessa = cd_commessa;
	}
	public java.lang.String getCd_dipartimento() {
		return cd_dipartimento;
	}
	public void setCd_dipartimento(java.lang.String cd_dipartimento) {
		this.cd_dipartimento = cd_dipartimento;
	}
	public java.lang.String getCd_elemento_voce() {
		return cd_elemento_voce;
	}
	public void setCd_elemento_voce(java.lang.String cd_elemento_voce) {
		this.cd_elemento_voce = cd_elemento_voce;
	}
	public java.lang.String getCd_linea_attivita() {
		return cd_linea_attivita;
	}
	public void setCd_linea_attivita(java.lang.String cd_linea_attivita) {
		this.cd_linea_attivita = cd_linea_attivita;
	}
	public java.lang.String getCd_modulo() {
		return cd_modulo;
	}
	public void setCd_modulo(java.lang.String cd_modulo) {
		this.cd_modulo = cd_modulo;
	}
	public java.lang.String getCd_progetto() {
		return cd_progetto;
	}
	public void setCd_progetto(java.lang.String cd_progetto) {
		this.cd_progetto = cd_progetto;
	}
	public java.lang.String getDs_commessa() {
		return ds_commessa;
	}
	public void setDs_commessa(java.lang.String ds_commessa) {
		this.ds_commessa = ds_commessa;
	}
	public java.lang.String getDs_elemento_voce() {
		return ds_elemento_voce;
	}
	public void setDs_elemento_voce(java.lang.String ds_elemento_voce) {
		this.ds_elemento_voce = ds_elemento_voce;
	}
	public java.lang.String getDs_linea_attivita() {
		return ds_linea_attivita;
	}
	public void setDs_linea_attivita(java.lang.String ds_linea_attivita) {
		this.ds_linea_attivita = ds_linea_attivita;
	}
	public java.lang.String getDs_modulo() {
		return ds_modulo;
	}
	public void setDs_modulo(java.lang.String ds_modulo) {
		this.ds_modulo = ds_modulo;
	}
	public java.lang.String getDs_progetto() {
		return ds_progetto;
	}
	public void setDs_progetto(java.lang.String ds_progetto) {
		this.ds_progetto = ds_progetto;
	}
	public java.lang.String getDs_reversale() {
		return ds_reversale;
	}
	public void setDs_reversale(java.lang.String ds_reversale) {
		this.ds_reversale = ds_reversale;
	}
	public java.lang.String getDs_scadenza() {
		return ds_scadenza;
	}
	public void setDs_scadenza(java.lang.String ds_scadenza) {
		this.ds_scadenza = ds_scadenza;
	}
	public java.lang.String getDs_variazione() {
		return ds_variazione;
	}
	public void setDs_variazione(java.lang.String ds_variazione) {
		this.ds_variazione = ds_variazione;
	}
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setEsercizio(java.lang.Integer esercizio) {
		this.esercizio = esercizio;
	}
	public java.math.BigDecimal getIm_stanz_iniziale_a1() {
		return im_stanz_iniziale_a1;
	}
	public void setIm_stanz_iniziale_a1(java.math.BigDecimal im_stanz_iniziale_a1) {
		this.im_stanz_iniziale_a1 = im_stanz_iniziale_a1;
	}
	public java.lang.Long getPg_accertamento() {
		return pg_accertamento;
	}
	public void setPg_accertamento(java.lang.Long pg_accertamento) {
		this.pg_accertamento = pg_accertamento;
	}
	public java.lang.Long getPg_accertamento_scadenzario() {
		return pg_accertamento_scadenzario;
	}
	public void setPg_accertamento_scadenzario(
			java.lang.Long pg_accertamento_scadenzario) {
		this.pg_accertamento_scadenzario = pg_accertamento_scadenzario;
	}
	public java.lang.Long getPg_commessa() {
		return pg_commessa;
	}
	public void setPg_commessa(java.lang.Long pg_commessa) {
		this.pg_commessa = pg_commessa;
	}
	public java.lang.Long getPg_modulo() {
		return pg_modulo;
	}
	public void setPg_modulo(java.lang.Long pg_modulo) {
		this.pg_modulo = pg_modulo;
	}
	public java.lang.Long getPg_progetto() {
		return pg_progetto;
	}
	public void setPg_progetto(java.lang.Long pg_progetto) {
		this.pg_progetto = pg_progetto;
	}
	public java.lang.Long getPg_reversale() {
		return pg_reversale;
	}
	public void setPg_reversale(java.lang.Long pg_reversale) {
		this.pg_reversale = pg_reversale;
	}
	public java.lang.Long getPg_variazione_pdg() {
		return pg_variazione_pdg;
	}
	public void setPg_variazione_pdg(java.lang.Long pg_variazione_pdg) {
		this.pg_variazione_pdg = pg_variazione_pdg;
	}
	public java.math.BigDecimal getReversali_comp() {
		return reversali_comp;
	}
	public void setReversali_comp(java.math.BigDecimal reversali_comp) {
		this.reversali_comp = reversali_comp;
	}
	public java.lang.String getTi_appartenenza() {
		return ti_appartenenza;
	}
	public void setTi_appartenenza(java.lang.String ti_appartenenza) {
		this.ti_appartenenza = ti_appartenenza;
	}
	public java.lang.String getTi_gestione() {
		return ti_gestione;
	}
	public void setTi_gestione(java.lang.String ti_gestione) {
		this.ti_gestione = ti_gestione;
	}
	public java.math.BigDecimal getVariazioni_meno() {
		return variazioni_meno;
	}
	public void setVariazioni_meno(java.math.BigDecimal variazioni_meno) {
		this.variazioni_meno = variazioni_meno;
	}
	public java.math.BigDecimal getVariazioni_piu() {
		return variazioni_piu;
	}
	public void setVariazioni_piu(java.math.BigDecimal variazioni_piu) {
		this.variazioni_piu = variazioni_piu;
	}
	public java.math.BigDecimal getAssestato() {
		return assestato;
	}
	public void setAssestato(java.math.BigDecimal assestato) {
		this.assestato = assestato;
	}
	public java.math.BigDecimal getDa_incassare() {
		return da_incassare;
	}
	public void setDa_incassare(java.math.BigDecimal da_incassare) {
		this.da_incassare = da_incassare;
	}
	public java.math.BigDecimal getDisponibile() {
		return disponibile;
	}
	public void setDisponibile(java.math.BigDecimal disponibile) {
		this.disponibile = disponibile;
	}
	public java.lang.String getDs_natura() {
		return ds_natura;
	}
	public void setDs_natura(java.lang.String ds_natura) {
		this.ds_natura = ds_natura;
	}
	public java.lang.String getCd_cds() {
		return cd_cds;
	}
	public void setCd_cds(java.lang.String cd_cds) {
		this.cd_cds = cd_cds;
	}
	
}