package it.cnr.contab.compensi00.docs.bulk;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import it.cnr.contab.consultazioni.bulk.ConsultazioniRestHome;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;
import it.cnr.jada.persistency.sql.SimpleFindClause;

public class VTerzoPerCompensoHome extends BulkHome implements ConsultazioniRestHome {
public VTerzoPerCompensoHome(Class clazz, java.sql.Connection conn) {
	super(clazz,conn);
}
public VTerzoPerCompensoHome(Class clazz,java.sql.Connection conn,PersistentCache persistentCache) {
	super(clazz,conn,persistentCache);
}
public VTerzoPerCompensoHome(java.sql.Connection conn) {
	super(V_terzo_per_compensoBulk.class,conn);
}
public VTerzoPerCompensoHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(V_terzo_per_compensoBulk.class,conn,persistentCache);
}
@Override
public SQLBuilder restSelect(UserContext userContext, SQLBuilder sql, CompoundFindClause compoundfindclause, OggettoBulk oggettobulk) throws ComponentException, PersistencyException {
	if (compoundfindclause != null && compoundfindclause.getClauses() != null){
		CompoundFindClause newClauses = new CompoundFindClause();
		Enumeration e1 = compoundfindclause.getClauses();
		Timestamp daData = null;
		Timestamp aData = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		while(e1.hasMoreElements() ){
			FindClause findClause = (FindClause) e1.nextElement();
			if (findClause instanceof SimpleFindClause){
				SimpleFindClause clause = (SimpleFindClause)findClause;
				if (clause.getPropertyName() != null && clause.getPropertyName().equals("daData"))	{
					Date parsedDate;
					try {
						parsedDate = dateFormat.parse((String) clause.getValue());
						daData = new Timestamp(parsedDate.getTime());
					} catch (ParseException e2) {
						throw new ComponentException(e2);
					}
					newClauses.addClause(clause.getLogicalOperator(), "dt_ini_validita", clause.getOperator(), daData);
				} else if (clause.getPropertyName() != null && clause.getPropertyName().equals("aData") )	{
					Date parsedDate;
					try {
						parsedDate = dateFormat.parse((String) clause.getValue());
						aData = new Timestamp(parsedDate.getTime());
					} catch (ParseException e2) {
						throw new ComponentException(e2);
					}
					newClauses.addClause(clause.getLogicalOperator(), "dt_fin_validita", clause.getOperator(), aData);
				} else {
					newClauses.addClause(clause.getLogicalOperator(), clause.getPropertyName(), clause.getOperator(), clause.getValue());
				}
			}
		}
		sql =  selectByClause(userContext, newClauses);
	}
	return sql;
}

}
