package it.cnr.contab.utenze00.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class RuoloKey extends OggettoBulk implements KeyedPersistent {
	// CD_RUOLO VARCHAR(20) NOT NULL (PK)
	private java.lang.String cd_ruolo;

public RuoloKey() {
	super();
}
public RuoloKey(java.lang.String cd_ruolo) {
	super();
	this.cd_ruolo = cd_ruolo;
}
public boolean equalsByPrimaryKey(Object o) {
	if (this == o) return true;
	if (!(o instanceof RuoloKey)) return false;
	RuoloKey k = (RuoloKey)o;
	if(!compareKey(getCd_ruolo(),k.getCd_ruolo())) return false;
	return true;
}
/* 
 * Getter dell'attributo cd_ruolo
 */
public java.lang.String getCd_ruolo() {
	return cd_ruolo;
}
public int primaryKeyHashCode() {
	return
		calculateKeyHashCode(getCd_ruolo());
}
/* 
 * Setter dell'attributo cd_ruolo
 */
public void setCd_ruolo(java.lang.String cd_ruolo) {
	this.cd_ruolo = cd_ruolo;
}
}
