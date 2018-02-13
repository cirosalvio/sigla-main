--------------------------------------------------------
--  DDL for View PRT_REND_FIN_ENT_GEST_PIE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "PRT_REND_FIN_ENT_GEST_PIE" ("ESERCIZIO", "ESERCIZIO_RES", "ST_DIP_YN", "SCIENT_YN", "PESO_DIP", "NUM_DIP", "DIP", "DS_DIPARTIMENTO", "CDS", "DS_CDS", "CDR", "CD_LINEA_ATTIVITA", "CD_ELEMENTO_VOCE", "CD_CLASSIFICAZIONE", "DS_CLASSIFICAZIONE", "NR_LIVELLO", "CD_LIVELLO1", "CD_LIVELLO1_ROM", "DS_LIVELLO1", "CD_LIVELLO2", "DS_LIVELLO2", "CD_LIVELLO3", "DS_LIVELLO3", "CD_LIVELLO4", "CD_LIVELLO5", "CD_LIVELLO6", "CD_LIVELLO7", "PREV_INIZIALE", "VAR_PIU_COMP", "VAR_MENO_COMP", "RISCOSSIONI_COMP", "IM_OBBL_ACC_COMP", "RESIDUI_INIZIALI", "VAR_PIU_RES", "VAR_MENO_RES", "RESIDUI_FINALI", "RISCOSSIONI_RES") AS 
  SELECT  ESERCIZIO,
        ESERCIZIO_RES,
        -- ST_DIP_YN
        DECODE(Get_Dip_From_Modulo(ESERCIZIO, PG_PROGETTO,'X'), 'TA', 'Y', 'ET', 'Y', 'AG', 'Y', 'ME', 'Y', 'SV', 'Y', 'PM', 'Y', 'MD', 'Y', 'SP', 'Y', 'ICT', 'Y',
                         'IC', 'Y', 'PC', 'Y', 'DG', 'Y', 'SAC', DECODE (CD_LIVELLO1, '4', 'N', '5', 'N', 'Y')),
        -- SCIENT_YN
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'TA', 'Y', 'ET', 'Y', 'AG', 'Y', 'ME', 'Y', 'SV', 'Y', 'PM', 'Y', 'MD','Y', 'SP', 'Y', 'ICT', 'Y', 'IC', 'Y', 'PC', 'Y', 'DG', 'Y', 'SAC', 'N', 'Y') SCIENT_YN,
        -- NEW Peso dip (anche 14 per Fondi e Partite di Giro)
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'TA', '01', 'ET', '02', 'AG', '03', 'ME', '04', 'SV', '05', 'PM', '06', 'MD', '07', 'SP', '08', 'ICT', '09',
                    'IC', '10', 'PC', '11', 'DG', '12', 'SAC', DECODE (CD_LIVELLO1, '4', '14', '5', '14', '13')),
        -- NUM_DIP DA 01 A 13 (PER NUMERAZIONE)
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'TA', '01', 'ET', '02', 'AG', '03', 'ME', '04', 'SV', '05', 'PM', '06', 'MD', '07', 'SP', '08', 'ICT', '09',
                    'IC', '10', 'PC', '11', 'DG', '12', 'SAC', '13'),
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'TA', 'TA', 'ET', 'ET', 'AG', 'AG', 'ME','ME', 'SV', 'SV', 'PM', 'PM', 'MD', 'MD', 'SP', 'SP', 'ICT','ICT', 'IC', 'IC',
                    'PC', 'PC', 'DG','DG', 'SAC', DECODE (CD_LIVELLO1, '4', 'SAC_FONDI_PGIRO', '5', 'SAC_FONDI_PGIRO', 'SAC')),
        Cnrctb020.GETDESDIPARTIMENTO (Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X')) DS_DIPARTIMENTO,
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'SAC', PRT_REND_FIN_ENT_GEST_DET_PIE.CD_PROPRIO_UNITA, PRT_REND_FIN_ENT_GEST_DET_PIE.CD_UNITA_ORGANIZZATIVA) PROPRIO_UO,
        DECODE(Get_Dip_From_Modulo(ESERCIZIO,PG_PROGETTO,'X'), 'SAC', PRT_REND_FIN_ENT_GEST_DET_PIE.UO_DS_UNITA_ORGANIZZATIVA, PRT_REND_FIN_ENT_GEST_DET_PIE.CDS_DS_UNITA_ORGANIZZATIVA) DES_PROPRIO,
        CDR,
        CD_LINEA_ATTIVITA,
        CD_ELEMENTO_VOCE,
        CD_CLASSIFICAZIONE,
        DS_CLASSIFICAZIONE,
        NR_LIVELLO,
        CD_LIVELLO1,
        trim(TO_CHAR(CD_LIVELLO1, 'RM')),
        DS_LIVELLO1,
        CD_LIVELLO2,
        DS_LIVELLO2,
        CD_LIVELLO3,
        DS_LIVELLO3,
        CD_LIVELLO4,
        CD_LIVELLO5,
        CD_LIVELLO6,
        CD_LIVELLO7,
        -- STANZIAMENTO DI COMPETENZA
        PREV_INIZIALE,
        -- VARIAZIONI IN PIU' (TOTALI, COMPRESE LE NEGATIVE)
        DECODE(VAR_PIU_COMP - VAR_MENO_COMP, ABS(VAR_PIU_COMP - VAR_MENO_COMP), VAR_PIU_COMP - VAR_MENO_COMP, 0),
        -- VARIAZIONI IN MENO (TOTALI, COMPRESE LE POSITIVE)
        DECODE(VAR_PIU_COMP - VAR_MENO_COMP, ABS(VAR_PIU_COMP - VAR_MENO_COMP), 0, ABS(VAR_PIU_COMP - VAR_MENO_COMP)),
        -- RISCOSSO A COMPETENZA
        RISCOSSIONI_COMP,
        -- ACCERTATO A COMPETENZA
        IM_OBBL_ACC_COMP,
        -- RESIDUI_INIZIALI (RICOSTRUITI DAI FINALI +/- LE VARIAZIONI AI PROPRI => SOLO 2006) + STANZIAMENTO RESIDUO INIZIALE  (col. 10)
        RESIDUI_ASSESTATO - VAR_PIU_RES + VAR_MENO_RES,
        -- VARIAZIONI IN PIU' AI RESIDUI (SOLO 2006)
        VAR_PIU_RES,
        -- VARIAZIONI IN MENO AI RESIDUI (SOLO 2006)
        VAR_MENO_RES,
        -- RESIDUI FINALI
        RESIDUI_ASSESTATO,
        -- RISCOSSIONI A RESIDUO
        RISCOSSIONI_RES
FROM PRT_REND_FIN_ENT_GEST_DET_PIE
;
