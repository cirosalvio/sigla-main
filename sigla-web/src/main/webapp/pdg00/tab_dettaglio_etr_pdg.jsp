<!-- 
 ?ResourceName ""
 ?ResourceTimestamp ""
 ?ResourceEdition ""
-->

<%@ page 
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		it.cnr.jada.bulk.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.pdg00.action.*,
		it.cnr.contab.pdg00.bp.*"
%>

<%
	CRUDEtrDetPdGBP bp = (CRUDEtrDetPdGBP)BusinessProcess.getBusinessProcess(request);
%>

<table border="0" cellspacing="0" cellpadding="2">
	<tr><% bp.getController().writeFormField(out,"im_ra_rce");%></tr>
	<tr><% bp.getController().writeFormField(out,"im_rb_rse");%></tr>
	<tr><% bp.getController().writeFormField(out,"im_rc_esr");%></tr>
</table>