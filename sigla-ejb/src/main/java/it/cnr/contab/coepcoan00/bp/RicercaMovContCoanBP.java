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

package it.cnr.contab.coepcoan00.bp;

import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
public class RicercaMovContCoanBP extends it.cnr.jada.util.action.SimpleCRUDBP {
/**
 * RicercaMovContCoanBP constructor comment.
 */
public RicercaMovContCoanBP() {
	super();
}
/**
 * RicercaMovContCoanBP constructor comment.
 * @param function java.lang.String
 */
public RicercaMovContCoanBP(String function) {
	super(function);
}
protected void init(it.cnr.jada.action.Config config,it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException 
{
	super.init( config, context );
	setStatus(SEARCH);
	try
	{
		resetForSearch( context );		
	} catch(Throwable e) {
		throw new BusinessProcessException(e);
	}
//	super.init(config,context);
}	
public OggettoBulk initializeModelForEdit(ActionContext context,OggettoBulk bulk) throws BusinessProcessException {
	try 
	{
		bulk = super.initializeModelForEdit( context, bulk );
		return bulk.initializeForEdit( this, context );
	} catch(Throwable e) {
		throw new it.cnr.jada.action.BusinessProcessException(e);
	}
}
/**
 *	Disabilito il bottone di cancellazione 
 */

public boolean isDeleteButtonHidden() {
	return true;
}
/**
 *	Disabilito il bottone di Nuovo
 */

public boolean isNewButtonHidden() {
	return true;
}
/**
 *	Disabilito il bottone di salva
 */

public boolean isSaveButtonHidden() {
	return true;
}
}
