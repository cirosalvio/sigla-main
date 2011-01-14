/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 18/02/2010
 */
package it.cnr.contab.docamm00.intrastat.bulk;

import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;

public class Modalita_erogazioneKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.String cd_modalita_erogazione;
	public Modalita_erogazioneKey() {
		super();
	}
	public Modalita_erogazioneKey(java.lang.Integer esercizio, java.lang.String cd_modalita_erogazione) {
		super();
		this.esercizio=esercizio;
		this.cd_modalita_erogazione=cd_modalita_erogazione;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Modalita_erogazioneKey)) return false;
		Modalita_erogazioneKey k = (Modalita_erogazioneKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getCd_modalita_erogazione(), k.getCd_modalita_erogazione())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getCd_modalita_erogazione());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio() {
		return esercizio;
	}
	public void setCd_modalita_erogazione(java.lang.String cd_modalita_erogazione)  {
		this.cd_modalita_erogazione=cd_modalita_erogazione;
	}
	public java.lang.String getCd_modalita_erogazione() {
		return cd_modalita_erogazione;
	}
}