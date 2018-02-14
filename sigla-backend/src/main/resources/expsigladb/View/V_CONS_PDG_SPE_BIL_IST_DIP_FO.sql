--------------------------------------------------------
--  DDL for View V_CONS_PDG_SPE_BIL_IST_DIP_FO
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "V_CONS_PDG_SPE_BIL_IST_DIP_FO" ("ESERCIZIO", "CD_CENTRO_RESPONSABILITA", "DS_CDR", "CD_CLASSIFICAZIONE", "DS_CLASSIFICAZIONE", "NR_LIVELLO", "CD_LIVELLO1", "CD_LIVELLO2", "CD_LIVELLO3", "CD_LIVELLO4", "CD_LIVELLO5", "CD_LIVELLO6", "CD_LIVELLO7", "PG_MODULO", "CD_MODULO", "DS_MODULO", "CD_TIPO_MODULO", "DS_TIPO_MODULO", "PG_COMMESSA", "CD_COMMESSA", "DS_COMMESSA", "PG_PROGETTO", "CD_PROGETTO", "DS_PROGETTO", "CD_DIPARTIMENTO", "DS_DIPARTIMENTO", "IM_DEC_IST_INT", "IM_DEC_IST_EST", "IM_DEC_AREA_INT", "IM_DEC_AREA_EST", "TRATT_ECON_INT", "TRATT_ECON_EST", "IM_ACC_ALTRE_SP_INT", "IM_PREV_A2", "IM_PREV_A3", "CD_COFOG", "CD_MISSIONE") AS 
  Select PREV.ESERCIZIO,
       PREV.CD_CENTRO_RESPONSABILITA,
       CDR.DS_CDR,
       PREV.CD_CLASSIFICAZIONE,
       PREV.DS_CLASSIFICAZIONE,
       PREV.NR_LIVELLO,
       PREV.CD_LIVELLO1,
       PREV.CD_LIVELLO2,
       PREV.CD_LIVELLO3,
       PREV.CD_LIVELLO4,
       PREV.CD_LIVELLO5,
       PREV.CD_LIVELLO6,
       PREV.CD_LIVELLO7,
       MODU.PG_PROGETTO PG_MODULO,
       MODU.CD_PROGETTO CD_MODULO,
       MODU.DS_PROGETTO DS_MODULO,
       MODU.CD_TIPO_PROGETTO CD_TIPO_MODULO,
       TIPO_PROGETTO.DS_TIPO_PROGETTO DS_TIPO_MODULO,
       COMM.PG_PROGETTO PG_COMMESSA,
       COMM.CD_PROGETTO CD_COMMESSA,
       COMM.DS_PROGETTO DS_COMMESSA,
       PROG.PG_PROGETTO,
       PROG.CD_PROGETTO,
       PROG.DS_PROGETTO,
       PROG.CD_DIPARTIMENTO,
       DIP.DS_DIPARTIMENTO,
       PREV.IM_DEC_IST_INT,
       PREV.IM_DEC_IST_EST,
       PREV.IM_DEC_AREA_INT,
       PREV.IM_DEC_AREA_EST,
       PREV.TRATT_ECON_INT,
       PREV.TRATT_ECON_EST,
       PREV.IM_ACC_ALTRE_SP_INT,
       PREV.IM_PREV_A2,
       PREV.IM_PREV_A3,PREV.CD_COFOG,PREV.cd_missione
