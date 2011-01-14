package it.cnr.contab.inventario00.docs.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Inventario_utilizzatori_laBase extends Inventario_utilizzatori_laKey implements Keyed {
	// PERCENTUALE_UTILIZZO_CDR DECIMAL(5,2) NOT NULL
	private java.math.BigDecimal percentuale_utilizzo_cdr;

	// PERCENTUALE_UTILIZZO_LA DECIMAL(5,2) NOT NULL
	private java.math.BigDecimal percentuale_utilizzo_la;

public Inventario_utilizzatori_laBase() {
	super();
}
public Inventario_utilizzatori_laBase(java.lang.String cd_linea_attivita,java.lang.String cd_utilizzatore_cdr,java.lang.Long nr_inventario,java.lang.Long pg_inventario,java.lang.Long progressivo) {
	super(cd_linea_attivita,cd_utilizzatore_cdr,nr_inventario,pg_inventario,progressivo);
}
/* 
 * Getter dell'attributo percentuale_utilizzo_cdr
 */
public java.math.BigDecimal getPercentuale_utilizzo_cdr() {
	return percentuale_utilizzo_cdr;
}
/* 
 * Getter dell'attributo percentuale_utilizzo_la
 */
public java.math.BigDecimal getPercentuale_utilizzo_la() {
	return percentuale_utilizzo_la;
}
/* 
 * Setter dell'attributo percentuale_utilizzo_cdr
 */
public void setPercentuale_utilizzo_cdr(java.math.BigDecimal percentuale_utilizzo_cdr) {
	this.percentuale_utilizzo_cdr = percentuale_utilizzo_cdr;
}
/* 
 * Setter dell'attributo percentuale_utilizzo_la
 */
public void setPercentuale_utilizzo_la(java.math.BigDecimal percentuale_utilizzo_la) {
	this.percentuale_utilizzo_la = percentuale_utilizzo_la;
}
}
