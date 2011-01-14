/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 17/07/2007
 */
package it.cnr.contab.compensi00.tabrif.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Gruppo_cr_uoKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.String cd_gruppo_cr;
	private java.lang.String cd_unita_organizzativa;
	public Gruppo_cr_uoKey() {
		super();
	}
	public Gruppo_cr_uoKey(java.lang.Integer esercizio, java.lang.String cd_gruppo_cr, java.lang.String cd_unita_organizzativa) {
		super();
		this.esercizio=esercizio;
		this.cd_gruppo_cr=cd_gruppo_cr;
		this.cd_unita_organizzativa=cd_unita_organizzativa;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Gruppo_cr_uoKey)) return false;
		Gruppo_cr_uoKey k = (Gruppo_cr_uoKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getCd_gruppo_cr(), k.getCd_gruppo_cr())) return false;
		if (!compareKey(getCd_unita_organizzativa(), k.getCd_unita_organizzativa())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getCd_gruppo_cr());
		i = i + calculateKeyHashCode(getCd_unita_organizzativa());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setCd_gruppo_cr(java.lang.String cd_gruppo_cr)  {
		this.cd_gruppo_cr=cd_gruppo_cr;
	}
	public java.lang.String getCd_gruppo_cr() {
		return cd_gruppo_cr;
	}
	public void setCd_unita_organizzativa(java.lang.String cd_unita_organizzativa)  {
		this.cd_unita_organizzativa=cd_unita_organizzativa;
	}
	public java.lang.String getCd_unita_organizzativa() {
		return cd_unita_organizzativa;
	}
}