package it.cnr.contab.doccont00.core.bulk;

import it.cnr.contab.config00.bulk.Parametri_cnrBulk;
import it.cnr.contab.config00.bulk.Parametri_cnrHome;
import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Ass_partita_giroHome extends BulkHome {
/**
 * <!-- @TODO: da completare -->
 * Costruisce un Ass_partita_giroHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 */
public Ass_partita_giroHome(java.sql.Connection conn) {
	super(Ass_partita_giroBulk.class,conn);
}
/**
 * <!-- @TODO: da completare -->
 * Costruisce un Ass_partita_giroHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 * @param persistentCache	La PersistentCache in cui vengono cachati gli oggetti persistenti caricati da questo Home
 */
public Ass_partita_giroHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(Ass_partita_giroBulk.class,conn,persistentCache);
}
public Ass_partita_giroBulk getAssociazionePGiroFor(AccertamentoPGiroBulk accert_pgiro) 
	throws PersistencyException, it.cnr.jada.comp.ApplicationException {

	PersistentHome parCNRHome = getHomeCache().getHome(Parametri_cnrBulk.class);
	Parametri_cnrBulk parCNR = (Parametri_cnrBulk)parCNRHome.findByPrimaryKey(new Parametri_cnrBulk(accert_pgiro.getEsercizio()));
	
	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND", "ti_appartenenza", sql.EQUALS, accert_pgiro.getTi_appartenenza());
	sql.addClause("AND", "ti_gestione", sql.EQUALS, accert_pgiro.getTi_gestione());
	sql.addClause("AND", "cd_voce", sql.EQUALS, accert_pgiro.getCapitolo().getCd_titolo_capitolo());
	sql.addClause("AND", "esercizio", sql.EQUALS, accert_pgiro.getEsercizio());
 
	java.util.List result = fetchAll(sql);
	if (result.size() == 0)
		if(parCNR.getFl_nuova_gestione_pg().booleanValue())
			throw new it.cnr.jada.comp.ApplicationException("Non esiste associazione fra capitoli di entrata e capitoli di spesa su Partite di giro, indicare la voce del Piano Contr.");
		else
			throw new it.cnr.jada.comp.ApplicationException("Non esiste associazione fra capitoli di entrata e capitoli di spesa su Partite di giro");
			
	if (result.size() > 1)
		throw new it.cnr.jada.comp.ApplicationException("Esiste più di un'associazione fra il capitolo di entrata e i capitoli di spesa su Partite di giro");
	return (Ass_partita_giroBulk)result.get(0);
}
public Ass_partita_giroBulk getAssociazionePGiroFor(ImpegnoPGiroBulk imp_pgiro) 
	throws PersistencyException, it.cnr.jada.comp.ApplicationException {
	PersistentHome parCNRHome = getHomeCache().getHome(Parametri_cnrBulk.class);
	Parametri_cnrBulk parCNR = (Parametri_cnrBulk)parCNRHome.findByPrimaryKey(new Parametri_cnrBulk(imp_pgiro.getEsercizio()));
	
	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND","ti_appartenenza_clg",sql.EQUALS, imp_pgiro.getTi_appartenenza() );
	sql.addClause("AND","ti_gestione_clg",sql.EQUALS, imp_pgiro.getTi_gestione() );
	sql.addClause("AND","cd_voce_clg",sql.EQUALS, imp_pgiro.getCd_elemento_voce() );
	sql.addClause("AND","esercizio",sql.EQUALS, imp_pgiro.getEsercizio() );		
	java.util.List result = fetchAll( sql );
	if ( result.size() == 0 )
		if(parCNR.getFl_nuova_gestione_pg().booleanValue())
			throw new it.cnr.jada.comp.ApplicationException("Non esiste associazione fra capitoli di entrata e capitoli di spesa su Partite di giro, indicare la voce del Piano Contr.");
		else
			throw new it.cnr.jada.comp.ApplicationException("Non esiste associazione fra capitoli di entrata e capitoli di spesa su Partite di giro");
	if ( result.size() > 1 )
		throw new it.cnr.jada.comp.ApplicationException("Esiste più di un'associazione fra il capitolo di spesa e i capitoli di entrata su Partite di giro");			
	return (Ass_partita_giroBulk) result.get(0);
}
}
