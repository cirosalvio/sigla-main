package it.cnr.contab.doccont00.bp;

import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
/**
 * Business Process che gestisce le attivit� di CRUD per l'entita' Accertamento Partita di Giro Tronco.
 */
public class CRUDAccertamentoPGiroTroncoBP extends CRUDAccertamentoPGiroBP {
/**
 * CRUDAccertamentoPGiroTroncoBP constructor comment.
 */
public CRUDAccertamentoPGiroTroncoBP() {
	super();
}
/**
 * CRUDAccertamentoPGiroTroncoBP constructor comment.
 * @param function java.lang.String
 */
public CRUDAccertamentoPGiroTroncoBP(String function) {
	super(function);
}
public String getFormTitle() {
	StringBuffer title = new StringBuffer( "Gestione Annotazione di Entrata su Partita di Giro tronca");
	title.append(" - ");
	switch(getStatus()) {
		case INSERT:
			title.append("Inserimento");
			break;
		case EDIT:
			title.append("Modifica");
			break;
		case SEARCH:
			title.append("Ricerca");
			break;
		case VIEW:
			title.append("Visualizza");
			break;
	}
	return title.toString();
}
/**
  * Inizializzo l'oggetto bulk in fase di ricerca libera, impostando a true il flag
  * che identifica l'accertamento su partita di giro "tronco"
  */
public OggettoBulk initializeModelForFreeSearch(ActionContext context,OggettoBulk bulk) throws BusinessProcessException 
{
	try 
	{
		AccertamentoPGiroBulk acc_pgiro = (AccertamentoPGiroBulk) super.initializeModelForFreeSearch( context, bulk );
		acc_pgiro.setFl_isTronco( true );
		return acc_pgiro;
	} catch(Exception e) {
		throw handleException(e);
	}
}
/**
  * Inizializzo l'oggetto bulk in fase di inserimento, impostando a true il flag
  * che identifica l'accertamento su partita di giro "tronco"
  */
public OggettoBulk initializeModelForInsert(ActionContext context,OggettoBulk bulk) throws BusinessProcessException 
{
	try 
	{
		AccertamentoPGiroBulk acc_pgiro = (AccertamentoPGiroBulk) super.initializeModelForInsert( context, bulk );
		acc_pgiro.setFl_isTronco( true );
		return acc_pgiro;
	} catch(Exception e) {
		throw handleException(e);
	}
}
/**
  * Inizializzo l'oggetto bulk in fase di ricerca, impostando a true il flag
  * che identifica l'accertamento su partita di giro "tronco"
  */
public OggettoBulk initializeModelForSearch(ActionContext context,OggettoBulk bulk) throws BusinessProcessException 
{
	try 
	{
		AccertamentoPGiroBulk acc_pgiro = (AccertamentoPGiroBulk) super.initializeModelForSearch( context, bulk );
		acc_pgiro.setFl_isTronco( true );
		return acc_pgiro;
	} catch(Exception e) {
		throw handleException(e);
	}
}
}
