/*
 * Created on Apr 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.consultazioni.bp;

import it.cnr.jada.util.Config;
import it.cnr.jada.util.action.ConsultazioniBP;
import it.cnr.jada.util.jsp.Button;
/**
 * @author mspasiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConsTotaleDocContCommessaContrattoBP extends ConsultazioniBP {

	public java.util.Vector addButtonsToToolbar(java.util.Vector listButton){
		Button button = new Button(Config.getHandler().getProperties(getClass()), "Toolbar.accertamenti");
		button.setSeparator(true);
		listButton.addElement(button);
		listButton.addElement(new Button(Config.getHandler().getProperties(getClass()), "Toolbar.obbligazioni"));
		return listButton;
	}

}
