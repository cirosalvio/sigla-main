<head>
<title>Approvvigionamento delle risorse per Modulo di Commessa</title>

<script language="JavaScript" src="scripts/util.js"></script>
<script language="javascript" src="scripts/css.js"></script>
</head>
<%@ page 
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		it.cnr.jada.bulk.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.prevent01.action.*,
		it.cnr.contab.prevent01.bp.*,
		it.cnr.contab.prevent01.bulk.*"
%>
<body class="Form">
<%
	CRUDPdg_Modulo_EntrateBP bp = (CRUDPdg_Modulo_EntrateBP)BusinessProcess.getBusinessProcess(request);
	bp.openFormWindow(pageContext);
%>
 <table>
    <tr>
    <td>
	<% bp.getController().writeFormLabel(out,"esercizio_ro"); %>
	</td>
	<td>
	<% bp.getController().writeFormInput(out,"esercizio_ro"); %>
	</td>	
	 </tr>

	<tr>
	 <td>
	<% bp.getController().writeFormLabel(out,"cdr_ro"); %>
	 </td>
	 <td colspan=7>
	<% bp.getController().writeFormInput(out,"cdr_ro"); %>
	<% bp.getController().writeFormInput(out,"ds_centro_responsabilita"); %>
	</td>	
	</tr>
	<tr>
	<td>
	<% bp.getController().writeFormLabel(out,"modulo"); %>
	</td>
	<td>
	<% bp.getController().writeFormInput(out,"modulo"); %>	
	 </td>
	 <td>
	<% bp.getController().writeFormLabel(out,"commessa"); %>
	 </td>
	 <td>
	<% bp.getController().writeFormInput(out,"commessa"); %>
	 </td>
	 <td>
	<% bp.getController().writeFormLabel(out,"progetto"); %>
	 </td>
	 <td>
	<% bp.getController().writeFormInput(out,"progetto"); %>
	 </td>
	 <td>
	<% bp.getController().writeFormLabel(out,"dipartimento"); %>
	 </td>
	 <td>
	<% bp.getController().writeFormInput(out,"dipartimento"); %>
	</td>
	</tr>
	</table>
	<table>
	<tr>
	<td>	
  	<% bp.getController().writeFormLabel(out,"importo_totale"); %>
	</td>	
	<td>	
	<% bp.getController().writeFormInput(out,"importo_totale"); %>	
	</td>
	</tr>	
	</table>
<table>
	  <tr>
	  	<td colspan = "4">
		  <% bp.getCrudDettagliEntrate().writeHTMLTable(
				pageContext,
				"default",	
				true,
				false,
				true,
				"900px",
				"150px"); %>
		</td>
	  </tr>
</table>
<!--	  
	<table>					
		<tr>
		<td>
		
		<%bp.getCrudDettagliEntrate().writeFormLabel(out,"cdr_linea"); %> 
		
		<% bp.getCrudDettagliEntrate().writeFormInput(out,"cdr_linea"); %>
		
		<% bp.getCrudDettagliEntrate().writeFormInput(out,"ds_cdr_linea"); %>
		</td>
		</tr>
		<tr>
		<td>
				<% bp.getCrudDettagliEntrate().writeFormLabel(out,"cd_linea_attivita"); %>
			
				<% bp.getCrudDettagliEntrate().writeFormInput(out,"cd_linea_attivita"); %>
			
				<% bp.getCrudDettagliEntrate().writeFormInput(out,"ds_linea_attivita"); %>
			
				<% bp.getCrudDettagliEntrate().writeFormInput(out,"find_linea_attivita"); %>
			</td>	
		</tr>
	</table> 
	-->	
<table>
	<tr>
		<td>
				<% bp.getCrudDettagliEntrate().writeFormLabel(out,"find_classificazione_voci"); %>
		</td>
		<td>
		
				<% bp.getCrudDettagliEntrate().writeFormInput(out,"find_classificazione_voci"); %>
		</td>
		<td>			
				<%bp.getCrudDettagliEntrate().writeFormLabel(out,"natura"); %>			
		</td>
	 	<td>
			
				<%bp.getCrudDettagliEntrate().writeFormInput(out,"natura"); %>
		</td>	
	</tr>	
	<tr>
		<td>
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"find_contraente"); %>
		</td>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"find_contraente",bp.isUtente_Ente(),null,null); %>
		</td>
	</tr>		
	<tr>
		<td>
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"ds_dettaglio"); %>
		</td>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"ds_dettaglio",bp.isUtente_Ente(),null,null); %>
		</td>
	</tr>		
	<tr>
		<td>
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_entrata_tot"); %>
		</td>
		<td>		
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_entrata_tot",bp.isUtente_Ente(),null,null); %>
	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"esercizio_inizio"); %>
	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"esercizio_inizio",bp.isUtente_Ente(),null,null); %>
	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"esercizio_fine"); %>
		
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"esercizio_fine",bp.isUtente_Ente(),null,null); %>
		</td>
	</tr>	
	 <tr>
		<td>
		</td>
		
    	<td>
		  <span class="FormLabel" style="color:black">Proposta</span>
		 </td>	
	
       </tr>
	<tr>

		<td>	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_entrata"); %>
		</td>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_entrata",bp.isUtente_Ente(),null,null); %>
			<span class="Group">
				<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_entrata_app"); %>
				<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_entrata_app",!bp.isUtente_Ente(),null,null); %>
			</span>		
		</td>
	</tr>	
	<tr>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_entrata_a2"); %>
		</td>
		<td>				
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_entrata_a2",bp.isUtente_Ente(),null,null); %>
		</td>
	</tr>	
	
	<tr>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_entrata_a3"); %>				
		</td>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_entrata_a3",bp.isUtente_Ente(),null,null); %>
		</td>
	</tr>	
		
	
	<tr>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"im_spese_vive"); %>
		</td>
		<td>	
		<table>
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"im_spese_vive",bp.isUtente_Ente(),null,null); %>
		</td>	
		<td>
			
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"ds_spese_vive"); %>				
		</td>	
		<td>
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"ds_spese_vive",bp.isUtente_Ente(),null,null); %>
		</td>
		</table>
		</td>	
	</tr>		
	<tr>
		<td>			
			<%bp.getCrudDettagliEntrate().writeFormLabel(out,"area"); %>				
		</td>	
		<td>	
			<%bp.getCrudDettagliEntrate().writeFormInput(out,null,"area",bp.isUtente_Ente(),null,null); %>
		</td>	
	</tr>
</table>
<%	bp.closeFormWindow(pageContext); %>
</body>










