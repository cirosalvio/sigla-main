/*
 * Copyright (C) 2020  Consiglio Nazionale delle Ricerche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * Created on Jan 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.ordmag.magazzino.bp;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import it.cnr.contab.ordmag.magazzino.bulk.MovimentiMagBulk;
import it.cnr.contab.ordmag.magazzino.ejb.MovimentiMagComponentSession;
import it.cnr.jada.UserContext;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ApplicationException;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.util.action.SelezionatoreListaBP;

/**
 * @author mincarnato
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SelezionatoreMovimentiDaAnnullareBP extends SelezionatoreListaBP {

	private boolean selectAllOnly;

	public SelezionatoreMovimentiDaAnnullareBP()
	{
		super();
	}

	public SelezionatoreMovimentiDaAnnullareBP(String s)
	{
		super(s);
	}

	public boolean isSelezionaButtonHidden()
	{
		return !table.isMultiSelection() || isSelectAllOnly();
	}
	
	public boolean isSelectAllOnly() {
		return selectAllOnly;
	}

	public void setSelectAllOnly(boolean b) {
		selectAllOnly = b;
	}
	public void annullaMovimenti(ActionContext context) throws BusinessProcessException{
		try {
	        UserContext userContext = context.getUserContext();
	        List<OggettoBulk> lista = getSelectedElements(context);

	        MovimentiMagComponentSession component = (MovimentiMagComponentSession) createComponentSession(context);

	        for (Iterator<OggettoBulk> i = lista.iterator(); i.hasNext(); ) {
	            MovimentiMagBulk mov = (MovimentiMagBulk)i.next();
	            component.annullaMovimento(context.getUserContext(), mov);
	        }

            commitUserTransaction();
            setFocusedElement(context, null);
            refresh(context);
			
		} catch (ApplicationException e) {
			throw handleException(e);
		} catch (ComponentException e) {
			throw handleException(e);
		} catch (IOException e) {
			throw handleException(e);
		} catch (PersistencyException e) {
			throw handleException(e);
		}
	}

	public it.cnr.jada.ejb.CRUDComponentSession createComponentSession(ActionContext actionContext) throws BusinessProcessException {

		return (MovimentiMagComponentSession) createComponentSession(
				"CNRORDMAG00_EJB_MovimentiMagComponentSession",
				MovimentiMagComponentSession.class);

	}


}
