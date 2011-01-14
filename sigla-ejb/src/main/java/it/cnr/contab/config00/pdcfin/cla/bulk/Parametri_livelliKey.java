/*
* Created by Generator 1.0
* Date 30/08/2005
*/
package it.cnr.contab.config00.pdcfin.cla.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Parametri_livelliKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	public Parametri_livelliKey() {
		super();
	}
	public Parametri_livelliKey(java.lang.Integer esercizio) {
		super();
		this.esercizio=esercizio;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Parametri_livelliKey)) return false;
		Parametri_livelliKey k = (Parametri_livelliKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio () {
		return esercizio;
	}
}