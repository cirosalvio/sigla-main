/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 16/04/2007
 */
package it.cnr.contab.compensi00.tabrif.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Acconto_classific_coriKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.Integer cd_anag;
	private java.lang.String cd_classificazione_cori;
	public Acconto_classific_coriKey() {
		super();
	}
	public Acconto_classific_coriKey(java.lang.Integer esercizio, java.lang.Integer cd_anag, java.lang.String cd_classificazione_cori) {
		super();
		this.esercizio=esercizio;
		this.cd_anag=cd_anag;
		this.cd_classificazione_cori=cd_classificazione_cori;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Acconto_classific_coriKey)) return false;
		Acconto_classific_coriKey k = (Acconto_classific_coriKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getCd_anag(), k.getCd_anag())) return false;
		if (!compareKey(getCd_classificazione_cori(), k.getCd_classificazione_cori())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getCd_anag());
		i = i + calculateKeyHashCode(getCd_classificazione_cori());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setCd_anag(java.lang.Integer cd_anag)  {
		this.cd_anag=cd_anag;
	}
	public java.lang.Integer getCd_anag() {
		return cd_anag;
	}
	public void setCd_classificazione_cori(java.lang.String cd_classificazione_cori)  {
		this.cd_classificazione_cori=cd_classificazione_cori;
	}
	public java.lang.String getCd_classificazione_cori() {
		return cd_classificazione_cori;
	}
}