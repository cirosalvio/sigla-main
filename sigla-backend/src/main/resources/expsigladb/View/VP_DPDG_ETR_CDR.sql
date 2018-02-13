--------------------------------------------------------
--  DDL for View VP_DPDG_ETR_CDR
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "VP_DPDG_ETR_CDR" ("ESERCIZIO", "CD_CDS", "DS_CDS", "CD_TIPO_UNITA", "CD_RESPONSABILE_CDS", "CD_RESPONSABILE_AMM_CDS", "CD_UNITA_ORGANIZZATIVA", "DS_UNITA_ORGANIZZATIVA", "FL_UO_CDS", "FL_RUBRICA", "FL_PRESIDENTE_AREA", "CD_AREA_RICERCA", "CD_CENTRO_RESPONSABILITA", "DS_CDR", "CD_PROPRIO_CDR", "CD_RESPONSABILE_CDR", "INDIRIZZO", "CD_CDR_AFFERENZA", "STATO_PIANO", "ANNOTAZIONI", "FL_RIBALTATO_SU_AREA", "CD_LINEA_ATTIVITA", "TI_APPARTENENZA", "TI_GESTIONE", "CD_ELEMENTO_VOCE", "PG_ENTRATA", "DT_REGISTRAZIONE", "DESCRIZIONE", "STATO", "ORIGINE", "CATEGORIA_DETTAGLIO", "FL_SOLA_LETTURA", "CD_CENTRO_RESPONSABILITA_CLGS", "CD_LINEA_ATTIVITA_CLGS", "TI_APPARTENENZA_CLGS", "TI_GESTIONE_CLGS", "CD_ELEMENTO_VOCE_CLGS", "PG_SPESA_CLGS", "IM_RA_RCE", "IM_RB_RSE", "IM_RC_ESR", "IM_RD_A2_RICAVI", "IM_RE_A2_ENTRATE", "IM_RF_A3_RICAVI", "IM_RG_A3_ENTRATE", "FL_LIMITE_ASS_OBBLIG", "FL_VOCE_PERSONALE", "CD_PARTE", "DS_ELEMENTO_VOCE", "CD_TITOLO", "DS_TITOLO", "CD_CAPOCONTO", "DS_CAPOCONTO", "CD_TIPO_LINEA_ATTIVITA", "DENOMINAZIONE", "CD_GRUPPO_LINEA_ATTIVITA", "CD_FUNZIONE", "CD_NATURA", "DS_LINEA_ATTIVITA", "CD_CDR_COLLEGATO", "CD_LA_COLLEGATO", "DS_FUNZIONE", "DS_NATURA", "CD_PROGETTO", "DS_PROGETTO") AS 
  (
select
--
-- Date: 09/11/2006
-- Version: 1.5
--
-- Vista di stampa dei dettagli di entrata CDR
-- La vista non effettua controlli su STO
--
-- History:
--
-- Date: 10/11/2001
-- Version: 1.0
-- Creazione
--
-- Date: 18/11/2001
-- Version: 1.1
-- Eliminazione esercizio da STO
--
-- Date: 23/02/2001
-- Version: 1.2
-- Estrazione dei soli dettali in non annullati
--
-- Date: 27/02/2002
-- Version: 1.3
-- Outer join su funzione per le entrate
--
-- Date: 21/11/2002
-- Version: 1.4
-- Eliminazione dei risultati dalla vista
--
-- Date: 09/11/2006
-- Version: 1.5
-- Aggiunta la selezione del progetto/commessa/modulo per anno
--
-- Body:
--
-- Table: UNITA_ORGANIZZATIVA
/*   1 */  d.ESERCIZIO
/*   2 */ ,a.CD_UNITA_ORGANIZZATIVA
/*   3 */ ,a.DS_UNITA_ORGANIZZATIVA
/*   5 */ ,a.CD_TIPO_UNITA
/*  12 */ ,a.CD_RESPONSABILE
/*  13 */ ,a.CD_RESPONSABILE_AMM
-- Table: UNITA_ORGANIZZATIVA
/*  23 */ ,b.CD_UNITA_ORGANIZZATIVA
/*  24 */ ,b.DS_UNITA_ORGANIZZATIVA
/*  28 */ ,b.FL_UO_CDS
/*  29 */ ,b.FL_RUBRICA
/*  35 */ ,b.FL_PRESIDENTE_AREA
/*  42 */ ,b.CD_AREA_RICERCA
-- Table: CDR
/*  44 */ ,c.CD_CENTRO_RESPONSABILITA
/*  46 */ ,c.DS_CDR
/*  48 */ ,c.CD_PROPRIO_CDR
/*  49 */ ,c.CD_RESPONSABILE
/*  50 */ ,c.INDIRIZZO
/*  56 */ ,c.CD_CDR_AFFERENZA
-- Table: PDG_PREVENTIVO
/*  59 */ ,d.STATO
/*  60 */ ,d.ANNOTAZIONI
/*  61 */ ,d.FL_RIBALTATO_SU_AREA
-- Table: PDG_PREVENTIVO_SPE_DET
/*  69 */ ,e.CD_LINEA_ATTIVITA
/*  70 */ ,e.TI_APPARTENENZA
/*  71 */ ,e.TI_GESTIONE
/*  72 */ ,e.CD_ELEMENTO_VOCE
/*  73 */ ,e.PG_ENTRATA
/*  74 */ ,e.DT_REGISTRAZIONE
/*  75 */ ,e.DESCRIZIONE
/*  76 */ ,e.STATO
/*  77 */ ,e.ORIGINE
/*  78 */ ,e.CATEGORIA_DETTAGLIO
/*  79 */ ,e.FL_SOLA_LETTURA
/* 120 */ ,e.CD_CENTRO_RESPONSABILITA_CLGS
/* 121 */ ,e.CD_LINEA_ATTIVITA_CLGS
/* 122 */ ,e.TI_APPARTENENZA_CLGS
/* 123 */ ,e.TI_GESTIONE_CLGS
/* 124 */ ,e.CD_ELEMENTO_VOCE_CLGS
/* 125 */ ,e.PG_SPESA_CLGS
,e.IM_RA_RCE
,e.IM_RB_RSE
,e.IM_RC_ESR
,e.IM_RD_A2_RICAVI
,e.IM_RE_A2_ENTRATE
,e.IM_RF_A3_RICAVI
,e.IM_RG_A3_ENTRATE
-- Table: ELEMENTO_VOCE
/* 132 */ ,f.FL_LIMITE_ASS_OBBLIG
/* 133 */ ,f.FL_VOCE_PERSONALE
/* 134 */ ,f.CD_PARTE
/* 135 */ ,f.DS_ELEMENTO_VOCE
,ft.CD_ELEMENTO_VOCE
,ft.DS_ELEMENTO_VOCE
,cfin.CD_CAPOCONTO_FIN
,cfin.DS_CAPOCONTO_FIN
-- Table: LINEA_ATTIVITA
/* 145 */ ,g.CD_TIPO_LINEA_ATTIVITA
/* 146 */ ,g.DENOMINAZIONE
/* 147 */ ,g.CD_GRUPPO_LINEA_ATTIVITA
/* 148 */ ,g.CD_FUNZIONE
/* 149 */ ,g.CD_NATURA
/* 150 */ ,g.DS_LINEA_ATTIVITA
/* 156 */ ,g.CD_CDR_COLLEGATO
/* 157 */ ,g.CD_LA_COLLEGATO
,i.ds_funzione
,j.ds_natura
,p.cd_progetto
,p.ds_progetto
from UNITA_ORGANIZZATIVA a, -- CDS di afferenza del PDG
UNITA_ORGANIZZATIVA b, -- UO di afferenza del PDG
CDR c, -- Cdr pdg
PDG_PREVENTIVO d, --Testata pdg
PDG_PREVENTIVO_ETR_DET e, -- Dettaglio
ELEMENTO_VOCE f, -- Elemento voce del dettaglio
ELEMENTO_VOCE fc, -- Categoria
CAPOCONTO_FIN cfin, -- Capoconto finanziario
ELEMENTO_VOCE ft, -- Titolo
LINEA_ATTIVITA g, --Linea di attivita del dettaglio
FUNZIONE i, -- Funzione
NATURA j, -- Natura
PROGETTO_PREV p-- Progetto
where
a.fl_cds = 'Y'
and b.CD_UNITA_PADRE=a.CD_UNITA_ORGANIZZATIVA
and b.fl_cds = 'N'
and c.CD_UNITA_ORGANIZZATIVA=b.CD_UNITA_ORGANIZZATIVA
and d.CD_CENTRO_RESPONSABILITA=c.CD_CENTRO_RESPONSABILITA
and e.ESERCIZIO=d.ESERCIZIO
and e.CD_CENTRO_RESPONSABILITA=c.CD_CENTRO_RESPONSABILITA
and e.STATO = 'Y'
and f.ESERCIZIO=d.ESERCIZIO
and f.TI_APPARTENENZA=e.TI_APPARTENENZA
and f.TI_GESTIONE=e.TI_GESTIONE
and f.CD_ELEMENTO_VOCE=e.CD_ELEMENTO_VOCE
and fc.ESERCIZIO=f.ESERCIZIO
and fc.TI_APPARTENENZA=f.TI_APPARTENENZA
and fc.TI_GESTIONE=f.TI_GESTIONE
and fc.CD_ELEMENTO_VOCE=f.CD_ELEMENTO_PADRE
and ft.ESERCIZIO=fc.ESERCIZIO
and ft.TI_APPARTENENZA=fc.TI_APPARTENENZA
and ft.TI_GESTIONE=fc.TI_GESTIONE
and ft.CD_ELEMENTO_VOCE=fc.CD_ELEMENTO_PADRE
and cfin.CD_CAPOCONTO_FIN(+)=f.CD_CAPOCONTO_FIN
and g.CD_CENTRO_RESPONSABILITA=c.CD_CENTRO_RESPONSABILITA
and g.CD_LINEA_ATTIVITA=e.CD_LINEA_ATTIVITA
and i.cd_funzione (+)= g.cd_funzione
and j.cd_natura = g.cd_natura
And g.pg_progetto = p.pg_progetto(+)
And (p.esercizio Is Null Or p.esercizio = d.ESERCIZIO)
);

   COMMENT ON TABLE "VP_DPDG_ETR_CDR"  IS 'Vista di stampa dei dettagli di entrata CDR
La vista non effettua controlli su STO';
