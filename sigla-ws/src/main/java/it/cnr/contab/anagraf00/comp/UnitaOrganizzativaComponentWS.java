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

import it.cnr.contab.client.docamm.Uo;
import it.cnr.contab.config00.ejb.Unita_organizzativaComponentSession;
import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
import it.cnr.contab.utenze00.bp.Costanti;
import it.cnr.contab.utenze00.bp.WSUserContext;
import it.cnr.jada.UserContext;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;

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
@WebService(endpointInterface="it.cnr.contab.anagraf00.ejb.UnitaOrganizzativaComponentSessionWS")
@XmlSeeAlso({java.util.ArrayList.class}) 
@DeclareRoles({"WSUserRole","IITRole"})
// annotation proprietarie di JBoss, purtroppo in JBoss 4.2.2 non funzionano i corrispondenti tag in jboss.xml
@EndpointConfig(configName = "Standard WSSecurity Endpoint")
@WebContext(contextRoot="/SIGLA-SIGLAEJB")
public class UnitaOrganizzativaComponentWS  {


public  UnitaOrganizzativaComponentWS() {
}
@RolesAllowed({"WSUserRole","IITRole"})
public java.util.ArrayList<Uo>  cercaUO(
		  String query,
		  String dominio,
		  Integer numMax,
		  String user,
		  String ricerca) throws Exception{
	List UO=null;
	java.util.ArrayList<Uo> listaUo=new ArrayList<Uo>();
	try{
		
	if(user== null)
		user="IIT";
	if(ricerca== null)
		ricerca="selettiva";
	 if(numMax==null)
		 numMax=20;
			 
	UserContext userContext = new WSUserContext(user,null,new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)),null,null,null);
	if(query== null){
		throw new SOAPFaultException(faultQueryNonDefinita());
	}else if(dominio== null||(!dominio.equalsIgnoreCase("codice")&&!dominio.equalsIgnoreCase("descrizione"))){
		throw new SOAPFaultException(faultDominioNonDefinito());
	}else{
		try {
			UO=(((Unita_organizzativaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_Unita_organizzativaComponentSession",Unita_organizzativaComponentSession.class)).findListaUOWS(userContext,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}

		int num = 0;
		if (UO != null && !UO.isEmpty()){
    		for (Iterator i = UO.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
    			Unita_organizzativaBulk uo = (Unita_organizzativaBulk)i.next();
    			Uo elem =new Uo();
    			elem.setCodice(uo.getCd_unita_organizzativa());
    			elem.setDescrizione(uo.getDs_unita_organizzativa());
    			listaUo.add(elem);
    			num++;
    		}
		}
		return listaUo; 
	}catch (SOAPFaultException e) {
		throw e;		
	} catch (Exception e) {
		throw new SOAPFaultException(faultGenerico());
	}
}
@RolesAllowed({"WSUserRole","IITRole"})
public String  cercaUOXml(
		  String query,
		  String dominio,
		  String numMax,
		  String user,
		  String ricerca) throws Exception{
	List UO=null;
	UserContext userContext = new WSUserContext(user,null,new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)),null,null,null);
	if(query== null){
		throw new SOAPFaultException(faultQueryNonDefinita());
	}else if(dominio== null||(!dominio.equalsIgnoreCase("codice")&&!dominio.equalsIgnoreCase("descrizione"))){
		throw new SOAPFaultException(faultDominioNonDefinito());
	}else{
		try {
			UO=(((Unita_organizzativaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRCONFIG00_EJB_Unita_organizzativaComponentSession",Unita_organizzativaComponentSession.class)).findListaUOWS(userContext,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}
	try {
		return generaXML(numMax,userContext,UO); 
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
public String  generaXML(String numMax,UserContext userContext,List UO) throws ParserConfigurationException, TransformerException, PersistencyException, ComponentException, RemoteException, EJBException{
		if (numMax==null)
			numMax=new Integer(20).toString();
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	DOMImplementation impl = builder.getDOMImplementation();
    	Document xmldoc = impl.createDocument(null, "root", null);
    	Element root = xmldoc.getDocumentElement();
    	root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation", "https://contab.cnr.it/SIGLA/schema/cercaUO.xsd");
    		root.appendChild(generaNumeroUO(xmldoc,UO));
    		int num = 0;
    		if (UO != null && !UO.isEmpty()){
	    		for (Iterator i = UO.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
	    			Unita_organizzativaBulk uo = (Unita_organizzativaBulk)i.next();
	    			root.appendChild(generaDettaglioUO(xmldoc,uo.getCd_unita_organizzativa(),uo.getDs_unita_organizzativa()));
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
    	//serializer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"cercaterzi");
    	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
    	serializer.setOutputProperty(OutputKeys.STANDALONE,"no");
    	serializer.transform(domSource, streamResult);
        return domWriter.toString();
}


private Element generaNumeroUO(Document xmldoc,List UO){
	Element e = xmldoc.createElement("numris");
	Node n = xmldoc.createTextNode(new Integer(UO.size()).toString());
	e.appendChild(n);
	return e;	
}
private Element generaDettaglioUO(Document xmldoc, String codice,  String descrizione){
	
	Element element = xmldoc.createElement("uo");
	
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
