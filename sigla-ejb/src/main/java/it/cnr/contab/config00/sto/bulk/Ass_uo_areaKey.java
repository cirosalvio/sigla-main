/*
* Created by Generator 1.0
* Date 12/09/2005
*/
package it.cnr.contab.config00.sto.bulk;

import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Ass_uo_areaKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Integer esercizio;
	private java.lang.String cd_unita_organizzativa;
	private java.lang.String cd_area_ricerca;
	public Ass_uo_areaKey() {
		super();
	}
	public Ass_uo_areaKey(java.lang.Integer esercizio, java.lang.String cd_unita_organizzativa, java.lang.String cd_area_ricerca) {
		super();
		this.esercizio=esercizio;
		this.cd_unita_organizzativa=cd_unita_organizzativa;
		this.cd_area_ricerca=cd_area_ricerca;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Ass_uo_areaKey)) return false;
		Ass_uo_areaKey k = (Ass_uo_areaKey) o;
		if (!compareKey(getEsercizio(), k.getEsercizio())) return false;
		if (!compareKey(getCd_unita_organizzativa(), k.getCd_unita_organizzativa())) return false;
		if (!compareKey(getCd_area_ricerca(), k.getCd_area_ricerca())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getEsercizio());
		i = i + calculateKeyHashCode(getCd_unita_organizzativa());
		i = i + calculateKeyHashCode(getCd_area_ricerca());
		return i;
	}
	public void setEsercizio(java.lang.Integer esercizio)  {
		this.esercizio=esercizio;
	}
	public java.lang.Integer getEsercizio () {
		return esercizio;
	}
	public void setCd_area_ricerca(java.lang.String cd_area_ricerca)  {
		this.cd_area_ricerca=cd_area_ricerca;
	}
	public java.lang.String getCd_area_ricerca () {
		return cd_area_ricerca;
	}
	public void setCd_unita_organizzativa(java.lang.String cd_unita_organizzativa)  {
		this.cd_unita_organizzativa=cd_unita_organizzativa;
	}
	public java.lang.String getCd_unita_organizzativa () {
		return cd_unita_organizzativa;
	}
}