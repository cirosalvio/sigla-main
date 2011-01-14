package it.cnr.contab.gestiva00.core.bulk;

import java.text.SimpleDateFormat;

import it.cnr.contab.docamm00.tabrif.bulk.*;
import it.cnr.contab.reports.bulk.Print_spooler_paramBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.BulkList;
import it.cnr.jada.util.*;

/**
 * Insert the type's description here.
 * Creation date: (10/16/2001 11:10:31 AM)
 * @author: Ardire Alfonso
 */
public abstract class Riepilogativi_iva_centroVBulk extends Riepilogativi_ivaVBulk {

	private Integer pageNumber = null;

/**
 * Filtro_ricerca_obbligazioniVBulk constructor comment.
 */
public Riepilogativi_iva_centroVBulk() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (2/24/2003 10:35:44 AM)
 * @return java.lang.Integer
 */
public java.lang.Integer getPageNumber() {
	return pageNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (12/6/2002 4:26:09 PM)
 */
public java.lang.String getReportName() {
	return "/gestiva/gestiva/registro_riepilogativo_centro.jasper";
}
/**
 * Insert the method's description here.
 * Creation date: (12/6/2002 4:26:09 PM)
 */
public java.util.Vector getReportParameters() {

	java.util.Vector params = new java.util.Vector();
	if (getId_report() != null) {
		params.add(getId_report().toString());
		params.add(getPageNumber().toString());
	}
	return params;

}
/**
 * Insert the method's description here.
 * Creation date: (30/08/2002 13.48.56)
 * @param newSezionaliFlag java.lang.String
 */
public java.util.Dictionary getSezionaliFlags() {

	OrderedHashtable dic = (OrderedHashtable)super.getSezionaliFlags();
	dic.remove(SEZIONALI_FLAGS_ACQ);
	dic.remove(SEZIONALI_FLAGS_VEN);
	return dic;
}
public String getTipo_report_stampato() {
	
	return "REGISTRO_RIEPILOGATIVO_CENTRO";
}
public it.cnr.jada.bulk.OggettoBulk initializeForSearch(
	it.cnr.jada.util.action.BulkBP bp,
	it.cnr.jada.action.ActionContext context) {

	Riepilogativi_iva_centroVBulk bulk = (Riepilogativi_iva_centroVBulk)super.initializeForSearch(bp, context);
	
	bulk.setEsercizio(it.cnr.contab.utenze00.bulk.CNRUserInfo.getEsercizio(context));
	it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk unita_organizzativa = it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa(context);
	bulk.setCd_unita_organizzativa(unita_organizzativa.getCd_unita_organizzativa());
	
	bulk.setCd_cds(unita_organizzativa.getUnita_padre().getCd_unita_organizzativa());
	bulk.setTipo_stampa(TIPO_STAMPA_RIEPILOGATIVI_CENTRO);

	bulk.setSezionaliFlag(SEZIONALI_FLAGS_SEZ);
	bulk.setPageNumber(new Integer(1));

	//In questo modo cancello l'impostazione dell'attributo settato nel super.initializeForSearch
	bulk.setTipoSezionaleFlag(null);
	
	return bulk;
}
/**
 * Insert the method's description here.
 * Creation date: (2/24/2003 10:35:44 AM)
 * @param newPageNumber java.lang.Integer
 */
public void setPageNumber(java.lang.Integer newPageNumber) {

	pageNumber = newPageNumber;	
}
public BulkList getPrintSpoolerParam() {
	BulkList printSpoolerParam = new BulkList();
	Print_spooler_paramBulk param;
	param = new Print_spooler_paramBulk();
	param.setNomeParam("idReport");
	param.setValoreParam(getId_report().toString());
	param.setParamType("java.lang.Integer");
	printSpoolerParam.add(param);
	
	param = new Print_spooler_paramBulk();
	param.setNomeParam("aPagIniziale");
	param.setValoreParam(getPageNumber().toString());
	param.setParamType("java.lang.String");
	printSpoolerParam.add(param);

	return printSpoolerParam;

}

}