From   PARAMETRI_CNR PAR,
       PROGETTO_PREV MODU,
       PROGETTO_PREV COMM,
       PROGETTO_PREV PROG,
       TIPO_PROGETTO,
       DIPARTIMENTO DIP,
       CDR,
      (Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_INT)  IM_DEC_IST_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_EST)  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_INT) + Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_EST) IM_ACC_ALTRE_SP_INT,
              0 IM_PREV_A2,
              0 IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              Nvl(CLA.CDR_ACCENTRATORE, 'xxx') != (Select VAL01
                                                   From   CONFIGURAZIONE_CNR
                                                   Where  ESERCIZIO = 0 And
                                                          CD_UNITA_FUNZIONALE = '*' And
                                                          CD_CHIAVE_PRIMARIA = 'CDR_SPECIALE' And
                                                          CD_CHIAVE_SECONDARIA = 'CDR_PERSONALE') And
              AREA.CD_TIPO_UNITA != 'AREA'
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All  -- AREE
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0 IM_DEC_IST_INT,
              0 IM_DEC_IST_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_INT)  IM_DEC_AREA_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_EST)  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              0  IM_PREV_A2,
              0  IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              AREA.CD_TIPO_UNITA = 'AREA'
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All -- TRATTAMENTO ECONOMICO DEL PERSONALE
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0  IM_DEC_IST_INT,
              0  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_INT)   TRATT_ECON_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_EST)   TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              0  IM_PREV_A2,
              0  IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              Nvl(CLA.CDR_ACCENTRATORE, 'xxx') = (Select VAL01
                                      From   CONFIGURAZIONE_CNR
                                      Where  ESERCIZIO = 0 And
                                             CD_UNITA_FUNZIONALE = '*' And
                                             CD_CHIAVE_PRIMARIA = 'CDR_SPECIALE' And
                                             CD_CHIAVE_SECONDARIA = 'CDR_PERSONALE')
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All -- ANNO 2 E 3
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0  IM_DEC_IST_INT,
              0  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              Sum(PDG_SPE.IM_SPESE_A2) IM_PREV_A2,
              Sum(PDG_SPE.IM_SPESE_A3) IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE) PREV
Where PAR.ESERCIZIO = PREV.ESERCIZIO And
      PAR.FL_NUOVO_PDG = 'N' And
      PREV.CD_CENTRO_RESPONSABILITA = CDR.CD_CENTRO_RESPONSABILITA AND
      PREV.PG_PROGETTO              = MODU.PG_PROGETTO And
      PREV.ESERCIZIO                = MODU.ESERCIZIO And
      MODU.ESERCIZIO_PROGETTO_PADRE = COMM.ESERCIZIO And
      MODU.PG_PROGETTO_PADRE        = COMM.PG_PROGETTO And
      COMM.ESERCIZIO_PROGETTO_PADRE = PROG.ESERCIZIO And
      COMM.PG_PROGETTO_PADRE        = PROG.PG_PROGETTO And
      MODU.CD_TIPO_PROGETTO         = TIPO_PROGETTO.CD_TIPO_PROGETTO (+) And
      DIP.CD_DIPARTIMENTO           = PROG.CD_DIPARTIMENTO
UNION ALL
Select PREV.ESERCIZIO,
       PREV.CD_CENTRO_RESPONSABILITA,
       CDR.DS_CDR,
       PREV.CD_CLASSIFICAZIONE,
       PREV.DS_CLASSIFICAZIONE,
       PREV.NR_LIVELLO,
       PREV.CD_LIVELLO1,
       PREV.CD_LIVELLO2,
       PREV.CD_LIVELLO3,
       PREV.CD_LIVELLO4,
       PREV.CD_LIVELLO5,
       PREV.CD_LIVELLO6,
       PREV.CD_LIVELLO7,
       NULL PG_MODULO,
       NULL CD_MODULO,
       NULL DS_MODULO,
       PROG.CD_TIPO_PROGETTO CD_TIPO_MODULO,
       TIPO_PROGETTO.DS_TIPO_PROGETTO DS_TIPO_MODULO,
       PROG.PG_PROGETTO PG_COMMESSA,
       PROG.CD_PROGETTO CD_COMMESSA,
       PROG.DS_PROGETTO DS_COMMESSA,
       AREA_PROG.PG_PROGETTO,
       AREA_PROG.CD_PROGETTO,
       AREA_PROG.DS_PROGETTO,
       AREA_PROG.CD_DIPARTIMENTO,
       DIP.DS_DIPARTIMENTO,
       PREV.IM_DEC_IST_INT,
       PREV.IM_DEC_IST_EST,
       PREV.IM_DEC_AREA_INT,
       PREV.IM_DEC_AREA_EST,
       PREV.TRATT_ECON_INT,
       PREV.TRATT_ECON_EST,
       PREV.IM_ACC_ALTRE_SP_INT,
       PREV.IM_PREV_A2,
       PREV.IM_PREV_A3,PREV.CD_COFOG,PREV.CD_MISSIONE
