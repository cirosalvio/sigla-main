package it.cnr.contab.docamm00.bp;

import it.cnr.contab.docamm00.docs.bulk.*;
import it.cnr.contab.doccont00.core.bulk.Accertamento_scadenzarioBulk;
import it.cnr.contab.docamm00.docs.bulk.IDocumentoAmministrativoBulk;
import it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk;
import it.cnr.jada.*;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.ejb.CRUDComponentSession;
import it.cnr.jada.persistency.sql.*;
import it.cnr.jada.util.*;

/**
 * Insert the type's description here.
 * Creation date: (12/3/2001 10:05:28 AM)
 * @author: CNRADM
 */
public interface IDocumentoAmministrativoBP {
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public CRUDComponentSession createComponentSession() throws BusinessProcessException;
public RemoteIterator findObbligazioni(UserContext context, Filtro_ricerca_obbligazioniVBulk filtro) throws BusinessProcessException;
public RemoteIterator findObbligazioniAttributes(ActionContext actionContext, CompoundFindClause clauses, OggettoBulk bulk, OggettoBulk context, String property) throws it.cnr.jada.action.BusinessProcessException;
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public Accertamento_scadenzarioBulk getAccertamento_scadenziario_corrente();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public IDocumentoAmministrativoBulk getBringBackDocAmm();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public it.cnr.contab.docamm00.docs.bulk.Risultato_eliminazioneVBulk getDeleteManager();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public IDocumentoAmministrativoBulk getDocumentoAmministrativoCorrente();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public Obbligazione_scadenzarioBulk getObbligazione_scadenziario_corrente();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public boolean isAutoGenerated();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public boolean isDeleting();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public boolean isManualModify();
/**
 * Insert the method's description here.
 * Creation date: (12/3/2001 10:21:14 AM)
 * @return it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk
 */
public void setIsDeleting(boolean newIsDeleting);
public void validaObbligazionePerDocAmm(ActionContext actionContext, OggettoBulk bulk) throws BusinessProcessException;
}
