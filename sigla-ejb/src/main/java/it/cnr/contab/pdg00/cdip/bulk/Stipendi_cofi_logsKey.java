/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 18/09/2006
 */
package it.cnr.contab.pdg00.cdip.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Stipendi_cofi_logsKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.Integer mese;
	private java.lang.Long pg_esecuzione;
	public Stipendi_cofi_logsKey() {
		super();
	}
	public Stipendi_cofi_logsKey(java.lang.Integer esercizio, java.lang.Integer mese, java.lang.Long pg_esecuzione) {
		super();
		this.esercizio=esercizio;
		this.mese=mese;
		this.pg_esecuzione=pg_esecuzione;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Stipendi_cofi_logsKey)) return false;
		Stipendi_cofi_logsKey k = (Stipendi_cofi_logsKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getMese(), k.getMese())) return false;
		if (!compareKey(getPg_esecuzione(), k.getPg_esecuzione())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getMese());
		i = i + calculateKeyHashCode(getPg_esecuzione());
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
	public void setPg_esecuzione(java.lang.Long pg_esecuzione)  {
		this.pg_esecuzione=pg_esecuzione;
	}
	public java.lang.Long getPg_esecuzione() {
		return pg_esecuzione;
	}
}