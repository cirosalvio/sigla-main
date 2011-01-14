package it.cnr.contab.doccont00.core.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Accertamento_scad_voceBase extends Accertamento_scad_voceKey implements Keyed {
	// CD_FONDO_RICERCA VARCHAR(20)
	private java.lang.String cd_fondo_ricerca;

	// IM_VOCE DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal im_voce;

public Accertamento_scad_voceBase() {
	super();
}
public Accertamento_scad_voceBase(java.lang.String cd_cds,java.lang.String cd_centro_responsabilita,java.lang.String cd_linea_attivita,java.lang.Integer esercizio,java.lang.Integer esercizio_originale,java.lang.Long pg_accertamento,java.lang.Long pg_accertamento_scadenzario) {
	super(cd_cds,cd_centro_responsabilita,cd_linea_attivita,esercizio,esercizio_originale,pg_accertamento,pg_accertamento_scadenzario);
}
/* 
 * Getter dell'attributo cd_fondo_ricerca
 */
public java.lang.String getCd_fondo_ricerca() {
	return cd_fondo_ricerca;
}
/* 
 * Getter dell'attributo im_voce
 */
public java.math.BigDecimal getIm_voce() {
	return im_voce;
}
/* 
 * Setter dell'attributo cd_fondo_ricerca
 */
public void setCd_fondo_ricerca(java.lang.String cd_fondo_ricerca) {
	this.cd_fondo_ricerca = cd_fondo_ricerca;
}
/* 
 * Setter dell'attributo im_voce
 */
public void setIm_voce(java.math.BigDecimal im_voce) {
	this.im_voce = im_voce;
}
}
