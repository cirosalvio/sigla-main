<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		it.cnr.jada.bulk.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.prevent01.bulk.*,
		it.cnr.contab.prevent01.bp.*"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<% JSPUtils.printBaseUrl(pageContext);%>
<%	StampaPdgpBilancioBP bp = (StampaPdgpBilancioBP)BusinessProcess.getBusinessProcess(request);%>
<script language="JavaScript" src="scripts/util.js"></script>
<script language="javascript" src="scripts/css.js"></script>
<title><%=bp.getFormTitle()%></title>
</head>
<body class="Form">

<%	bp.openFormWindow(pageContext); %>

<table width=100%>
 <tr>
  <td>
	<div class="Group">
	<table>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"esercizio"); %></td>
		<td><% bp.getController().writeFormInput(out,"esercizio"); %></td>
	  </tr>	
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_gestione"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_gestione",false,null,"onClick=\"submitForm('doOnTipoChange')\""); %></td>
	  </tr>  
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_stampa"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_stampa",false,null,"onClick=\"submitForm('doDefault')\""); %></td>
	  </tr>  
	  <% if (bp.isStampaRendicontoGestionale()) {%>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_parte"); %></td>
        <td><% bp.getController().writeFormInput(out,"ti_parte"); %></td>
      </tr>
	  <% } %>		
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_aggregazione"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_aggregazione",false,null,"onClick=\"submitForm('doOnTipoAggregazioneChange')\""); %></td>
	  </tr>  
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_origine"); %></td>
		<td><% bp.getController().writeFormInput(out,"ti_origine"); %></td>
	  </tr>  
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_livello"); %></td>
		<td><% bp.getController().writeFormInput(out,"ti_livello"); %></td>
	  </tr>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_riepilogo"); %></td>
		<td><% bp.getController().writeFormInput(out,"ti_riepilogo"); %></td>
	  </tr>
	</table>
	<div class="Group">
	<% if (bp.isStampaRendiconto()) {%>
	    <table>
		  <tr>
			<td class="GroupLabel" colspan="4">Aggiornamento Dati Rendiconto</td>
		  </tr>
		  <tr>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Competenza Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaRendicontoCompetenzaAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Residui Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaRendicontoResiduiAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Cassa Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaRendicontoCassaAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Competenza Anno Precedente", "if (disableDblClick()) submitForm('doAggiornaRendicontoCompetenzaAP')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Residui Anno Precedente", "if (disableDblClick()) submitForm('doAggiornaRendicontoResiduiAP')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Cassa Anno Precedente", "if (disableDblClick()) submitForm('doAggiornaRendicontoCassaAP')",null,true, bp.getParentRoot().isBootstrap());%></td>
		  </tr>
		</div>
	    </table>
	<% } else { %>
	    <table>
		  <tr>
			<td class="GroupLabel" colspan="4">Aggiornamento Dati Bilancio</td>
		  </tr>
		  <tr>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Residuo Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaResiduoAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Cassa Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaCassaAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
		    <% if (Stampa_pdgp_bilancioBulk.TIPO_PROVVISORIO.equals(((Stampa_pdgp_bilancioBulk)bp.getModel()).getTi_origine())) { %>
	 	    	<td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Previsione Anno Corrente", "if (disableDblClick()) submitForm('doAggiornaPrevisioneAC')",null,true, bp.getParentRoot().isBootstrap());%></td>
	 	    	<td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Previsione Anno Precedente", "if (disableDblClick()) submitForm('doAggiornaPrevisioneAP')",null,true, bp.getParentRoot().isBootstrap());%></td>
		 	    <td><%JSPUtils.button(out, "img/transfer.gif", "img/transfer.gif", "Residuo Anno Precedente", "if (disableDblClick()) submitForm('doAggiornaResiduoAP')",null,true, bp.getParentRoot().isBootstrap());%></td>
		 	<% } %>
		  </tr>
		</div>
	    </table>
	<% } %>
   </td>
  </tr>
</table>


<% bp.closeFormWindow(pageContext); %>

</body>
</html>