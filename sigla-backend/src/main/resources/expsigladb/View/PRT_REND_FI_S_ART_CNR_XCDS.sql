--------------------------------------------------------
--  DDL for View PRT_REND_FI_S_ART_CNR_XCDS
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "PRT_REND_FI_S_ART_CNR_XCDS" ("ESER", "CDS", "PARTE", "DS_PARTE", "TITOLO", "DS_TITOLO", "CATEGORIA", "DS_CATEGORIA", "SEZIONE", "DS_SEZIONE", "CAPITOLO", "DS_CAPITOLO", "ARTICOLO", "DS_ARTICOLO", "INIZIALE", "VAR_PIU", "VAR_MENO", "ASSESTATO", "IMPEGNATO", "ACCREDITAMENTI", "REGOLARIZZATI", "DA_ACCRED", "IN_MENO", "IN_PIU", "INIZIALE_R", "VAR_PIU_R", "VAR_MENO_R", "ASSESTATO_R", "ACCREDITAMENTI_R", "REGOLARIZZATI_R", "IN_PIU_R", "IN_MENO_R", "TOT_ACCRED", "TOT_DA_ACCRED", "CDS_OR") AS 
  SELECT
--
-- Date: 13/12/2006
-- Version: 1.1
--
-- Vista di stampa Rendiconto Finanziario CDS Spese X CDS(SOLO importi DIVERSI DA zero)
--
-- History:
--
-- Date: 02/03/2004
-- Version: 1.0
-- Creazione
--
-- Date: 13/12/2006
-- Version: 1.1
-- Aggiunto il campo per il recupero degli importi delle variazioni di bilancio
-- di tipo REGOLARIZZAZIONE_CONTABILE
--
-- Body
--
ESER,CDS,PARTE,DS_PARTE,TITOLO,DS_TITOLO,CATEGORIA,DS_CATEGORIA,
SEZIONE,DS_SEZIONE,CAPITOLO,DS_CAPITOLO,ARTICOLO,DS_ARTICOLO,INIZIALE,
VAR_PIU,VAR_MENO,ASSESTATO,IMPEGNATO,ACCREDITAMENTI,REGOLARIZZATI,
--IN_PIU,
DA_ACCRED,IN_MENO,IN_PIU,INIZIALE_R,VAR_PIU_R,VAR_MENO_R,ASSESTATO_R,
ACCREDITAMENTI_R,REGOLARIZZATI_R,IN_PIU_R,IN_MENO_R,TOT_ACCRED,TOT_DA_ACCRED,CDS_OR
FROM
PRT_REND_FI_S_CNR_art_XCDS_ALL
WHERE
(INIZIALE+VAR_PIU+VAR_MENO+ACCREDITAMENTI+INIZIALE_R+VAR_PIU_R+VAR_MENO_R+ACCREDITAMENTI_R)<>0
;

   COMMENT ON TABLE "PRT_REND_FI_S_ART_CNR_XCDS"  IS 'Vista di stampa Rendiconto Finanziario CDS Spese X CDS(SOLO importi DIVERSI DA zero)';