From   PARAMETRI_CNR PAR,
       PROGETTO_PREV PROG,
       PROGETTO_PREV AREA_PROG,
       TIPO_PROGETTO,
       DIPARTIMENTO DIP,
       CDR,
      (Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_INT)  IM_DEC_IST_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_EST)  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_INT) + Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_EST) IM_ACC_ALTRE_SP_INT,
              0 IM_PREV_A2,
              0 IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              Nvl(CLA.CDR_ACCENTRATORE, 'xxx') != (Select VAL01
                                                   From   CONFIGURAZIONE_CNR
                                                   Where  ESERCIZIO = 0 And
                                                          CD_UNITA_FUNZIONALE = '*' And
                                                          CD_CHIAVE_PRIMARIA = 'CDR_SPECIALE' And
                                                          CD_CHIAVE_SECONDARIA = 'CDR_PERSONALE') And
              AREA.CD_TIPO_UNITA != 'AREA'
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All  -- AREE
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0 IM_DEC_IST_INT,
              0 IM_DEC_IST_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_INT)  IM_DEC_AREA_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_DECENTRATA_EST)  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              0  IM_PREV_A2,
              0  IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              AREA.CD_TIPO_UNITA = 'AREA'
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All -- TRATTAMENTO ECONOMICO DEL PERSONALE
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0  IM_DEC_IST_INT,
              0  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_INT)   TRATT_ECON_INT,
              Sum(PDG_SPE.IM_SPESE_GEST_ACCENTRATA_EST)   TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              0  IM_PREV_A2,
              0  IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA AND
              Nvl(CLA.CDR_ACCENTRATORE, 'xxx') = (Select VAL01
                                      From   CONFIGURAZIONE_CNR
                                      Where  ESERCIZIO = 0 And
                                             CD_UNITA_FUNZIONALE = '*' And
                                             CD_CHIAVE_PRIMARIA = 'CDR_SPECIALE' And
                                             CD_CHIAVE_SECONDARIA = 'CDR_PERSONALE')
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       Union All -- ANNO 2 E 3
       Select PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
              CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,
              0  IM_DEC_IST_INT,
              0  IM_DEC_IST_EST,
              0  IM_DEC_AREA_INT,
              0  IM_DEC_AREA_EST,
              0  TRATT_ECON_INT,
              0  TRATT_ECON_EST,
              0  IM_ACC_ALTRE_SP_INT,
              Sum(PDG_SPE.IM_SPESE_A2) IM_PREV_A2,
              Sum(PDG_SPE.IM_SPESE_A3) IM_PREV_A3,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE
       From   PDG_MODULO_SPESE PDG_SPE, V_CLASSIFICAZIONE_VOCI CLA, UNITA_ORGANIZZATIVA AREA
       Where  PDG_SPE.ID_CLASSIFICAZIONE = CLA.ID_CLASSIFICAZIONE AND
              PDG_SPE.CD_CDS_AREA = AREA.CD_UNITA_ORGANIZZATIVA
       Group By PDG_SPE.ESERCIZIO, PDG_SPE.CD_CENTRO_RESPONSABILITA, PDG_SPE.PG_PROGETTO,
                CD_CLASSIFICAZIONE, DS_CLASSIFICAZIONE, NR_LIVELLO, CD_LIVELLO1, CD_LIVELLO2, CD_LIVELLO3, CD_LIVELLO4, CD_LIVELLO5, CD_LIVELLO6, CD_LIVELLO7,PDG_SPE.CD_COFOG,PDG_SPE.CD_MISSIONE) PREV
Where PAR.ESERCIZIO = PREV.ESERCIZIO And
      PAR.FL_NUOVO_PDG = 'Y' And
      PREV.CD_CENTRO_RESPONSABILITA = CDR.CD_CENTRO_RESPONSABILITA AND
      PREV.PG_PROGETTO              = PROG.PG_PROGETTO And
      PREV.ESERCIZIO                = PROG.ESERCIZIO And
      PROG.ESERCIZIO_PROGETTO_PADRE = AREA_PROG.ESERCIZIO And
      PROG.PG_PROGETTO_PADRE        = AREA_PROG.PG_PROGETTO And
      PROG.CD_TIPO_PROGETTO         = TIPO_PROGETTO.CD_TIPO_PROGETTO (+) And
      DIP.CD_DIPARTIMENTO           = AREA_PROG.CD_DIPARTIMENTO;
