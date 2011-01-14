package it.cnr.contab.fondecon00.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import it.cnr.contab.fondecon00.comp.FondoSpesaComponent;
@Stateless(name="CNRFONDECON00_EJB_FondoSpesaComponentSession")
public class FondoSpesaComponentSessionBean extends it.cnr.jada.ejb.CRUDComponentSessionBean implements FondoSpesaComponentSession {
@PostConstruct
	public void ejbCreate() throws javax.ejb.CreateException {
	componentObj = new it.cnr.contab.fondecon00.comp.FondoSpesaComponent();
}
public static it.cnr.jada.ejb.CRUDComponentSessionBean newInstance() throws javax.ejb.EJBException {
	return new FondoSpesaComponentSessionBean();
}
public it.cnr.contab.fondecon00.core.bulk.Fondo_spesaBulk setCitta(it.cnr.jada.UserContext param0,it.cnr.contab.fondecon00.core.bulk.Fondo_spesaBulk param1,it.cnr.contab.anagraf00.tabter.bulk.ComuneBulk param2) throws it.cnr.jada.comp.ComponentException,javax.ejb.EJBException {
	pre_component_invocation(param0,componentObj);
	try {
		it.cnr.contab.fondecon00.core.bulk.Fondo_spesaBulk result = ((FondoSpesaComponent)componentObj).setCitta(param0,param1,param2);
		component_invocation_succes(param0,componentObj);
		return result;
	} catch(it.cnr.jada.comp.NoRollbackException e) {
		component_invocation_succes(param0,componentObj);
		throw e;
	} catch(it.cnr.jada.comp.ComponentException e) {
		component_invocation_failure(param0,componentObj);
		throw e;
	} catch(RuntimeException e) {
		throw uncaughtRuntimeException(param0,componentObj,e);
	} catch(Error e) {
		throw uncaughtError(param0,componentObj,e);
	}
}
}
