package it.cnr.contab.pdg00.comp;

// ***************************************************************
// *
// *                    Source Code Generated by
// *                 Computer Associates International, Inc.
// *
// *    Class: IPdGPreventivoDettagliMgr
// *    Package: it.cnr.contab.pdg00.comp
// *    Date:  Wed Oct 31 11:37:37 GMT 2001
// *    User:  cnr004
// *
// *    Copyright 2000, Computer Associates International, Inc.
// *    All rights reserved.
// *
// ***************************************************************
// *
// * Change History
// *
// * Initials    Date       Comments
// *+--------+------------+--------------------------------------------------
// *|        |            |
// *+--------+------------+--------------------------------------------------
// *|        |            |
// *+--------+------------+--------------------------------------------------

import it.cnr.contab.pdg00.bulk.Pdg_preventivo_spe_detBulk;
import it.cnr.contab.config00.sto.bulk.CdrBulk;
import it.cnr.contab.pdg00.bulk.Pdg_preventivo_etr_detBulk;
import it.cnr.contab.config00.pdcfin.bulk.Elemento_voceBulk;
import it.cnr.contab.pdg00.bulk.Pdg_preventivo_detBulk;
import it.cnr.contab.config00.latt.bulk.WorkpackageBulk;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

public interface IPdGPreventivoDettagliMgr extends it.cnr.jada.comp.ICRUDMgr
{


/** 
  *  Tutti i controlli superati
  *    PreCondition:
  *      Nessun'altra precondizione � verificata
  *    PostCondition:
  *      ll dettaglio viene marcato con CATEGORIA 'SIN', FL_SOLA_LETTURA = false e salvato
  *  spesa relativa altra UO
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato qualche colonna relativa a 'spese verso altra UO' (J,L,R,T,AD,AF,AM,AD); il PDG del CDR scelto � aperto (stato uguale a A, B, D o E)
  *    PostCondition:
  *      Viene creata nel PDG del cdr scelto (servente) un nuovo dettaglio di spesa valorizzazzato come segue:
  *      dettaglioServente.esercizio = dettaglioServito.esercizio
  *      dettaglioServente.cdr = cdrServente
  *      dettaglioServente.appartenenza = dettaglioServito.appartenenza
  *      dettaglioServente.gestione = dettaglioServito.gestione
  *      dettaglioServente.voce = dettaglioServito.voce
  *      dettaglioServente.clgs = dettaglioServito
  *      dettaglioServente.CATEGORIA = 'CAR'
  *      dettaglioServente.STATO = 'X'
  *      dettaglioServente.lineaDiAttivita =  Linea_attivitaComponent.creaLineaAttivitaSAUO(cdrServente,dettaglioServito.lineaDiAttivita)
  *      dettaglioServente.U = dettaglioServito.J / L / R / T (quello valorizzato)
  *      dettaglioServente.AG = dettaglioServito.AD / AF (quello valorizzato)
  *      dettaglioServente.AP = dettaglioServito.AM / AD (quello valorizzato)
  *      il dettaglio servito viene marcato con CATEGORIA 'SCR', quello servente con categotria 'CAR', e viene messo in STATO 'X'
  *  PDG servente gi� chiuso
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato qualche colonna riferita ad un'altro cdr (CLGS != NULL) e il PDG del CDR scelto � gi� "chiuso" (checkChiusuraPdg genera una Exception)
  *    PostCondition:
  *      Viene generata una ApplicationException con il messaggio "Il PDG del <cdr> servente � stato gi� chiuso
  *  costi senza spese
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato la colonna P, AB o AI ; il PDG del CDR scelto � modificabile (stato A, B, D o E), la voce � "Prestazioni da struttura dell'ente" (isVoceCSSAC() ritorna true)
  *    PostCondition:
  *      Viene creata per il cdr servente un nuovo dettaglio di entrata valorizzazzato come segue:
  *      entrataServente.esercizio = dettaglioServito.esercizio
  *      entrataServente.cdr = cdrServente
  *      entrataServente.appartenenza = dettaglioServito.appartenenza
  *      entrataServente.gestione = dettaglioServito.gestione
  *      entrataServente.voce = getVoceRicaviFigurativi()
  *      entrataServente.clgs = dettaglioServito
  *      entrataServente.CATEGORIA = 'CAR'
  *      entrataServente.STATO = 'X'
  *      entrataServente.lineaDiAttivita = Linea_attivitaComponent.creaLineaAttivitaCSSAC(cdrServente,dettaglioServito.lineaDiAttivita)
  *      entrataServente.B = dettaglioServito.P
  *      entrataServente.D = dettaglioServito.AB 
  *      entrataServente.F = dettaglioServito.AI
  *      
  *      il dettaglio servito viene marcato con CATEGORIA 'SCR' e viene messo in STATO 'X'
  *      
  *  costi senza spese, voce non valida
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato la colonna P, AB o AI, la voce NON � "Prestazioni da struttura dell'ente" (isVoceCSSAC() ritorna false)
  *    PostCondition:
  *      Viene generata una ApplicationException con il messaggio "La voce del piano dei conti deve essere 'Prestazioni da struttura dell'ente'"
  *  spesa relativa altra UO, campi non validi
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato qualche colonna relativa a 'spese verso altra UO' (J,L,R,T,AD,AF,AM,AD)
  *      sono compilati pi� di uno alla volta di
  *      - J, L, R, T
  *      - AD, AF
  *      - AM, AD
  *      oppure sono compilati altri campi non relativi ad altra UO eccetto H/AA/AH
  *    PostCondition:
  *      Viene generata una ApplicationException.
  *  costi senza spese, campi non validi
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e l'utente ha compilato la colonna P, AB o AI e qualche altra colonna non relativa a "costi senza spese verso altro CDR"
  *    PostCondition:
  *      Viene generata una ApplicationException.
  *  spesa relativa altra UO per il personale, linea di attivita non valida
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e 
  *      l'utente ha compilato qualche colonna relativa a 'spese verso altra UO' (J,L,R,T,AD,AF,AM,AD) e
  *      isVoceSAUOP(dettaglio.voce) e
  *      il PDG del CDR scelto � aperto (stato uguale a A, B, D o E)
  *      la linea di attivita ha natura o funzione diverse da uno
  *    PostCondition:
  *      Viene generata una eccezione con messaggio: "Le spese per personale devono avere una linea di attivit� con natura e funzione 1"
  *  spesa relativa altra UO per il personale
  *    PreCondition:
  *      Viene richiesto il salvataggio di una riga di spese del PDG e 
  *      l'utente ha compilato qualche colonna relativa a 'spese verso altra UO' (J,L,R,T,AD,AF,AM,AD) e
  *      isVoceSAUOP(dettaglio.voce) = true e
  *      il PDG del CDR scelto � aperto (stato uguale a A, B, D o E)
  *      la linea di attivita ha natura o funzione uguale a uno
  *    PostCondition:
  *      Viene creata nel PDG del cdr scelto (servente) un nuovo dettaglio di spesa valorizzazzato come segue:
  *      dettaglioServente.esercizio = dettaglioServito.esercizio
  *      dettaglioServente.cdr = cdrServente
  *      dettaglioServente.appartenenza = dettaglioServito.appartenenza
  *      dettaglioServente.gestione = dettaglioServito.gestione
  *      dettaglioServente.voce = dettaglioServito.voce
  *      dettaglioServente.clgs = dettaglioServito
  *      dettaglioServente.CATEGORIA = 'CAR'
  *      dettaglioServente.STATO = 'X'
  *      dettaglioServente.lineaDiAttivita =  Linea_attivitaComponent.creaLineaAttivitaSAUOP(cdrServente)
  *      dettaglioServente.U = dettaglioServito.J / L / R / T (quello valorizzato)
  *      dettaglioServente.AG = dettaglioServito.AD / AF (quello valorizzato)
  *      dettaglioServente.AP = dettaglioServito.AM / AD (quello valorizzato)
  *      il dettaglio servito viene marcato con CATEGORIA 'SCR', quello servente con categotria 'CAR', e viene messo in STATO 'X'
  *  PDG gi� chiuso
  *    PreCondition:
  *      checkChiusuraPdg genera una eccezione
  *    PostCondition:
  *      Viene lasciata uscire l'eccezione
  *  linea di attivit� di natura 5, cdr non collegato ad area
  *    PreCondition:
  *      Linea di attivit� con natura 5 e
  *      CDR afferente ad UO non collegata ad area
  *    PostCondition:
  *      Genera una ApplicationException con il messaggio "Non � possibile creare dettagli con natura 5 perch� il CDR non � collegato ad area attraverso la sua unit� organizzativa"
  *  linea di attivit� di natura 5, cdr area
  *    PreCondition:
  *      Linea di attivit� con natura 5 e
  *      CDR afferente UO di tipo area
  *    PostCondition:
  *      Genera una ApplicationException con il messaggio "Non � possibile creare dettagli con natura 5 perch� il CDR appartiene ad un'area"
  *  linea di attivit� di natura 5, scarico verso altra UO/CDR
  *    PreCondition:
  *      Linea di attivit� con natura 5
  *      e l'utente ha compilato qualche colonna relativa a 'spese verso altra UO' (J,L,R,T,AD,AF,AM,AD); il PDG del CDR scelto � aperto (stato uguale a A, B, D o E) o verso altro CDR (P, AB o AI)
  *    PostCondition:
  *      Genera una ApplicationException con il messaggio "Non � possibile scaricare costi su altra UO o CDR con natura 5"
  *  Dettaglio di spesa/entrata con importo negativo
  *    PreCondition:
  *      Viene richiesto l'inserimento di un dettaglio di spesa/entrata con importo negativo
  *    PostCondition:
  *      Viene generata una ApplicationException con il messaggio "non e' possibile inserire importi negativi"
  *  linea di attivit� di natura 5, dettaglio di entrata
  *    PreCondition:
  *      Linea di attivit� con natura 5 e
  *      il dettaglio specificato � un dettaglio di entrata
  *    PostCondition:
  *      Genera un ApplicationException con il messaggio "Non � possibile creare voci di entrata con natura 5"
  *  dtl costo del personale - CDR non � CDR PERSONALE, CDR_SERVIZIO_ENTE - tempo indeterminato
  *    PreCondition:
  *      Il CDR � diverso da CDR PERSONALE e da CDR_SERVIZIO_ENTE.
  *      Nell'interfaccia stipendi � presente almeno un dettaglio stipendiale contenente la voce di spesa Y diversa da voce TFR (identificata in configurazione).
  *      Il dipendente specificato nel dettaglio stipendiale, sulla voce Y, � un dipendente a tempo indeterminato.
  *    PostCondition:
  *      Creazione/Modifica di dettaglio del PDG di un qualsiasi CDR diverso da  CDR DEL PERSONALE e da CDR_SERVIZIO_ENTE, su voce Y diversa da TFR  nel caso su Y esista in interfaccia stipendi un dipendente a tempo indeterminato
  *      Le colonne L e V non saranno imputabile direttamente
  *  dtl costo del personale - CDR � CDR PERSONALE  - tempo indeterminato
  *    PreCondition:
  *      Il CDR � uguale a CDR PERSONALE.
  *      Nell'interfaccia stipendi � presente almeno un dettaglio stipendiale contenente la voce di spesa Y diversa da voce TFR (identificata in configurazione).
  *      Il dipendente specificato nel dettaglio stipendiale, sulla voce Y, � un dipendente a tempo indeterminato.
  *    PostCondition:
  *      Creazione/Modifica di dettaglio del PDG del CDR DEL PERSONALE, su voce Y diversa da TFR nel caso su Y esista in interfaccia stipendi un dipendente a tempo indeterminato.
  *      La colonna K non sar� imputabile direttamente.
  *  dtl costo del personale - CDR non � CDR PERSONALE, CDR_SERVIZIO_ENTE - tempo determinato
  *    PreCondition:
  *      Il CDR � diverso da CDR PERSONALE e da CDR_SERVIZIO_ENTE.
  *      Nell'interfaccia stipendi � presente almeno un dettaglio stipendiale contenente la voce di spesa Y diversa da voce TFR (identificata in configurazione).
  *      Il dipendente specificato nel dettaglio stipendiale, sulla voce Y, � un dipendente a tempo determinato.
  *    PostCondition:
  *      Creazione/Modifica di dettaglio del PDG di un qualsiasi CDR diverso da  CDR DEL PERSONALE e da CDR_SERVIZIO_ENTE, su voce Y diversa da TFR  nel caso su Y esista in interfaccia stipendi un dipendente a tempo determinato
  *      Le colonne O e V non saranno imputabile direttamente
  *  dtl costo del personale - CDR � CDR PERSONALE  - tempo determinato
  *    PreCondition:
  *      Il CDR � uguale a CDR PERSONALE.
  *      Nell'interfaccia stipendi � presente almeno un dettaglio stipendiale contenente la voce di spesa Y diversa da voce TFR (identificata in configurazione).
  *      Il dipendente specificato nel dettaglio stipendiale, sulla voce Y, � un dipendente a tempo determinato.
  *    PostCondition:
  *      Creazione/Modifica di dettaglio del PDG del CDR DEL PERSONALE, su voce Y diversa da TFR nel caso su Y esista in interfaccia stipendi un dipendente a tempo determinato.
  *      La colonna O non sar� imputabile direttamente.
  *  Check imputabilit� importi su voci del personale_1
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio NON � il conto TFR
  *      Il PDG NON � del personale o quello speciale di servizio ENTE
  *      La voce specificata nel dettaglio del PDG � presente in COSTO DEL DIPENDENTE su almeno un dipendente a tempo INDETERMINATO
  *      Le colonne L o V sono valorizzate
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulle colonne L o V
  *  Check imputabilit� importi su voci del personale_2
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio NON � il conto TFR
  *      Il PDG NON � del personale o quello speciale di servizio ENTE
  *      La voce specificata nel dettaglio del PDG � presente in COSTO DEL DIPENDENTE su almeno un dipendente a tempo DETERMINATO
  *      Le colonne O o V sono valorizzate
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulle colonne O o V
  *  Check imputabilit� importi su voci del personale_3
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio NON � il conto TFR
  *      Il PDG � quello del personale
  *      La voce specificata nel dettaglio del PDG � presente in COSTO DEL DIPENDENTE su almeno un dipendente a tempo INDETERMINATO
  *      La colonna K � valorizzata
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulla colonna K
  *      
  *  Check imputabilit� importi su voci del personale_4
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio NON � il conto TFR
  *      Il PDG � quello del personale
  *      La voce specificata nel dettaglio del PDG � presente in COSTO DEL DIPENDENTE su almeno un dipendente a tempo DETERMINATO
  *      La colonna O � valorizzate
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulla colonna O
  *  Check imputabilit� importi su voci del personale_5
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio � il conto TFR
  *      Il PDG NON � del personale o quello speciale di servizio ENTE
  *      Le colonne O o V sono valorizzate
  *      
  *      
  *      
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulle colonne O o V
  *  Check imputabilit� importi su voci del personale_6
  *    PreCondition:
  *      Il dettaglio � di spesa
  *      Il conto su cui sto registrando il dettaglio � il conto TFR
  *      Il PDG � del personale
  *      La colonna O � valorizzata
  *       
  *    PostCondition:
  *      Viene generata una eccezione che segnala la non imputabilit� sulla colonna O
  *      
  *
  * @param userContext UserContext in uso.
  * @param bulk {@link it.cnr.contab.pdg00.bulk.Pdg_preventivo_detBulk } da salvare.
  *
  * @return {@link it.cnr.contab.pdg00.bulk.Pdg_preventivo_detBulk } salvato
  */
public abstract OggettoBulk creaConBulk (UserContext userContext,OggettoBulk bulk) throws ComponentException;
/** 
  *  PDG non modificabile
  *    PreCondition:
  *      checkLivelloResponsabilita(pdg,pdg_det) genera una eccezione
  *    PostCondition:
  *      Lascia uscire l'eccezione generata
  *  Dettaglio scaricato o caricato
  *    PreCondition:
  *      CATEGORIA = 'SCR' o 'CAR'
  *    PostCondition:
  *      Genera una eccezione con messaggio: "Il dettaglio non pu� essere eliminato"
  *  PDG gi� chiuso
  *    PreCondition:
  *      checkChiusuraPdg(pdg_det) genera una eccezione
  *    PostCondition:
  *      Lascia uscire l'eccezione generata
  *  Tutti i controlli superati
  *    PreCondition:
  *      Nessun'altra precondizione verificata
  *    PostCondition:
  *      Elimina il dettaglio del pdg specificato
 */
public abstract void eliminaConBulk (UserContext userContext,OggettoBulk bulk) throws ComponentException;
/** 
  *  normale
  *    PreCondition:
  *      Impostare il CdR di appartenenza
  *    PostCondition:
  *      Vengono pre impostati l'esercizio e il CdR da userContext, viene preimpostato ORIGINE a 'DIR'
 */
public abstract OggettoBulk inizializzaBulkPerInserimento (UserContext userContext,OggettoBulk bulk) throws ComponentException;
/** 
  *  Tutti i controlli superati
  *    PreCondition:
  *      Nessun'altra precondizione � verificata
  *    PostCondition:
  *      Il dettaglio viene salvato
  *  Dettaglio scaricato, gi� chiuso
  *    PreCondition:
  *      PDG modificabile dall'utente, CATEGORIA = 'SCR', stato != 'X'
  *    PostCondition:
  *      Genera una eccezione con messaggio: "Il dettaglio � gi� stato contrattato e non � pi� modificabile. Contattare il CDR servito"
  *  PDG non modificabile
  *    PreCondition:
  *      checkLivelloResponsabilita(pdg,pdg_det) genera una eccezione
  *    PostCondition:
  *      Lascia uscire l'eccezione generata
  *  Dettaglio scaricato
  *    PreCondition:
  *      PDG modificabile dall'utente, CATEGORIA = 'SCR', stato = 'X'
  *    PostCondition:
  *      Salva il dettaglio e modificato lo stato del dettaglio collegato
  *  Dettaglio caricato entrata
  *    PreCondition:
  *      PDG modificabile dall'utente, CATEGORIA = 'CAR'
  *    PostCondition:
  *      Genera una ApplicationException con il messaggio "Il dettaglio non � modificabile. Contattare il CDR servente"
  *  PDG gi� chiuso
  *    PreCondition:
  *      checkChiusuraPdg(pdg_det) genera una eccezione
  *    PostCondition:
  *      Lascia uscire l'eccezione generata
  *  Dettaglio caricato spesa
  *    PreCondition:
  *      PDG modificabile dall'utente, CATEGORIA = 'CAR', e viene richiesto di modificare una colonna diversa da colonna pagamenti (V)
  *    PostCondition:
  *      Genera una ApplicationException con il messaggio: "Per i dettagli di carico del serevnte solo la colonna pagamenti (V) puo' essere modificata"
 */
public abstract OggettoBulk modificaConBulk (UserContext userContext,OggettoBulk bulk) throws ComponentException;
}
