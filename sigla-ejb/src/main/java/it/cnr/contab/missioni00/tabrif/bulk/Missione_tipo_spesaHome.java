package it.cnr.contab.missioni00.tabrif.bulk;

import it.cnr.contab.anagraf00.tabrif.bulk.*;
import it.cnr.contab.config00.esercizio.bulk.EsercizioHome;
import java.sql.*;
import it.cnr.contab.compensi00.docs.bulk.CompensoBulk;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.beans.*;
import it.cnr.jada.persistency.sql.*;

public class Missione_tipo_spesaHome extends BulkHome 
{
public Missione_tipo_spesaHome(java.sql.Connection conn) {
	super(Missione_tipo_spesaBulk.class,conn);
}
public Missione_tipo_spesaHome(java.sql.Connection conn,PersistentCache persistentCache) {
	super(Missione_tipo_spesaBulk.class,conn,persistentCache);
}
/**
 * Vengono aggiunte alla query le clausole realtive alla chiave primaria 
 * tranne la data inizio validita
 * 
*/
private void addPrimaryClauses(SQLBuilder sql, Missione_tipo_spesaBulk tipoSpesa){

	sql.addClause("AND", "cd_ti_spesa",          sql.EQUALS, tipoSpesa.getCd_ti_spesa());
 	sql.addClause("AND", "ti_area_geografica",   sql.EQUALS, tipoSpesa.getTi_area_geografica());
   	sql.addClause("AND", "pg_nazione",           sql.EQUALS, tipoSpesa.getPg_nazione());
 	sql.addClause("AND", "pg_rif_inquadramento", sql.EQUALS, tipoSpesa.getPg_rif_inquadramento());
}
/**
 * Viene ricercata la massima Data Inizio Validita presente in tabella per l'oggetto avente la
 * stessa chiave dell'oggetto passato come parametro e data inizio validita diversa da <dataInizio>
 * Viene restituita la data trovata, NULL altrimenti
 * 
*/
private Timestamp findDataInizioPeriodoPrecedente(Missione_tipo_spesaBulk tipoSpesa, Timestamp dataInizio) throws PersistencyException, IntrospectionException{

	SQLBuilder sql = createSQLBuilder();

	sql.setHeader("SELECT MAX(DT_INIZIO_VALIDITA) AS DT_INIZIO_VALIDITA");
	addPrimaryClauses(sql, tipoSpesa);
	sql.addClause("AND", "dt_inizio_validita", sql.NOT_EQUALS, dataInizio);

	Broker broker = createBroker(sql);
	Object value = null;
	if (broker.next()) {
		value = broker.fetchPropertyValue("dt_inizio_validita",getIntrospector().getPropertyType(getPersistentClass(),"dt_inizio_validita"));
		broker.close();
	}
	return (Timestamp)value;
}
/**
 * Viene ricercato l'oggetto bulk avente la stessa chiave dell'oggetto passato come parametro
 * e data inizio validita uguale alla data inizio pi� alta diversa da quella del parametro
 * Viene restituito l'oggetto trovato, NULL altrimenti
 * 
*/
public Missione_tipo_spesaBulk findIntervalloPrecedente(Missione_tipo_spesaBulk tipoSpesa, boolean lock) throws PersistencyException, IntrospectionException, BusyResourceException, OutdatedResourceException{

	Timestamp data = findDataInizioPeriodoPrecedente(tipoSpesa, tipoSpesa.getDt_inizio_validita());
	if (data==null)
		return null;
	SQLBuilder sql = createSQLBuilder();
	addPrimaryClauses(sql, tipoSpesa);
	sql.addClause("AND", "dt_inizio_validita", sql.EQUALS, data);

	Missione_tipo_spesaBulk obj = null;
	if (lock)
		obj = (Missione_tipo_spesaBulk)fetchAndLock(sql);
	else{
		Broker broker = createBroker(sql);
		if (broker.next())
			obj = (Missione_tipo_spesaBulk)fetch(broker);
		broker.close();
	}
	return obj;
}
/**
 * Esegue la Query per cercare i profili (Tab Rif_inquadramento)
 *
*/
public java.util.List findRifInquadramenti(Missione_tipo_spesaBulk tipoSpesa,it.cnr.contab.anagraf00.tabrif.bulk.Rif_inquadramentoHome rifInqHome,it.cnr.contab.anagraf00.tabrif.bulk.Rif_inquadramentoBulk clause) throws  PersistencyException {
	return rifInqHome.findAll();
}
/**
 * Verifico se l'oggetto bulk ricevuto come parametro ha una versione con DATA CANCELLAZIONE valorizzata
 * Il metodo ritorna la versione dell'oggetto Bulk con data cancellazione valorizzata, NULL altrimenti
 *
**/
public Missione_tipo_spesaBulk getBulkLogicamenteCancellato(Missione_tipo_spesaBulk tipoSpesa) throws PersistencyException {

	SQLBuilder sql = createSQLBuilder();
	addPrimaryClauses(sql, tipoSpesa);
	sql.addSQLClause("AND","DT_CANCELLAZIONE",     sql.ISNOTNULL, null );
		
	Missione_tipo_spesaBulk obj = null;
	SQLBroker broker = createBroker(sql);
	if(broker.next())
		obj = (Missione_tipo_spesaBulk)fetch(broker);
	broker.close();

	return obj;
}
/**
 * Per poter CREARE un nuovo record :
 *
 * 1. Se non ho altri record in tabella con la stessa chiave, procedo con l'nserimento senza ulteriori controlli
 * 2. Se ho altri record con stessa chiave:
 *
 *		2.1. la data di inizio validita' del nuovo record deve essere maggiore di quella del record piu' recente
 *		2.2. la data di inizio validita' del nuovo record deve essere maggiore della data fine validita del record piu' recente
 *			==> la data di fine validita' del record piu' recente viene aggiornata con la data di inizio validita' del nuovo record meno un giorno
 *			==> la data di fine validita' del nuovo record viene posta uguale alla data infinto (31/12/2200)
 * 		Altrimenti viene emesso il messaggio:
 * 			"La Data Inizio Validita non � valida. Deve essere maggiore di xxx"
*/
public void validaPeriodoInCreazione(UserContext userContext, Missione_tipo_spesaBulk corrente) throws PersistencyException, it.cnr.jada.comp.ApplicationException, OutdatedResourceException, BusyResourceException, IntrospectionException {

	Missione_tipo_spesaBulk precedente = findIntervalloPrecedente(corrente, true);
 	if(precedente!=null){
		if (precedente.getDataFineValidita()!=null && corrente.getDt_inizio_validita().compareTo(precedente.getDataFineValidita())<=0){
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			throw new it.cnr.jada.comp.ApplicationException("La Data Inizio Validita non � valida. Deve essere maggiore di " + sdf.format(precedente.getDataFineValidita()));
		}else if(corrente.getDt_inizio_validita().compareTo(precedente.getDt_inizio_validita())<=0){
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			throw new it.cnr.jada.comp.ApplicationException("La Data Inizio Validita non � valida. Deve essere maggiore di " + sdf.format(precedente.getDt_inizio_validita()));
		}
	 	precedente.setDt_fine_validita(CompensoBulk.decrementaData(corrente.getDt_inizio_validita()));
		update(precedente, userContext);
	}
	corrente.setDt_fine_validita(EsercizioHome.DATA_INFINITO);
}
}
