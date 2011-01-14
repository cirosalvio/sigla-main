<!-- 
 ?ResourceName "TemplateForm.jsp"
 ?ResourceTimestamp "08/11/00 16.43.22"
 ?ResourceEdition "1.0"
-->

<%@ page 
	import="it.cnr.jada.util.jsp.*,it.cnr.jada.action.*,java.util.*, it.cnr.jada.util.action.*,it.cnr.contab.doccont00.bp.*"
%>


<%  
		CRUDReversaleBP bp = (CRUDReversaleBP)BusinessProcess.getBusinessProcess(request);
		it.cnr.contab.doccont00.core.bulk.ReversaleIBulk reversale = (it.cnr.contab.doccont00.core.bulk.ReversaleIBulk)bp.getModel();
%>
  <div class="Group">		
  <table border="0" cellspacing="0" cellpadding="2">
	<tr>
			<td><% bp.getController().writeFormLabel( out, "esercizio"); %></td>
	   	<td><% bp.getController().writeFormInput( out, "esercizio"); %></td>
	   	<% if ( reversale.getCd_unita_organizzativa() != null && reversale.getCd_unita_organizzativa().equals( reversale.getCd_uo_ente())) { %>
	   	<td><% bp.getController().writeFormLabel( out, "ti_competenza_residuo"); %></td>
	   	<td><% bp.getController().writeFormInput( out, "ti_competenza_residuo"); %></td>
	   	<% } else { %>
		   <td colspan=2></td>
	   	<% } %>		   
	</tr>
	<tr>
		<td><% bp.getController().writeFormLabel( out, "unita_organizzativa"); %></td>
		<td><% bp.getController().writeFormInput( out,"default", "unita_organizzativa", false, "FormInput","onchange=\"submitForm('doCambiaUnitaOrganizzativa')\"" ); %></td>
		<td colspan=2></td>						
	</tr>
  </table>
  </div>
  
  <div class="Group">		
  <table border="0" cellspacing="0" cellpadding="2">
	<tr>
			<td><% bp.getController().writeFormInput( out, "ti_reversale" ); %></td>
	</tr>
  </table>
  </div>
  
  <div class="Group">		
  <table border="0" cellspacing="0" cellpadding="2">	
	<tr>
			<td><% bp.getController().writeFormLabel( out, "pg_reversale"); %></td>
			<td><% bp.getController().writeFormInput( out, "pg_reversale"); %>
				<% bp.getController().writeFormLabel( out, "dt_emissione"); %>
				<% bp.getController().writeFormInput( out, "dt_emissione"); %></td>
			<td><% bp.getController().writeFormLabel( out, "stato"); %></td>
			<td><% bp.getController().writeFormInput( out, "stato"); %></td>
	</tr>
	<tr>
			<td><% bp.getController().writeFormLabel( out, "dt_trasmissione"); %></td>
			<td><% bp.getController().writeFormInput( out, "dt_trasmissione"); %>
				<% bp.getController().writeFormLabel( out, "dt_ritrasmissione"); %>
				<% bp.getController().writeFormInput( out, "dt_ritrasmissione"); %></td>
			<td><% bp.getController().writeFormLabel( out, "stato_trasmissione"); %></td>
			<td><% bp.getController().writeFormInput( out, "stato_trasmissione"); %></td>
	</tr>
	<tr colspan=2>
			<td><% bp.getController().writeFormLabel( out, "ds_reversale"); %></td>
			<td><% bp.getController().writeFormInput( out, "ds_reversale"); %></td>
	</tr>
  </table>
  </div>

  <div class="Group">		
  <table border="0" cellspacing="0" cellpadding="2">	
  	<tr>
			<td><% bp.getController().writeFormLabel( out, "im_reversale"); %></td>
    		<td><% bp.getController().writeFormInput( out, "im_reversale"); %></td>
    		<td><% bp.getController().writeFormLabel( out, "im_incassato"); %></td>
    		<td colspan=%><% bp.getController().writeFormInput( out, "im_incassato"); %></td>
	</tr>
	
  </table>
  </div>
<% if (!bp.isSearching() && bp.isSiope_attiva() && reversale.isRequiredSiope()) {%>
  <div class="Group">		
	<fieldset class="fieldset">
	<legend class="GroupLabel">Codici SIOPE</legend>
	  <table border="0" cellspacing="0" cellpadding="2">	
	  	<tr>
				<td><% bp.getController().writeFormLabel( out, "im_associato_siope"); %></td>
	    		<td><% bp.getController().writeFormInput( out, "im_associato_siope"); %></td>
				<td><% bp.getController().writeFormLabel( out, "im_da_associare_siope");%></td>
	    		<td><% bp.getController().writeFormInput( out, "im_da_associare_siope");%></td>
		</tr>
	  </table>
	</fieldset>
  </div>  
<% } %> 
  