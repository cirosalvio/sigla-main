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
		it.cnr.contab.prevent00.bulk.*,
		it.cnr.contab.prevent00.action.*,
		it.cnr.contab.prevent00.bp.*"
%>

<%	CRUDDettagliEtrBilancioPrevCnrBP bp = (CRUDDettagliEtrBilancioPrevCnrBP)BusinessProcess.getBusinessProcess(request);%>

<%JSPUtils.tabbed(
	pageContext,
	"tabEsercizio",
	new String[][]{
			{ "tabCompetenza", "Competenza","/prevent00/tab_det_etr_bilancio_cnr_esercizio_competenze.jsp" },
			{ "tabResidui", "Residui","/prevent00/tab_det_etr_bilancio_cnr_esercizio_residui.jsp" }
    },
	bp.getTab("tabEsercizio"),
	"center",
	"600px",
	"300px" );
%>