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
		it.cnr.contab.doccont00.bp.*"
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<script language="JavaScript" src="scripts/util.js"></script>
<% JSPUtils.printBaseUrl(pageContext); %>
</head>
<script language="javascript" src="scripts/css.js"></script>

<%  
	CRUDAccertamentoBP bp = (CRUDAccertamentoBP)BusinessProcess.getBusinessProcess(request);
%>

<% if ( bp.getModel() instanceof it.cnr.contab.doccont00.core.bulk.AccertamentoResiduoBulk ) {%>
		<title>Gestione Accertamento Residuo</title>
<%} else {%>
		<title>Gestione Accertamento</title>
<%}%>
	
<body class="Form">
<% bp.openFormWindow(pageContext);%>

<table class="Panel">
	<tr><td>
		<%	
			
			JSPUtils.tabbed(
					pageContext,
					"tab",
					bp.getTabs(),
					bp.getTab("tab"),
					"center",
					null, null,
					!bp.isEditingScadenza());
		%>
	</td></tr>
</table>

<%	bp.closeFormWindow(pageContext); %>

</body>
</html>