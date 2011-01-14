/*
* Created by Generator 1.0
* Date 16/06/2005
*/
package it.cnr.contab.doccont00.consultazioni.bulk;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativa_enteBulk;
import it.cnr.contab.utenze00.bp.CNRUserContext;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;
public class V_cons_obbl_riportabiliHome extends BulkHome {
	public V_cons_obbl_riportabiliHome(java.sql.Connection conn) {
		super(V_cons_obbl_riportabiliBulk.class, conn);
	}
	public V_cons_obbl_riportabiliHome(java.sql.Connection conn, PersistentCache persistentCache) {
		super(V_cons_obbl_riportabiliBulk.class, conn, persistentCache);
	}
	public SQLBuilder selectByClause(UserContext usercontext, CompoundFindClause compoundfindclause)
		throws PersistencyException
	{
		SQLBuilder sql = super.selectByClause(usercontext, compoundfindclause);
		sql.addSQLClause("AND","V_CONS_OBBL_RIPORTABILI.ESERCIZIO",sql.EQUALS,CNRUserContext.getEsercizio(usercontext));

		Unita_organizzativa_enteBulk ente = (Unita_organizzativa_enteBulk) getHomeCache().getHome(Unita_organizzativa_enteBulk.class).findAll().get(0);

		//	Se uo 999.000 in scrivania: visualizza tutto l'elenco
		//	Se uo ***.000 in scrivania: visualizza l'elenco di tutte le U.O.
		if (!((CNRUserContext) usercontext).getCd_unita_organizzativa().equals( ente.getCd_unita_organizzativa())){

			sql.addSQLClause("AND","V_CONS_OBBL_RIPORTABILI.CD_CDS",SQLBuilder.EQUALS,CNRUserContext.getCd_cds(usercontext));

			Unita_organizzativaBulk uoScrivania = (Unita_organizzativaBulk)getHomeCache().getHome(Unita_organizzativaBulk.class).findByPrimaryKey(new Unita_organizzativaBulk(CNRUserContext.getCd_unita_organizzativa(usercontext)));
			if(!uoScrivania.isUoCds())
			  sql.addSQLClause("AND","V_CONS_OBBL_RIPORTABILI.CD_UNITA_ORGANIZZATIVA",SQLBuilder.EQUALS,CNRUserContext.getCd_unita_organizzativa(usercontext));  
		}	
		sql.addOrderBy("CD_LINEA_ATTIVITA, CD_CENTRO_RESPONSABILITA, ESERCIZIO_ORIGINALE, PG_OBBLIGAZIONE, PG_OBBLIGAZIONE_SCADENZARIO");
		return sql;
	}	
}