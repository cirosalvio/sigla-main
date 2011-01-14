package it.cnr.contab.anagraf00.comp;

import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import it.cnr.contab.client.docamm.VoceIva;
import it.cnr.contab.docamm00.ejb.VoceIvaComponentSession;
import it.cnr.contab.docamm00.tabrif.bulk.Voce_ivaBulk;
import it.cnr.contab.utenze00.bp.Costanti;
import it.cnr.contab.utenze00.bp.WSUserContext;
import it.cnr.jada.UserContext;
import it.cnr.jada.comp.ComponentException;

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
@XmlSeeAlso({java.util.ArrayList.class}) 
@WebService(endpointInterface="it.cnr.contab.anagraf00.ejb.VoceIVAComponentSessionWS")
@DeclareRoles({"WSUserRole","IITRole"})
// annotation proprietarie di JBoss, purtroppo in JBoss 4.2.2 non funzionano i corrispondenti tag in jboss.xml
@EndpointConfig(configName = "Standard WSSecurity Endpoint")
@WebContext(contextRoot="/SIGLA-SIGLAEJB")
public class VoceIVAComponentWS  {


public  VoceIVAComponentWS() {
}
@RolesAllowed({"WSUserRole","IITRole"})
public java.util.ArrayList<VoceIva>  cercaVoceIVA(
		  String query,
		  String dominio,
		  Integer numMax,
		  String user,
		  String ricerca) throws Exception{
	 List VociIVA=null;
	java.util.ArrayList<VoceIva> listaVociIVA=new ArrayList<VoceIva>();
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
			VociIVA=(((VoceIvaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRDOCAMM00_EJB_VoceIvaComponentSession",VoceIvaComponentSession.class)).findListaVoceIVAWS(userContext,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}
	
		int num = 0;
		if (VociIVA != null && !VociIVA.isEmpty()){
    		for (Iterator i =VociIVA.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
    			Voce_ivaBulk voce = (Voce_ivaBulk)i.next();
    			VoceIva v=new VoceIva();
    			v.setCodice(voce.getCd_voce_iva());
    			v.setDescrizione(voce.getCd_voce_iva());
    			v.setPercentuale(voce.getPercentuale());
    			listaVociIVA.add(v);
    			num++;
    		}
		}
		return listaVociIVA;
	}catch (SOAPFaultException e) {
		throw e;	
	} catch (Exception e) {
		throw new SOAPFaultException(faultGenerico());
	}
}
@RolesAllowed({"WSUserRole","IITRole"})
public String  cercaVoceIVAXml(
		  String query,
		  String dominio,
		  String numMax,
		  String user,
		  String ricerca) throws Exception{
	List VociIVA=null;
	UserContext userContext = new WSUserContext(user,null,new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)),null,null,null);
	if(query== null){
		throw new SOAPFaultException(faultQueryNonDefinita());
	}else if(dominio== null||(!dominio.equalsIgnoreCase("codice")&&!dominio.equalsIgnoreCase("descrizione"))){
		throw new SOAPFaultException(faultDominioNonDefinito());
	}else{
		try {
			VociIVA=(((VoceIvaComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRDOCAMM00_EJB_VoceIvaComponentSession",VoceIvaComponentSession.class)).findListaVoceIVAWS(userContext,query, dominio, ricerca));
		} catch (ComponentException e) {
			throw new SOAPFaultException(faultGenerico());
		} catch (RemoteException e) {
			throw new SOAPFaultException(faultGenerico());
		}
	}
	try {
		return generaXML(numMax,VociIVA);
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

public String  generaXML(String numMax,List VociIVA) throws ParserConfigurationException, TransformerException{
		if (numMax==null)
			numMax=new Integer(20).toString();
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	DOMImplementation impl = builder.getDOMImplementation();
    	Document xmldoc = impl.createDocument(null, "root", null);
    	Element root = xmldoc.getDocumentElement();
    	root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation", "https://contab.cnr.it/SIGLA/schema/cercavoceiva.xsd");
    	
    		root.appendChild(generaNumeroVoci(xmldoc,VociIVA));
    		int num = 0;
    		if (VociIVA != null && !VociIVA.isEmpty()){
	    		for (Iterator i =VociIVA.iterator();i.hasNext()&&num< new Integer(numMax).intValue();){
	    			Voce_ivaBulk voce = (Voce_ivaBulk)i.next();
	    			root.appendChild(generaDettaglioVoci( xmldoc,voce.getCd_voce_iva(),voce.getDs_voce_iva(),voce.getPercentuale().toString()));
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


private Element generaNumeroVoci(Document xmldoc,List VociIVA){
	Element e = xmldoc.createElement("numris");
	Node n = xmldoc.createTextNode(new Integer(VociIVA.size()).toString());
	e.appendChild(n);
	return e;	
}
private Element generaDettaglioVoci(Document xmldoc, String voce,String descrizione, String perc){
	
	Element element = xmldoc.createElement("voceiva");
	
	Element elementCodice = xmldoc.createElement("codice");
	Node nodeCodice = xmldoc.createTextNode(voce);
	elementCodice.appendChild(nodeCodice);
	element.appendChild(elementCodice);

	
	Element elementDenominazione = xmldoc.createElement("descrizione");
	Node nodeDenominazione = xmldoc.createTextNode(descrizione==null?"":descrizione);
	elementDenominazione.appendChild(nodeDenominazione);
	element.appendChild(elementDenominazione);
	
	Element elementP = xmldoc.createElement("percentuale");
	Node nodeP = xmldoc.createTextNode(perc);
	elementP.appendChild(nodeP);
	element.appendChild(elementP);
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
