package it.cnr.contab.pdg00.cdip.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Ass_cdp_uoHome extends BulkHome {
/**
 * <!-- @TODO: da completare -->
 * Costruisce un Ass_cdp_uoHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 */
public Ass_cdp_uoHome(java.sql.Connection conn) {
	super(Ass_cdp_uoBulk.class,conn);
}
/**
 * <!-- @TODO: da completare -->
 * Costruisce un Ass_cdp_uoHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 * @param persistentCache	La PersistentCache in cui vengono cachati gli oggetti persistenti caricati da questo Home
 */
public Ass_cdp_uoHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(Ass_cdp_uoBulk.class,conn,persistentCache);
}
}
