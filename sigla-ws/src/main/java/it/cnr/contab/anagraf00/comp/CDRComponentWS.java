package it.cnr.contab.anagraf00.comp;

import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import it.cnr.contab.client.docamm.Cdr;
import it.cnr.contab.config00.ejb.CDRComponentSession;
import it.cnr.contab.config00.sto.bulk.CdrBulk;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
import it.cnr.contab.docamm00.ejb.FatturaAttivaSingolaComponentSession;
import it.cnr.contab.utenze00.bp.Costanti;
import it.cnr.contab.utenze00.bp.WSUserContext;
import it.cnr.jada.UserContext;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import org.jboss.ws.annotation.EndpointConfig;
import org.jboss.wsf.spi.annotation.WebContext;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import org.jboss.ws.annotation.EndpointConfig;
import org.jboss.wsf.spi.annotation.WebContext;
/**
 * Questa classe svolge le operazioni fondamentali di lettura, scrittura e filtro dei dati
 * immessi o richiesti dall'utente. In oltre sovrintende alla gestione e creazione dati a cui
 * l'utente stesso non ha libero accesso e/o non gli sono trasparenti.
 */
@Stateless
@WebService(endpointInterface="it.cnr.contab.anagraf00.ejb.CDRComponentSessionWS")
@XmlSeeAlso({java.util.ArrayList.class}) 
@DeclareRoles({"WSUserRole","IITRole"})
// annotation proprietarie di JBoss, purtroppo in JBoss 4.2.2 non funzionano i corrispondenti tag in jboss.xml
@EndpointConfig(configName = "Standard WSSecurity Endpoint")
@WebContext(contextRoot="/SIGLA-SIGLAEJB")
public class CDRComponentWS  {

	
public  CDRComponentWS() {
}
@RolesAllowed({"WSUserRole","IITRole"})
public String  cercaCDRXml(
		  String uo,
		  String query,
		  String dominio,
		  String numMax,
		  String user,
		  String ricerca) throws Exception{
	List Cdr=null;
	UserContext userContext = new WSUserContext(user,null,new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)),null,null,null);
	if(uo== null)
		throw new SOAPFaultException(faultUONonDefinita());
	if(query== null){
		throw new SOAPFaultException(faultQueryNonDefinita());
	}else if(dominio== null||(!dominio.equalsIgnoreCase("codice")&&!dominio.equalsIgnoreCase("descrizione"))){
		throw new SOAPFaultException(faultDominioNonDefinito());
	}else{
		try {
			Unita_organizzativaBulk uo_db=new Unita_organizzativaBulk();
			uo_db=(((Unita_organizzativaBulk)(((FatturaAttivaSingolaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRDOCAMM00_EJB_FatturaAttivaSingolaComponentSession",FatturaAttivaSingolaComponentSession.class)).completaOggetto(userContext,new Unita_organizzativaBulk(uo)))));
			if(uo_db==null)
				throw new SOAPFaultException(faultUONonDefinita());
			else	
				Cdr=(((CDRComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_CDRComponentSession",CDRComponentSession.class)).findListaCDRWS(userContext,uo,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}
	try {
		return generaXML(numMax,userContext,Cdr); 
	} catch (Exception e) {
		throw new SOAPFaultException(faultGenerico());
	}
}
@RolesAllowed({"WSUserRole","IITRole"})
public java.util.ArrayList<Cdr>  cercaCDR(
		  String uo,
		  String query,
		  String dominio,
		  Integer numMax,
		  String user,
		  String ricerca) throws Exception{
try {
	List Cdr=null;
	java.util.ArrayList<Cdr> listaCdr=new ArrayList<Cdr>();
	if(user== null)
		user="IIT";
	if(ricerca== null)
		ricerca="selettiva";
	 if(numMax==null)
		 numMax=20;
	UserContext userContext = new WSUserContext(user,null,new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)),null,null,null);
	if(uo== null)
		throw new SOAPFaultException(faultUONonDefinita());
	if(query== null){
		throw new SOAPFaultException(faultQueryNonDefinita());
	}else if(dominio== null||(!dominio.equalsIgnoreCase("codice")&&!dominio.equalsIgnoreCase("descrizione"))){
		throw new SOAPFaultException(faultDominioNonDefinito());
	}else{
		try {
			Unita_organizzativaBulk uo_db=new Unita_organizzativaBulk();
			uo_db=(((Unita_organizzativaBulk)(((FatturaAttivaSingolaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRDOCAMM00_EJB_FatturaAttivaSingolaComponentSession",FatturaAttivaSingolaComponentSession.class)).completaOggetto(userContext,new Unita_organizzativaBulk(uo)))));
			if(uo_db==null)
				throw new SOAPFaultException(faultUONonDefinita());
			else	
				Cdr=(((CDRComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_CDRComponentSession",CDRComponentSession.class)).findListaCDRWS(userContext,uo,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}
	
		int num = 0;
		if (Cdr != null && !Cdr.isEmpty()){
    		for (Iterator i = Cdr.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
    			CdrBulk cdr = (CdrBulk)i.next();
    			Cdr c=new Cdr();
    			c.setCodice(cdr.getCd_centro_responsabilita());
    			c.setDescrizione(cdr.getDs_cdr());
    			listaCdr.add(c);
    			num++;
    		}
		}
		return listaCdr;
	}catch (SOAPFaultException e) {
		throw e;	 
	} catch (Exception e) {
		throw new SOAPFaultException(faultGenerico());
	}
}
private SOAPFault faultGenerico() throws SOAPException {
	return generaFault(new String(Costanti.ERRORE_WS_100.toString()),
			Costanti.erroriWS.get(Costanti.ERRORE_WS_100));
}
private SOAPFault faultQueryNonDefinita() throws SOAPException {
	return generaFault(new String(Costanti.ERRORE_WS_101.toString()),
			Costanti.erroriWS.get(Costanti.ERRORE_WS_101));
}
private SOAPFault faultDominioNonDefinito() throws SOAPException {
	return generaFault(new String(Costanti.ERRORE_WS_102.toString()),
			Costanti.erroriWS.get(Costanti.ERRORE_WS_102));
}
private SOAPFault faultUONonDefinita() throws SOAPException {
	return generaFault(new String(Costanti.ERRORE_WS_105.toString()),
			Costanti.erroriWS.get(Costanti.ERRORE_WS_105));
}
public String  generaXML(String numMax,UserContext userContext,List Cdr) throws ParserConfigurationException, TransformerException, PersistencyException, ComponentException, RemoteException, EJBException{
		if (numMax==null)
			numMax=new Integer(20).toString();
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	DOMImplementation impl = builder.getDOMImplementation();
    	Document xmldoc = impl.createDocument(null, "root", null);
    	Element root = xmldoc.getDocumentElement();
    	root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation", "https://contab.cnr.it/SIGLA/schema/cercacdr.xsd");
    	
		root.appendChild(generaNumeroCDR(xmldoc,Cdr));
			int num = 0;
			if (Cdr != null && !Cdr.isEmpty()){
	    		for (Iterator i = Cdr.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
	    			CdrBulk cdr = (CdrBulk)i.next();
	    			root.appendChild(generaDettaglioCDR(xmldoc,cdr.getCd_centro_responsabilita(),cdr.getDs_cdr()));
	    			num++;
	    		}
			}
    	
    	DOMSource domSource = new DOMSource(xmldoc);
    	StringWriter domWriter = new StringWriter();
    	StreamResult streamResult = new StreamResult(domWriter);

    	TransformerFactory tf = TransformerFactory.newInstance();
    	Transformer serializer = tf.newTransformer();
    	serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
    	//serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"http://150.146.206.250/DTD/cercaterzi.dtd");
    	//serializer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"cercatariffari");
    	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
    	serializer.setOutputProperty(OutputKeys.STANDALONE,"no");
    	serializer.transform(domSource, streamResult);
        return domWriter.toString();
}

private Element generaNumeroCDR(Document xmldoc,List Cdr){
	Element e = xmldoc.createElement("numris");
	Node n = xmldoc.createTextNode(new Integer(Cdr.size()).toString());
	e.appendChild(n);
	return e;	
}
private Element generaDettaglioCDR(Document xmldoc, String codice,  String descrizione){
	
	Element element = xmldoc.createElement("cdr");
	
	Element elementCodice = xmldoc.createElement("codice");
	Node nodeCodice = xmldoc.createTextNode(codice);
	elementCodice.appendChild(nodeCodice);
	element.appendChild(elementCodice);

	Element elementStato = xmldoc.createElement("descrizione");
	Node nodeStato = xmldoc.createTextNode(descrizione);
	elementStato.appendChild(nodeStato);
	element.appendChild(elementStato);
	return element;
}

private SOAPFault generaFault(String localName,String stringFault) throws SOAPException{
	MessageFactory factory = MessageFactory.newInstance();
	SOAPMessage message = factory.createMessage(); 
	SOAPFactory soapFactory = SOAPFactory.newInstance();
	SOAPBody body = message.getSOAPBody(); 
	SOAPFault fault = body.addFault();
	Name faultName = soapFactory.createName(localName,"", SOAPConstants.URI_NS_SOAP_ENVELOPE);
	fault.setFaultCode(faultName);
	fault.setFaultString(stringFault);
	return fault;
}

}
