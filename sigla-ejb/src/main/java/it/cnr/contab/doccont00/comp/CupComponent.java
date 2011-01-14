package it.cnr.contab.doccont00.comp;

import it.cnr.contab.doccont00.tabrif.bulk.*;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.*;
import it.cnr.jada.comp.*;

public class CupComponent extends it.cnr.jada.comp.CRUDComponent 
{

    public  CupComponent()
    {
    }

/**
 * Esegue una operazione di eliminazione logica o fisica di un OggettoBulk.
 *
 * Pre-post-conditions:
 *
 * Nome: Cancellazione logica di un cup
 * Pre: Una richiesta di cancellazione logica di un cup e' stata generata
 * Post: Il data cancellazione del cup e' stato valorizzata
 *
 * Nome: Cancellazione fisica di un cup
 * Pre: Una richiesta di cancellazione fisica di un cup e' stata generata
 * Post: cup e' stato eliminato
 * 
 * @param	userContext	lo UserContext che ha generato la richiesta
 * @param	bulk	il <code>CupBulk</code> che deve essere cancellato
 */	
public void eliminaConBulk(UserContext userContext,OggettoBulk bulk) throws ComponentException
{
	CupBulk cup = (CupBulk) bulk;
	try
	{
		deleteBulk( userContext, cup );
	}
	catch ( it.cnr.jada.persistency.sql.ReferentialIntegrityException e )
	{
		try
		{
			if(cup.getDt_canc()==null){
				java.sql.Timestamp dataOdierna = it.cnr.jada.util.ejb.EJBCommonServices.getServerDate();
				cup.setDt_canc(dataOdierna);
				cup.setUser( userContext.getUser());
				updateBulk( userContext, cup);
			}else
				throw new ApplicationException("Codice Cup presente sui documenti. Gi� cancellato logicamente.");
		}
		catch ( Exception ex )
		{
			throw handleException(cup, ex );
		}
	}
	catch ( it.cnr.jada.persistency.PersistencyException e )
	{
		throw handleException( cup, e );	
	}

}

}
