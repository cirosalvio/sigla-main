/*
 * Copyright (C) 2019  Consiglio Nazionale delle Ricerche
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

package it.cnr.contab.doccont00.bp;

import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.util.action.*;
/**
 * Business Process che gestisce le attività di CRUD per l'entita' Accertamento Residuo.
 */
public class CRUDAccertamentoPGiroResiduoBP extends CRUDAccertamentoPGiroBP {
/**
 * CRUDAccertamentoResiduoBP constructor comment.
 */
public CRUDAccertamentoPGiroResiduoBP() {
	super();
}
/**
 * CRUDAccertamentoResiduoBP constructor comment.
 * @param function java.lang.String
 */
public CRUDAccertamentoPGiroResiduoBP(String function) {
	super(function);
}
/*
 *	Disabilito il bottone di cancellazione documento
 */

public boolean isDeleteButtonEnabled() 
{
	return false;	
}
/*
 *	Disabilito il bottone di creazione documento
 */

public boolean isNewButtonEnabled() 
{
	return false;	
}
/**
 * Inzializza il ricevente nello stato di SEARCH.
 * @param context Il contesto dell'azione
 */
public void reset(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
	try {
		rollbackUserTransaction();
		setModel( context, createEmptyModelForSearch(context) );
		setStatus(SEARCH);
		setDirty(false);
		resetChildren( context );
		resetTabs(context);
	} catch(Throwable e) {
		throw new it.cnr.jada.action.BusinessProcessException(e);
	}
}
}
