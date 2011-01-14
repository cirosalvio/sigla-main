
package it.cnr.contab.doccont00.consultazioni.bp;

import java.rmi.RemoteException;
import java.util.Iterator;
import it.cnr.contab.config00.latt.bulk.WorkpackageBulk;
import it.cnr.contab.doccont00.consultazioni.bulk.V_cons_gae_comp_res_sintesiBulk;
import it.cnr.contab.doccont00.consultazioni.ejb.ConsGAEComResSintComponentSession;
import it.cnr.contab.pdg00.ejb.StampaSituazioneSinteticaGAEComponentSession;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;
import it.cnr.jada.util.Config;
import it.cnr.jada.util.RemoteIterator;
import it.cnr.jada.util.action.ConsultazioniBP;
import it.cnr.jada.util.ejb.EJBCommonServices;
import it.cnr.jada.util.jsp.Button;

public class ConsGAEComResSintBP extends ConsultazioniBP {
	
	public static final String DETT= "DETT";
	
	private String pathConsultazione;
	public ConsGAEComResSintComponentSession createConsGAEComResSintComponentSession() throws javax.ejb.EJBException,java.rmi.RemoteException {		
		   return (ConsGAEComResSintComponentSession)it.cnr.jada.util.ejb.EJBCommonServices.createEJB("CNRDOCCONT00_EJB_ConsGAEComResSintComponentSession",ConsGAEComResSintComponentSession.class);
	}
	protected void init(it.cnr.jada.action.Config config,it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
		try {
			super.init(config,context);
			setMultiSelection(true);
			setPageSize(10);
			setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(WorkpackageBulk.class));
		} catch (RemoteException e) {
			  throw new BusinessProcessException(e);
		}
	}
	public void initVariabili(it.cnr.jada.action.ActionContext context,  String livello_destinazione) throws it.cnr.jada.action.BusinessProcessException {
		   try {
			   this.setPathConsultazione(livello_destinazione);
			   if(livello_destinazione!=null){
				   setBulkInfo(it.cnr.jada.bulk.BulkInfo.getBulkInfo(V_cons_gae_comp_res_sintesiBulk.class));
				   setBulkClass(V_cons_gae_comp_res_sintesiBulk.class);
				   getBulkInfo().setShortDescription("Consultazione Situazione Sintetica Rendicontazione GAE");
				   getBulkInfo().setLongDescription("Situaz.Sin.Rendicontazione GAE");
			   }
		   }catch(Throwable e) {
			   throw new BusinessProcessException(e);
		   }
	   }	
	 public java.util.Vector addButtonsToToolbar(java.util.Vector listButton){
		   if(getPathConsultazione()==null){
			   Button button1 = new Button(Config.getHandler().getProperties(getClass()), "Toolbar.dettagli");
			   button1.setSeparator(true);
			   listButton.addElement(button1);
		   }   
	   	   return listButton;
	 }	
	   /**
		* Ritorna la CompoundFindClause ottenuta in base alla selezione effettuata
		*
		* @param field il campo da aggiornare 
		* @param label il nuovo valore da sostituire al vecchio
		*/
	
	   public CompoundFindClause getSelezione(ActionContext context,String livello_destinazione) throws it.cnr.jada.action.BusinessProcessException {
		   try	{
			   CompoundFindClause clauses = new CompoundFindClause();
			   Integer esercizio = it.cnr.contab.utenze00.bp.CNRUserContext.getEsercizio(context.getUserContext());
			   	 
			      for (Iterator i = getSelectedElements(context).iterator();i.hasNext();) 
				   {
					   WorkpackageBulk bulk = (WorkpackageBulk)i.next();
					   CompoundFindClause parzclause = new CompoundFindClause();
					   if (livello_destinazione.compareTo(DETT)==0){
						   parzclause.addClause("AND","cdr",SQLBuilder.EQUALS,bulk.getCd_centro_responsabilita());
						   parzclause.addClause("AND","lda",SQLBuilder.EQUALS,bulk.getCd_linea_attivita());
					   }   
					    clauses.addChild(parzclause);
					    parzclause.setLogicalOperator("OR");
				   }
			      CompoundFindClause clausola_es=new CompoundFindClause();
			      clausola_es.addClause("AND","esercizio",SQLBuilder.EQUALS,esercizio);
			      clauses=clauses.and(clauses,clausola_es);
			      return clauses;
		   }catch(Throwable e) {
			   throw new BusinessProcessException(e);
		   }
	   }
	 public RemoteIterator search(ActionContext context, CompoundFindClause compoundfindclause, OggettoBulk oggettobulk) throws BusinessProcessException {
			try {
				setFindclause(compoundfindclause);
				if(getPathConsultazione()!=null)
					return createConsGAEComResSintComponentSession().findConsultazione(context.getUserContext(),getBaseclause(),compoundfindclause);
				else{
					 return ((StampaSituazioneSinteticaGAEComponentSession)EJBCommonServices.createEJB(
							"CNRPDG00_EJB_StampaSituazioneSinteticaGAEComponentSession",
							StampaSituazioneSinteticaGAEComponentSession.class)).selezionaGae( context.getUserContext(), compoundfindclause);
				}
			}catch(Throwable e) {
				throw new BusinessProcessException(e);
			}
		}

		public void openIterator(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
			try	{	
				
				 if (getPathConsultazione()!=null && getPathConsultazione().compareTo(DETT)==0)
					 setIterator(context,createConsGAEComResSintComponentSession().findConsultazione(context.getUserContext(),getBaseclause(),null));
				 else{
					 it.cnr.jada.util.RemoteIterator ri = ((StampaSituazioneSinteticaGAEComponentSession)EJBCommonServices.createEJB(
								"CNRPDG00_EJB_StampaSituazioneSinteticaGAEComponentSession",
								StampaSituazioneSinteticaGAEComponentSession.class)).selezionaGae( context.getUserContext(), getBaseclause());
						this.setIterator(context,ri);	 
				 }
						 
			}catch(Throwable e) {
				throw new BusinessProcessException(e);
			}
		}
		public String getPathConsultazione() {
			return pathConsultazione;
		}
		public void setPathConsultazione(String pathConsultazione) {
			this.pathConsultazione = pathConsultazione;
		}
}
