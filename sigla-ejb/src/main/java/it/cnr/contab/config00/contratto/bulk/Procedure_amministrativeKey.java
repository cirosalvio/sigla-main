/*
* Created by Generator 1.0
* Date 09/05/2005
*/
package it.cnr.contab.config00.contratto.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Procedure_amministrativeKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.String cd_proc_amm;
	public Procedure_amministrativeKey() {
		super();
	}
	public Procedure_amministrativeKey(java.lang.String cd_proc_amm) {
		super();
		this.cd_proc_amm=cd_proc_amm;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Procedure_amministrativeKey)) return false;
		Procedure_amministrativeKey k = (Procedure_amministrativeKey) o;
		if (!compareKey(getCd_proc_amm(), k.getCd_proc_amm())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getCd_proc_amm());
		return i;
	}
	public void setCd_proc_amm(java.lang.String cd_proc_amm)  {
		this.cd_proc_amm=cd_proc_amm;
	}
	public java.lang.String getCd_proc_amm () {
		return cd_proc_amm;
	}
}