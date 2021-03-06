--------------------------------------------------------
--  DDL for View V_MESSAGGI_UTENTE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "V_MESSAGGI_UTENTE" ("CD_UTENTE", "PG_MESSAGGIO", "DS_MESSAGGIO", "SERVER_URL", "PRIORITA", "SOGGETTO", "CORPO", "DT_INIZIO_VALIDITA", "DT_FINE_VALIDITA", "DACR", "UTCR", "DUVA", "UTUV", "PG_VER_REC", "VISIONATO", "LETTO") AS 
  (Select
--
-- Date: 23/08/2005
-- Version: 1.0
--
-- Vista per la visualizzazione dei messaggi per utente
--
-- History:
--
-- Date: 23/08/2005
-- Version: 1.0
-- Creazione
--
-- Body:
--
UTENTE.CD_UTENTE, MESSAGGIO.PG_MESSAGGIO, MESSAGGIO.DS_MESSAGGIO,
MESSAGGIO.SERVER_URL, MESSAGGIO.PRIORITA, MESSAGGIO.SOGGETTO,
MESSAGGIO.CORPO, MESSAGGIO.DT_INIZIO_VALIDITA, MESSAGGIO.DT_FINE_VALIDITA,
MESSAGGIO.DACR, MESSAGGIO.UTCR, MESSAGGIO.DUVA, MESSAGGIO.UTUV, MESSAGGIO.PG_VER_REC,
(Select Decode(Count(0), 0, 'N', 'Y') From MESSAGGIO_VISIONATO
 Where MESSAGGIO_VISIONATO.PG_MESSAGGIO = MESSAGGIO.PG_MESSAGGIO
 And MESSAGGIO_VISIONATO.CD_UTENTE = UTENTE.CD_UTENTE) VISIONATO,
(Select Decode(Count(0), 0, 'N', 'Y') From MESSAGGIO_LETTO
 Where MESSAGGIO_LETTO.PG_MESSAGGIO = MESSAGGIO.PG_MESSAGGIO
 And MESSAGGIO_LETTO.CD_UTENTE = UTENTE.CD_UTENTE) LETTO
From MESSAGGIO, UTENTE
Where MESSAGGIO.CD_UTENTE Is Null
And   (MESSAGGIO.DT_INIZIO_VALIDITA IS NULL OR MESSAGGIO.DT_INIZIO_VALIDITA < SYSDATE )
And   (MESSAGGIO.DT_FINE_VALIDITA IS NULL OR MESSAGGIO.DT_FINE_VALIDITA > SYSDATE )
Union All
Select
MESSAGGIO.CD_UTENTE, MESSAGGIO.PG_MESSAGGIO, MESSAGGIO.DS_MESSAGGIO,
MESSAGGIO.SERVER_URL, MESSAGGIO.PRIORITA, MESSAGGIO.SOGGETTO,
MESSAGGIO.CORPO, MESSAGGIO.DT_INIZIO_VALIDITA, MESSAGGIO.DT_FINE_VALIDITA,
MESSAGGIO.DACR, MESSAGGIO.UTCR, MESSAGGIO.DUVA, MESSAGGIO.UTUV, MESSAGGIO.PG_VER_REC,
(Select Decode(Count(0), 0, 'N', 'Y') From MESSAGGIO_VISIONATO
 Where MESSAGGIO_VISIONATO.PG_MESSAGGIO = MESSAGGIO.PG_MESSAGGIO
 And MESSAGGIO_VISIONATO.CD_UTENTE = MESSAGGIO.CD_UTENTE) VISIONATO,
(Select Decode(Count(0), 0, 'N', 'Y') From MESSAGGIO_LETTO
 Where MESSAGGIO_LETTO.PG_MESSAGGIO = MESSAGGIO.PG_MESSAGGIO
 And MESSAGGIO_LETTO.CD_UTENTE = MESSAGGIO.CD_UTENTE) LETTO
From MESSAGGIO
Where MESSAGGIO.CD_UTENTE Is Not Null
And   (MESSAGGIO.DT_INIZIO_VALIDITA IS NULL OR MESSAGGIO.DT_INIZIO_VALIDITA < SYSDATE )
And   (MESSAGGIO.DT_FINE_VALIDITA IS NULL OR MESSAGGIO.DT_FINE_VALIDITA > SYSDATE ) )
;
