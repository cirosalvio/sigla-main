/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 27/11/2006
 */
package it.cnr.contab.progettiric00.geco.bulk;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.util.ejb.EJBCommonServices;
public class Geco_modulo_rstlHome extends BulkHome {
	public Geco_modulo_rstlHome(Connection conn) {
		super(Geco_modulo_rstlBulk.class, conn);
	}
	public Geco_modulo_rstlHome(Connection conn, PersistentCache persistentCache) {
		super(Geco_modulo_rstlBulk.class, conn, persistentCache);
	}
	@Override
	public Timestamp getServerTimestamp() throws PersistencyException {
		return new Timestamp(new java.util.Date().getTime());
	}
	@Override
	public Timestamp getServerDate() throws PersistencyException {
		return new Timestamp(new java.util.Date().getTime());
	}	
}