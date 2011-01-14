package it.cnr.contab.inventario00.tabrif.bulk;

import it.cnr.contab.inventario01.bulk.Buono_carico_scaricoBulk;
import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.sql.*;

public class Tipo_carico_scaricoHome extends BulkHome {
public Tipo_carico_scaricoHome(java.sql.Connection conn) {
	super(Tipo_carico_scaricoBulk.class,conn);
}
public Tipo_carico_scaricoHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(Tipo_carico_scaricoBulk.class,conn,persistentCache);
}
public java.util.Collection findTipoMovimenti(it.cnr.contab.inventario01.bulk.Buono_carico_scaricoBulk buonoCS) throws IntrospectionException, PersistencyException{

	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND","ti_documento",sql.EQUALS, buonoCS.getTi_documento());
	sql.addClause("AND","dt_cancellazione", sql.ISNULL,null);
	if(buonoCS.getTipoMovimento()!=null){
		sql.addClause("AND","fl_aumento_valore",sql.EQUALS, buonoCS.getTipoMovimento().getFl_aumento_valore());
	}
	if(buonoCS.isPerVendita())
		sql.addClause("AND","fl_vendita",sql.EQUALS, Boolean.TRUE);
	else
		if (buonoCS.isByFattura()||buonoCS.isByDocumento())
		  sql.addClause("AND","fl_vendita",sql.EQUALS, Boolean.FALSE);
	if (buonoCS.isByFattura()||buonoCS.isByDocumento()){
		sql.addClause("AND","fl_fatturabile",sql.EQUALS, Boolean.TRUE);
		// Se � un Buono di Carico proveniente da una fattura x aumento di valore
		if (buonoCS instanceof Buono_carico_scaricoBulk && ((Buono_carico_scaricoBulk)buonoCS).isByFatturaPerAumentoValore() ||
			(buonoCS instanceof Buono_carico_scaricoBulk && ((Buono_carico_scaricoBulk)buonoCS).isByDocumentoPerAumentoValore())){
			sql.addClause("AND","fl_aumento_valore",sql.EQUALS, Boolean.TRUE);
		}
		else {
			sql.addClause("AND","fl_aumento_valore",sql.EQUALS, Boolean.FALSE);
		}
	}
	if (buonoCS instanceof Buono_carico_scaricoBulk && 
	   (buonoCS.getTipoMovimento() != null && !buonoCS.getTipoMovimento().getFl_buono_per_trasferimento())
		||((buonoCS.getCrudStatus()== buonoCS.UNDEFINED)||(buonoCS.getCrudStatus()== buonoCS.TO_BE_CREATED)))
	// Esclude i tipi di movimento "Per trasferimento"
		sql.addClause("AND","fl_buono_per_trasferimento",sql.EQUALS, Boolean.FALSE);
	
	return fetchAll(sql);
}

public java.util.Collection findTipoMovimenti(String tipo) throws IntrospectionException, PersistencyException{

	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND","ti_documento",sql.EQUALS, tipo);
	sql.addClause("AND","dt_cancellazione", sql.ISNULL,null);
	
	return fetchAll(sql);
}
public java.util.Collection findTipoMovimentiPerTrasferimento(it.cnr.contab.inventario00.docs.bulk.Trasferimento_inventarioBulk bulk, String ti_documento) throws IntrospectionException, PersistencyException{

	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND","fl_buono_per_trasferimento",sql.EQUALS, Boolean.TRUE);
	
	sql.addClause("AND","ti_documento",sql.EQUALS, ti_documento);
	sql.addClause("AND","dt_cancellazione", sql.ISNULL,null);
	
	
	return fetchAll(sql);
}
public boolean hasMovimentiFatturabili(String tipo) throws IntrospectionException, PersistencyException{

	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND","ti_documento",sql.EQUALS, tipo);
	sql.addClause("AND","dt_cancellazione", sql.ISNULL,null);
	sql.addClause("AND", "fl_fatturabile", sql.EQUALS, Boolean.TRUE);
	
	return (fetchAll(sql) ==null);
}
}
