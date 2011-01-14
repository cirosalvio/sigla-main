/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 28/09/2006
 */
package it.cnr.contab.pdg00.cdip.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Stipendi_cofi_obb_scadKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.Integer mese;
	private java.lang.String cd_cds_obbligazione;
	private java.lang.Integer esercizio_obbligazione;
	private java.lang.Integer esercizio_ori_obbligazione;
	private java.lang.Long pg_obbligazione;
	public Stipendi_cofi_obb_scadKey() {
		super();
	}
	public Stipendi_cofi_obb_scadKey(java.lang.Integer esercizio, java.lang.Integer mese, java.lang.String cd_cds_obbligazione, java.lang.Integer esercizio_obbligazione, java.lang.Integer esercizio_ori_obbligazione, java.lang.Long pg_obbligazione) {
		super();
		this.esercizio=esercizio;
		this.mese=mese;
		this.cd_cds_obbligazione=cd_cds_obbligazione;
		this.esercizio_obbligazione=esercizio_obbligazione;
		this.esercizio_ori_obbligazione=esercizio_ori_obbligazione;
		this.pg_obbligazione=pg_obbligazione;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Stipendi_cofi_obb_scadKey)) return false;
		Stipendi_cofi_obb_scadKey k = (Stipendi_cofi_obb_scadKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getMese(), k.getMese())) return false;
		if (!compareKey(getCd_cds_obbligazione(), k.getCd_cds_obbligazione())) return false;
		if (!compareKey(getEsercizio_obbligazione(), k.getEsercizio_obbligazione())) return false;
		if (!compareKey(getEsercizio_ori_obbligazione(), k.getEsercizio_ori_obbligazione())) return false;
		if (!compareKey(getPg_obbligazione(), k.getPg_obbligazione())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getMese());
		i = i + calculateKeyHashCode(getCd_cds_obbligazione());
		i = i + calculateKeyHashCode(getEsercizio_obbligazione());
		i = i + calculateKeyHashCode(getEsercizio_ori_obbligazione());
		i = i + calculateKeyHashCode(getPg_obbligazione());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setMese(java.lang.Integer mese)  {
		this.mese=mese;
	}
	public java.lang.Integer getMese() {
		return mese;
	}
	public void setCd_cds_obbligazione(java.lang.String cd_cds_obbligazione)  {
		this.cd_cds_obbligazione=cd_cds_obbligazione;
	}
	public java.lang.String getCd_cds_obbligazione() {
		return cd_cds_obbligazione;
	}
	public void setEsercizio_obbligazione(java.lang.Integer esercizio_obbligazione)  {
		this.esercizio_obbligazione=esercizio_obbligazione;
	}
	public java.lang.Integer getEsercizio_obbligazione() {
		return esercizio_obbligazione;
	}
	public void setEsercizio_ori_obbligazione(java.lang.Integer esercizio_ori_obbligazione)  {
		this.esercizio_ori_obbligazione=esercizio_ori_obbligazione;
	}
	public java.lang.Integer getEsercizio_ori_obbligazione() {
		return esercizio_ori_obbligazione;
	}
	public void setPg_obbligazione(java.lang.Long pg_obbligazione)  {
		this.pg_obbligazione=pg_obbligazione;
	}
	public java.lang.Long getPg_obbligazione() {
		return pg_obbligazione;
	}
}