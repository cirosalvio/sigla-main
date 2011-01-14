package it.cnr.contab.config00.pdcep.bulk;

import it.cnr.jada.bulk.*;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.sql.SQLBuilder;
import it.cnr.jada.util.*;

/**
 * Classe che eredita le caratteristiche della classe <code>Voce_epBulk</code>,
 * che contiene le variabili e i metodi comuni a tutte le sue sottoclassi.
 * In particolare si riferisce ad un'entit� di tipo Capoconto.
 */
public class CapocontoBulk extends Voce_epBulk {
	
/**
 * Costruttore della classe <code>CapocontoBulk</code>.
 */
public CapocontoBulk() {
	setTi_voce_ep(Voce_epHome.TIPO_CAPOCONTO);
}
public CapocontoBulk(java.lang.String cd_voce_ep,java.lang.Integer esercizio) {
	super(cd_voce_ep,esercizio);
}
/**
 * Metodo per inizializzare l'oggetto bulk in fase di inserimento.
 * @param bp  Business Process <code>CRUDBP</code> in uso.
 * @param context  <code>ActionContext</code> in uso.
 * @return OggettoBulk this Oggetto bulk in uso.
 */
public OggettoBulk initializeForInsert(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) {
	setEsercizio(it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context));
	return this;
}
/**
 * Metodo per inizializzare l'oggetto bulk in fase di ricerca.
 * @param bp  Business Process <code>CRUDBP</code> in uso.
 * @param context  <code>ActionContext</code> in uso.
 * @return OggettoBulk this Oggetto bulk in uso.
 */
public OggettoBulk initializeForSearch(it.cnr.jada.util.action.CRUDBP bp,it.cnr.jada.action.ActionContext context) {
	setEsercizio(it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context));
	return this;
}
/**
 * Metodo con cui si verifica la validit� di alcuni campi, mediante un 
 * controllo sintattico o contestuale.
 */
public void validate() throws ValidationException {
	super.validate();

	// controllo su campo GRUPPO
	if ( getCd_voce_ep_padre() == null || getCd_voce_ep_padre().equals("") )
			throw new ValidationException( "Il campo GRUPPO deve essere selezionato." );
	// controllo su campo CODICE CAPOCONTO
	if ( !isNullOrEmpty( getCd_proprio_voce_ep() ) )
	{
		long cdLong;
		try
		{
			cdLong = Long.parseLong( getCd_proprio_voce_ep() );
		}
		catch (java.lang.NumberFormatException e)
		{
			throw new ValidationException( "Il campo CODICE CAPOCONTO deve essere numerico. " );			
		}
		if ( cdLong < 0 )
			throw new ValidationException( "Il campo CODICE CAPOCONTO deve essere maggiore di 0. " );					
	}
	// controllo su campo DESCRIZIONE 
		if ( getDs_voce_ep() == null || getDs_voce_ep().equals("") )
			throw new ValidationException("Il campo NOME CAPOCONTO � obbligatorio.");
}
}
