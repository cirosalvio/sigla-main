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

package it.cnr.contab.cori00.docs.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Liquid_gruppo_coriHome extends BulkHome {
/**
  *  Costruttore utilizzato dalla sottoclasse <code>Liquid_gruppo_coriIHome</code>.
  *
**/  
public Liquid_gruppo_coriHome(Class clazz, java.sql.Connection conn) {
	super(clazz,conn);
}
/**
  *  Costruttore utilizzato dalla sottoclasse <code>Liquid_gruppo_coriIHome</code>.
  *
**/
public Liquid_gruppo_coriHome(Class clazz, java.sql.Connection conn,PersistentCache persistentCache) {
	super(clazz,conn,persistentCache);
}
public Liquid_gruppo_coriHome(java.sql.Connection conn) {
	super(Liquid_gruppo_coriBulk.class,conn);
}
public Liquid_gruppo_coriHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(Liquid_gruppo_coriBulk.class,conn,persistentCache);
}
}
