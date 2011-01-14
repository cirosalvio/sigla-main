package it.cnr.contab.doccont00.intcass.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class V_ext_cassiere00Bulk extends V_ext_cassiere00Base {

	private SimpleBulkList logs;

public V_ext_cassiere00Bulk() {
	super();
}
public int addToLogs (Ext_cassiere00_logsBulk nuovoRigo)
{

	if (getLogs() == null)
		setLogs(new SimpleBulkList());
		
	getLogs().add(nuovoRigo);
	nuovoRigo.setFile(this);
	
	return getLogs().size()-1;
}
/**
 * Insert the method's description here.
 * Creation date: (22/04/2003 17.22.47)
 * @return it.cnr.jada.bulk.SimpleBulkList
 */
public it.cnr.jada.bulk.SimpleBulkList getLogs() {
	return logs;
}
public Ext_cassiere00_logsBulk removeFromLogs( int indiceDiLinea ) {

	Ext_cassiere00_logsBulk element = (Ext_cassiere00_logsBulk)getLogs().get(indiceDiLinea);

	return (Ext_cassiere00_logsBulk)getLogs().remove(indiceDiLinea);
}
/**
 * Insert the method's description here.
 * Creation date: (22/04/2003 17.22.47)
 * @param newLogs it.cnr.jada.bulk.SimpleBulkList
 */
public void setLogs(it.cnr.jada.bulk.SimpleBulkList newLogs) {
	logs = newLogs;
}
}
