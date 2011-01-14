package it.cnr.contab.docamm00.consultazioni.bp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import it.cnr.contab.config00.sto.bulk.Unita_organizzativaBulk;
import it.cnr.contab.docamm00.consultazioni.bulk.V_terzi_da_conguagliareBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Config;
import it.cnr.jada.util.SendMail;
import it.cnr.jada.util.action.ConsultazioniBP;
import it.cnr.jada.util.jsp.Button;

public class ConsTerziDaConguagliareBP extends ConsultazioniBP {
	private boolean uoEnte = false;

	@SuppressWarnings("unchecked")
	@Override
	public Vector addButtonsToToolbar(Vector listButton) {
		if (isUoEnte()){		
			Button eMail = new Button();
			eMail.setImg("img/bringback24.gif");
			eMail.setDisabledImg("img/bringback24.gif");
			eMail.setLabel("<u>C</u>dS da Conguagliare");
			eMail.setHref("javascript:submitForm('doCdSDaConguagliare')");
			eMail.setStyle("width:125px;");
			eMail.setTitle("Comunica ai segretari dei CdS i terzi ancora da conguagliare.");
			eMail.setAccessKey("C");
			eMail.setSeparator(true);
			listButton.add(eMail);
		}
		return super.addButtonsToToolbar(listButton);
	}

	@Override
	protected void init(Config config, ActionContext context) throws BusinessProcessException {
		setUoEnte(context);
		super.init(config, context);
	}
	public void setUoEnte(ActionContext context){	
		Unita_organizzativaBulk uo = it.cnr.contab.utenze00.bulk.CNRUserInfo.getUnita_organizzativa(context);
		if (uo.getCd_tipo_unita().equals(it.cnr.contab.config00.sto.bulk.Tipo_unita_organizzativaHome.TIPO_UO_ENTE))
			setUoEnte(true);	
	}

	public boolean isUoEnte() {
		return uoEnte;
	}

	public void setUoEnte(boolean uoEnte) {
		this.uoEnte = uoEnte;
	}	
	
}
