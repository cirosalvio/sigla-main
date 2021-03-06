<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.docamm00.tabrif.bulk.*,
		it.cnr.contab.docamm00.docs.bulk.*,
		it.cnr.contab.docamm00.bp.*"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<% JSPUtils.printBaseUrl(pageContext); %>
<script language="javascript" src="scripts/css.js"></script>
<script language="JavaScript" src="scripts/util.js"></script>
<title>Ricerca di un documento amministrativo</title>

</head>
<body class="Form">
<%	DocumentiAmministrativiProtocollabiliBP bp = (DocumentiAmministrativiProtocollabiliBP)BusinessProcess.getBusinessProcess(request);
	Filtro_ricerca_doc_amm_protocollabileVBulk filtro = (Filtro_ricerca_doc_amm_protocollabileVBulk)bp.getModel();
	it.cnr.contab.anagraf00.core.bulk.TerzoBulk soggetto = filtro.getSoggetto();
	bp.openFormWindow(pageContext); %>

	<div class="Group" style="width:100%">
		<table width="100%">
			<tr>
				<td>
					<% bp.getController().writeFormLabel(out,"group");%>
				</td>
				<td>
					<% bp.getController().writeFormInput(out,null,"group",false,null,"onChange=\"submitForm('doOnGroupChange')\"");%>
				</td>
			</tr>
		</table>
		<br>
		<div class="Group" style="width:100%">
			<table width="100%">
				<tr>
					<td>
						<% bp.getController().writeFormLabel(out,"cd_soggetto");%>
					</td>
					<td colspan="3">
						<% bp.getController().writeFormInput(out,"cd_soggetto");%>
						<% bp.getController().writeFormInput(out,"soggetto");%>
					</td>
				</tr>
				<tr>
					<td>
						<% bp.getController().writeFormLabel(out,"cd_precedente");%>
					</td>
					<td colspan="3">
						<% bp.getController().writeFormInput(out, "cd_precedente");%>
					</td>
				</tr>
				<tr>
					<% bp.getController().writeFormField(out,"codice_fiscale");%>
					<% bp.getController().writeFormField(out, "partita_iva");%>
				</tr>	
				<%	if (soggetto != null && soggetto.getAnagrafico() != null) { %>
					<tr>
						<%	if (soggetto.getAnagrafico().isStrutturaCNR()) { %>
								<td>
									<b>Nome</b>
								</td>
						<%	} else { %>
								<td>
									<%bp.getController().writeFormLabel(out,"ragione_sociale");%>
								</td>
						<%	} %>
						<td  colspan="3">
							<%bp.getController().writeFormInput(out,"ragione_sociale");%>
						</td>
					</tr>
					<tr>
						<td>
							<% bp.getController().writeFormLabel(out,"denominazione_sede");%>
						</td>
						<td colspan="3">
							<% bp.getController().writeFormInput(out,"denominazione_sede");%>
						</td>
					</tr>
				<% } else { %>
						<tr>
							<td>
								<% bp.getController().writeFormLabel(out,"ragione_sociale");%>
							</td>
							<td colspan="3">
								<% bp.getController().writeFormInput(out,"ragione_sociale");%>
							</td>
						</tr>
				<%	} %>
				<tr>
					<td>
						<% bp.getController().writeFormLabel(out,"cognome");%>
					</td>
					<td colspan="3">
						<% bp.getController().writeFormInput(out,"cognome");%>
					</td>
				</tr>
				<tr>
					<td>
						<% bp.getController().writeFormLabel(out,"nome");%>
					</td>
					<td colspan="3">
						<% bp.getController().writeFormInput(out,"nome");%>
					</td>
				</tr>
			</table>	
		</div>
	</div>
	<div class="Group" style="width:100%">
		<table width="100%">
			<tr>
				<td>
					<% bp.getController().writeFormLabel(out,"optionRadioGroup");%>
				</td>
				<td>
					<% bp.getController().writeFormInput(out,null,"optionRadioGroup",false,null,"onClick=\"submitForm('doOnOptionChange')\"");%>
				</td>
			</tr>
			<tr>
				<td>
					<% bp.getController().writeFormLabel(out,"dt_stampa");%>
				</td>
				<td>
					<% bp.getController().writeFormInput(out,null,"dt_stampa",false,null,"");%>
				</td>
			</tr>
		</table>
	</div>

	<% bp.closeFormWindow(pageContext); %>
</body>