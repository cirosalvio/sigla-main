package it.cnr.contab.config00.ejb;

import it.cnr.contab.config00.bulk.RicercaContrattoBulk;

import javax.ejb.Remote;

@Remote
public interface ContrattoComponentSession
	extends it.cnr.jada.ejb.CRUDComponentSession, it.cnr.jada.ejb.PrintComponentSession {
it.cnr.contab.config00.contratto.bulk.ContrattoBulk salvaDefinitivo(it.cnr.jada.UserContext param0,it.cnr.contab.config00.contratto.bulk.ContrattoBulk param1) throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
it.cnr.jada.bulk.OggettoBulk cercaContrattoCessato(it.cnr.jada.UserContext param0,it.cnr.jada.bulk.OggettoBulk param1) throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
void controllaCancellazioneAssociazioneUo(it.cnr.jada.UserContext param0,it.cnr.contab.config00.contratto.bulk.Ass_contratto_uoBulk param1) throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
it.cnr.contab.config00.contratto.bulk.ContrattoBulk initializzaUnita_Organizzativa(it.cnr.jada.UserContext param0,it.cnr.contab.config00.contratto.bulk.ContrattoBulk param1) throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
java.util.List findListaContrattiWS(it.cnr.jada.UserContext userContext,String uo,String tipo,String query,String dominio,String tipoRicerca)throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
java.util.List findListaContrattiSIP(it.cnr.jada.UserContext userContext,RicercaContrattoBulk bulk)throws it.cnr.jada.comp.ComponentException,java.rmi.RemoteException;
}
