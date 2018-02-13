--------------------------------------------------------
--  DDL for View VPG_REVERSALE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "VPG_REVERSALE" ("ID", "CHIAVE", "SEQUENZA", "DESCRIZIONE", "CD_CDS", "ESERCIZIO", "PG_REVERSALE", "TI_RECORD", "ID_RIGA", "DT_EMISSIONE", "TI_REVERSALE", "TI_COMPETENZA_RESIDUO", "DS_CDS", "CD_UO_ORIGINE", "DS_UO_CDS", "CAB", "ABI", "NUMERO_CONTO", "CIN", "INTESTAZIONE", "DS_ABICAB", "VIA", "CAP", "DS_COMUNE", "CD_PROVINCIA", "IM_REVERSALE", "IM_REVERSALE_LETTERE", "CD_TERZO", "DENOMINAZIONE_SEDE", "VIA_SEDE", "CAP_COMUNE_SEDE", "DS_COMUNE_SEDE", "CD_PV_TERZO", "CODICE_FISCALE", "CD_TIPO_DOCUMENTO_AMM", "PG_DOC_AMM", "IM_REVERSALE_RIGA", "IM_REVERSALE_RIGA_LETTERE", "DS_REVERSALE_RIGA", "ESERCIZIO_ORI_ACCERTAMENTO", "PG_ACCERTAMENTO", "DS_ACCERTAMENTO", "CD_VOCE", "DS_VOCE", "CD_SOSPESO", "IM_ASSOCIATO", "DT_REGISTRAZ_SOSPESO", "UTCR", "DACR", "CD_SIOPE", "DS_SIOPE", "IM_SIOPE", "IBAN", "CD_CUP", "DS_CUP", "IM_CUP", "STATO") AS 
  SELECT
--
-- Date: 10/05/2007
-- Version: 1.4
--
-- Vista per la stampa massiva delle reversali
-- Protocollo VPG
--
-- History:
--
-- Date: 11/02/2003
-- Version: 1.0
-- Creazione
--
-- Date: 04/03/2003
-- Version: 1.1
-- Gestione varchar2 con size > 200
--
-- Date: 20/01/2004
-- Version: 1.2
-- Estrazione CIN dalla banca (richiesta n. 697)
--
-- Date: 19/07/2006
-- Version: 1.3
-- Gestione Impegni/Accertamenti Residui:
-- gestito il nuovo campo ESERCIZIO_ORIGINALE
--
-- Date: 10/05/2007
-- Version: 1.4
-- Aggiunti i campi relativi ai codici siope
--
-- Body:
--
ID,
CHIAVE,
SEQUENZA,
DESCRIZIONE,
ATTRIBUTO_1,   -- cd_cds
IMPORTO_1,	   -- esercizio
IMPORTO_2,	   -- pg_reversale
TIPO,		   -- ti_record
ATTRIBUTO_2,   -- id_riga
DATA_1, 	   -- DT_EMISSIONE
ATTRIBUTO_3,   -- ti_reversale
ATTRIBUTO_4,   -- ti_competenza_residuo
ATTRIBUTO_LONG_1,   -- ds_cds
ATTRIBUTO_6,   -- CD_UO_ORIGINE
ATTRIBUTO_LONG_2,   -- ds_uo_cds
ATTRIBUTO_8,   -- CAB
ATTRIBUTO_9,   -- ABI
ATTRIBUTO_10,  -- NUMERO_CONTO
ATTRIBUTO_32,  --CIN,
ATTRIBUTO_11,  -- INTESTAZIONE
ATTRIBUTO_12,  -- DS_ABICAB
ATTRIBUTO_13,  -- VIA
ATTRIBUTO_14,  -- CAP
ATTRIBUTO_30,  -- DS_COMUNE
ATTRIBUTO_15,  -- CD_PROVINCIA
IMPORTO_3,	   -- IM_REVERSALE
ATTRIBUTO_LONG_3,  -- IM_REVERSALE_LETTERE
IMPORTO_4,	   -- CD_TERZO
ATTRIBUTO_17,  -- DENOMINAZIONE_SEDE
ATTRIBUTO_18,  -- VIA_SEDE || ' ' || l.NUMERO_CIVICO_SEDE
ATTRIBUTO_19,  -- CAP_COMUNE_SEDE
ATTRIBUTO_31,  -- DS_COMUNE_SEDE
ATTRIBUTO_20,  -- CD_PV_TERZO
ATTRIBUTO_21,  -- CODICE_FISCALE
ATTRIBUTO_22,  -- CD_TIPO_DOCUMENTO_AMM
IMPORTO_5, 	   -- PG_DOC_AMM
IMPORTO_6,	   -- IM_REVERSALE_RIGA
ATTRIBUTO_LONG_4,  -- IM_REVERSALE_RIGA_LETTERE
ATTRIBUTO_LONG_5,  -- DS_REVERSALE_RIGA
IMPORTO_9,	   -- ESERCIZIO_ORI_ACCERTAMENTO
IMPORTO_7,	   -- PG_ACCERTAMENTO
ATTRIBUTO_LONG_6,  -- DS_ ACCERTAMENTO
ATTRIBUTO_26,  -- cd_voce
ATTRIBUTO_LONG_7,  -- ds_voce
ATTRIBUTO_28,  -- cd_sospeso
IMPORTO_8,	   -- im_associato
DATA_2,		   -- DT_REGISTRAZ_SOSPESO
ATTRIBUTO_29,  -- UTCR
DATA_3		   -- DACR
,ATTRIBUTO_33   --CD_SIOPE
,ATTRIBUTO_34   --DS_SIOPE
,IMPORTO_10     --IM_SIOPE
,ATTRIBUTO_35   --IBAN
,ATTRIBUTO_36   --CD_CUP
,ATTRIBUTO_37   --DS_CUP
,IMPORTO_11     --IM_CUP
,ATTRIBUTO_38  -- STATO
from TMP_REPORT_GENERICO trp;

   COMMENT ON TABLE "VPG_REVERSALE"  IS 'Vista per la stampa massiva delle reversali Protocollo VPG';
