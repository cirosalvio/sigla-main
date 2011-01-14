package it.cnr.contab.docamm00.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless(name="CNRDOCAMM00_EJB_TariffarioComponentSession")
public class TariffarioComponentSessionBean extends it.cnr.jada.ejb.CRUDComponentSessionBean implements TariffarioComponentSession {
@PostConstruct
	public void ejbCreate() throws javax.ejb.CreateException {
	componentObj = new it.cnr.contab.docamm00.comp.TariffarioComponent();
}
public static it.cnr.jada.ejb.CRUDComponentSessionBean newInstance() throws javax.ejb.EJBException {
	return new TariffarioComponentSessionBean();
}
public java.util.List findListaTariffariWS(it.cnr.jada.UserContext param0,String param1,String param2,String param3,String param4) throws it.cnr.jada.comp.ComponentException,javax.ejb.EJBException {
	pre_component_invocation(param0,componentObj);
	try {
		java.util.List result = ((it.cnr.contab.docamm00.comp.TariffarioComponent)componentObj).findListaTariffariWS(param0,param1,param2,param3,param4);
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
