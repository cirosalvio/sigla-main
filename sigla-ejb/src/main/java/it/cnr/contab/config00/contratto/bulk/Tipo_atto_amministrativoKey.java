/*
* Creted by Generator 1.0
* Date 07/04/2005
*/
package it.cnr.contab.config00.contratto.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Tipo_atto_amministrativoKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.String cd_tipo_atto;
	public Tipo_atto_amministrativoKey() {
		super();
	}
	public Tipo_atto_amministrativoKey(java.lang.String cd_tipo_atto) {
		super();
		this.cd_tipo_atto=cd_tipo_atto;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Tipo_atto_amministrativoKey)) return false;
		Tipo_atto_amministrativoKey k = (Tipo_atto_amministrativoKey) o;
		if (!compareKey(getCd_tipo_atto(), k.getCd_tipo_atto())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getCd_tipo_atto());
		return i;
	}
	public void setCd_tipo_atto(java.lang.String cd_tipo_atto)  {
		this.cd_tipo_atto=cd_tipo_atto;
	}
	public java.lang.String getCd_tipo_atto () {
		return cd_tipo_atto;
	}
}