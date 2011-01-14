package it.cnr.contab.doccont00.bp;

import it.cnr.contab.doccont00.intcass.bulk.V_ext_cassiere00Bulk;
import it.cnr.contab.doccont00.intcass.bulk.Distinta_cassiereBulk;
import it.cnr.contab.doccont00.intcass.bulk.Ext_cassiere00Bulk;
import it.cnr.contab.doccont00.intcass.bulk.Ext_cassiere00_logsBulk;
import it.cnr.jada.action.*;
import it.cnr.jada.util.action.SimpleDetailCRUDController;
import it.cnr.jada.util.jsp.Button;
/**
 * Insert the type's description here.
 * Creation date: (10/04/2003 12.00.24)
 * @author: Gennaro Borriello
 */
public class CaricaFileCassiereBP extends it.cnr.jada.util.action.SelezionatoreListaBP {

	private final LogsController logs = new LogsController("Logs",Ext_cassiere00_logsBulk.class,"logs",this);

	private String azione;
	
	public static final String CARICA = "C";
	public static final String PROCESSA = "P";

	//private class LogsController extends SimpleDetailCRUDController{
		//public LogsController(String name,Class modelClass,String listPropertyName,it.cnr.jada.util.action.FormController parent){
			//super(name,modelClass,listPropertyName,parent);
		//}

		///**
		  //* Reimplementato per disegnare, insieme agli altri pulsanti, il pulsante per visualizzare 
		  //*	  i logs relativi al File selezionato.
		  //*
		//*/
		//protected it.cnr.jada.util.jsp.Button[] createCRUDToolbar() {
			//it.cnr.jada.util.jsp.Button[] toolbar = new it.cnr.jada.util.jsp.Button[6];
			//toolbar[0] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.add");
			//toolbar[1] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.filter");
			//toolbar[2] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.remove");
			//toolbar[3] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.removeAll");
			//toolbar[4] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"CRUDToolbar.removeFilter");
			//toolbar[5] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.logs");
			//return toolbar;
		//}
	//}
/**
 * CaricaFileCassiereBP constructor comment.
 */
public CaricaFileCassiereBP() {
	super();
	setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(V_ext_cassiere00Bulk.class));
	
}
/**
 * CaricaFileCassiereBP constructor comment.
 * @param function java.lang.String
 */
public CaricaFileCassiereBP(String function) {
	super(function);
	setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(V_ext_cassiere00Bulk.class));
}
/**
  * Crea la {@link DistintaCassiereComponentSession } da usare per effettuare operazioni.
  *	Si � resa necessaria la sua implementazione, poich� il BP � un BulkBP piuttosto
  *	che un SimpleCRUDBP.
  *
  * @return DistintaCassiereComponentSession
**/
public it.cnr.contab.doccont00.ejb.DistintaCassiereComponentSession createComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException,BusinessProcessException {
	
	return (it.cnr.contab.doccont00.ejb.DistintaCassiereComponentSession)createComponentSession("CNRDOCCONT00_EJB_DistintaCassiereComponentSession", it.cnr.contab.doccont00.ejb.DistintaCassiereComponentSession.class);
}
/**
  * Reimplementato per disegnare, insieme agli altri pulsanti, il pulsante per processare
  *	  il File selezionato. Aggiunto anche il pulsante per la visualizzazione dei dettagli,
  *	  ed il pulsante per filtrare i file visualizzati.
  *
*/
public it.cnr.jada.util.jsp.Button[] createNavigatorToolbar() {
	it.cnr.jada.util.jsp.Button[] toolbar = new it.cnr.jada.util.jsp.Button[7];
	int i = 0;
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Navigator.previousFrame");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Navigator.previous");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Navigator.next");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Navigator.nextFrame");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.process");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.details");
	toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.filter");
	
	return toolbar;
}
/**
  *	Permette di non visualizzare alcun pulsante nella Toolbar principale
*/
public it.cnr.jada.util.jsp.Button[] createToolbar() {

	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (25/09/2003 12.13.49)
 * @return java.lang.String
 */
public java.lang.String getAzione() {
	return azione;
}
public String getFormTitle() {
	return "<script>document.write(document.title)</script>";
}
/**
 * Insert the method's description here.
 * Creation date: (23/04/2003 9.24.50)
 * @return it.cnr.jada.util.action.SimpleDetailCRUDController
 */
public final it.cnr.jada.util.action.SimpleDetailCRUDController getLogs() {
	return logs;
}
/**
  * Richiama il metodo refresh(ActionContext), che inizializza l'Iterator.
  *
  * @param config il <code>Config</code> che contiene le informazioni di configurazione del BP.
  * @param context il <code>ActionContext</code> che contiene le informazioni relative alla richiesta.
 */
protected void init(Config config,ActionContext context) throws BusinessProcessException {
	
	super.init(config,context);
	setAzione(config.getInitParameter("caricaProcessaAction"));
	
	getLogs().setMultiSelection(false);
	refresh(context);
}
/**
 * Disegna una FORM HTML che contiene le seguenti parti:
 * <ul>
 * <li>un input hidden "comando" che contiene il nome del comando da eseguire sulla action;
 * <li>un input hidden "businessProcess" che contiene il nome del BusinessProcess contestuale
 * 		all'azione.
 * <li>una serie di input hidden necessari per conservare la posizione di scoll sul browser
 * 		alla risposta.
 * </ul>
 * Per completare il disegno della FORM � necessario invocare <code>closeForm</code>
 * @param context il PageContext della JSP su cui va disegnata la FORM
 * @param action nome della action da eseguire
 * @param target target HTML su cui eseguire la action
 */
public void openForm(javax.servlet.jsp.PageContext context,String action,String target) throws java.io.IOException,javax.servlet.ServletException {

	if (CARICA.equals(getAzione())){
		openForm(context,action,target,"multipart/form-data");
	} else
		super.openForm(context, action, target);
	
}
/**
  * Imposta l'Iteratore del Selezionatore, facendo una query sulla vista V_EXT_CASSIERE00,
  *	( DistintaCassiereComponentSession.cercaFile_Cassiere() ).
  *
  * @param context il <code>ActionContext</code> che contiene le informazioni relative alla richiesta  
 */  
public void refresh(ActionContext context) throws BusinessProcessException {
	try {
		setIterator(context,
				createComponentSession().cercaFile_Cassiere(context.getUserContext(), null));
	} catch(Throwable e) {
		throw new BusinessProcessException(e);
	}
}
/**
 * Insert the method's description here.
 * Creation date: (25/09/2003 12.13.49)
 * @param newAzione java.lang.String
 */
public void setAzione(java.lang.String newAzione) {
	azione = newAzione;
}
/**
  * E' stato necessario reimplementare il metodo per far si che la tabella dei logs venga 
  *	aggiornata ogni volta che l'utente seleziona un elemento dalla tabella dei file.
  *
  * @param context il <code>ActionContext</code> che contiene le informazioni relative alla richiesta
  * @param newModel l' <code>OggettoBulk</code> che contiene le informazioni relative all'oggetto da inizializzare
  *
*/  
public void setModel(ActionContext context,it.cnr.jada.bulk.OggettoBulk newModel) throws BusinessProcessException {

	if (newModel != null){
		try{
			newModel = createComponentSession().caricaLogs(context.getUserContext(), (V_ext_cassiere00Bulk)newModel);
		}
		catch (Throwable e){
			throw new BusinessProcessException(e);
		}
	}
	
	super.setModel(context, newModel);
}
}
