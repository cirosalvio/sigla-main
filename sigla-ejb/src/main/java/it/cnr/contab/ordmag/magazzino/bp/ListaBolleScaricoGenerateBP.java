package it.cnr.contab.ordmag.magazzino.bp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.cnr.contab.config00.contratto.bulk.ContrattoBulk;
import it.cnr.contab.config00.service.ContrattoService;
import it.cnr.contab.ordmag.magazzino.bulk.BollaScaricoMagBulk;
import it.cnr.contab.service.SpringUtil;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Config;
import it.cnr.jada.util.action.SelezionatoreListaBP;
import it.cnr.jada.util.jsp.Button;

public class ListaBolleScaricoGenerateBP extends SelezionatoreListaBP {
	private transient final static Logger logger = LoggerFactory.getLogger(ListaBolleScaricoGenerateBP.class);
	private static final long serialVersionUID = 1L;
	private List<BollaScaricoMagBulk> bolle;

	@Override
	protected void init(Config config, ActionContext context)
			throws BusinessProcessException {
		super.init(config, context);
	}

	public ListaBolleScaricoGenerateBP() {
		this("");
	}

	public it.cnr.jada.util.jsp.Button[] createToolbar() {
		Button[] toolbar = new Button[2];
		int i = 0;
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.print");
		toolbar[i++] = new it.cnr.jada.util.jsp.Button(it.cnr.jada.util.Config.getHandler().getProperties(getClass()),"Toolbar.excel");
		return toolbar;
	}

	public ListaBolleScaricoGenerateBP(String function) {
		super(function);
	}
	
	public ListaBolleScaricoGenerateBP(String function, List<BollaScaricoMagBulk> bolle) {
		super(function);
	}


	public List<BollaScaricoMagBulk> getBolle() {
		return bolle;
	}


	public void setBolle(List<BollaScaricoMagBulk> bolle) {
		this.bolle = bolle;
	}
	
}