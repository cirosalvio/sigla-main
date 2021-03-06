/*
 * Copyright (C) 2019  Consiglio Nazionale delle Ricerche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package it.cnr.contab.config00.sto.bulk;
import java.sql.*;
import java.sql.*;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

/**
 * <!-- @TODO: da completare -->
 */

public class EnteHome extends it.cnr.jada.bulk.BulkHome {
/**
 * <!-- @TODO: da completare -->
 * Costruisce un EnteHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 */
public EnteHome(java.sql.Connection conn) {
	super(EnteBulk.class,conn);
}

/**
 * <!-- @TODO: da completare -->
 * Costruisce un EnteHome
 *
 * @param conn	La java.sql.Connection su cui vengono effettuate le operazione di persistenza
 * @param persistentCache	La PersistentCache in cui vengono cachati gli oggetti persistenti caricati da questo Home
 */
public EnteHome(java.sql.Connection conn, it.cnr.jada.persistency.PersistentCache persistentCache) {
	super(EnteBulk.class,conn,persistentCache);
}

/**
 * Restituisce il SQLBuilder per selezionare fra tutte le Unita Organizzative quelle che sono CDS non ENTE
 * @return SQLBuilder
 */

public SQLBuilder createSQLBuilder()
{
	SQLBuilder sql = super.createSQLBuilder();
	sql.addClause( "AND", "fl_cds", SQLBuilder.EQUALS, new Boolean(true));
	sql.addClause( "AND", "cd_tipo_unita", SQLBuilder.EQUALS, Tipo_unita_organizzativaHome.TIPO_UO_ENTE);
	return sql;
}
}