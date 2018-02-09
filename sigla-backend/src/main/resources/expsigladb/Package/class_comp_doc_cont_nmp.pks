CREATE OR REPLACE PACKAGE CLASS_COMP_DOC_CONT_NMP AS
  --
  -- CLASS_COMP_DOC_CONT_NMP- Package di gestione inserimenti nella tabella di Log :L_ASS_COMP_DOC_CONT_NMP
  -- Date: 08/04/2004
  -- Version: 1.0
  --
  -- Dependency:
  --
  -- History:
  --
  -- Date: 08/04/2004
  -- Version: 1.0
  -- Creazione
  --
  -- Constants:
  --
  -- Functions e Procedures:
  --
  -- Pre-post-name: Inserimento di una riga nella tabella di LOG : 'L_ASS_COMP_DOC_CONT_NMP'
  --
  -- pre: esistenza della tabella di LOG : 'L_ASS_COMP_DOC_CONT_NMP'
  -- post: Viene inserita una riga nella tabella di LOG,
  --
  -- parametri:
  --   aL_ASS_COMP_DOC_CONT_NMP-> Riga da inserire
  procedure INSERTRIGA(aTbName varchar2, aL_ASS_COMP_DOC_CONT_NMP L_ASS_COMP_DOC_CONT_NMP%rowtype);
  --
  -- Pre-post-name: aggiornamento della tabella di registro dei LOG : LOG_REGISTRY
  --
  -- pre: esistenza della tabella di LOG_REGISTRY
  -- post: aggiornamento della tabella di LOG_REGISTRY per lo stato della
  --       tabella L_ASS_COMP_DOC_CONT_NMP
  --
  -- parametri:
  --   aTbName -> Nome della tabella sorgente
  --   aTipoTrg -> Tipologia del trigger che ha scatenato la modifica
  --            'I' -> Trigger di Inserimento
  --            'D' -> Trigger di Cancellazione
  --            'U' -> Trigger di Aggiornamento
  --   aUser -> Utente che ha scatenato la modifica
  procedure updateRegistry(aTbName varchar2, aTipoTrg char, aUser varchar2 );
  --
  -- Pre-post-name: attivazione del trigger sulla tabella :ASS_COMP_DOC_CONT_NMP
  --
  -- pre: Esistenza del trigger in esame, sulla tabella ASS_COMP_DOC_CONT_NMP, e stato del trigger in esame = Disable
  -- post: attivazione del trigger in esame sulla tabella ASS_COMP_DOC_CONT_NMP
  --
  -- parametri:
  --   aNomeTrg -> Nome del trigger che vogliamo attivare
  procedure attivaTrg(aNomeTab varchar2, aNomeTrg varchar2, aUser varchar2 ) ;
  --
  -- Pre-post-name: disattivazione del trigger sulla tabella :ASS_COMP_DOC_CONT_NMP
  --
  -- pre: Esistenza del trigger in esame, sulla tabella ASS_COMP_DOC_CONT_NMP, e stato del trigger in esame = Enable
  -- post: disattivazione del trigger in esame sulla tabella ASS_COMP_DOC_CONT_NMP
  --
  -- parametri:
  --   aNomeTrg -> Nome del trigger che vogliamo disattivare
  procedure disattivaTrg(aNomeTab varchar2, aNomeTrg varchar2, aUser varchar2 );
end;
/


CREATE OR REPLACE PACKAGE BODY CLASS_COMP_DOC_CONT_NMP AS
  procedure UpdRegStatoTrg(aNomeTab varchar2, aNomeTrg varchar2, aStato char, aUser varchar2 ) as
  lStrSql varchar2(100);
  lTipoTrg char(1);
  lOwner varchar2(30);
  begin
    select user into lOwner from dual;
    lTipoTrg := substr(aNomeTrg,2,1) ;
    if lTipoTrg = 'D' then
      if aStato ='D' then
        update LOG_REGISTRY
        set STATO_TRG_AD = aStato,
        DT_DISATTIVAZIONE_AD = sysdate,
        DUVA = sysdate,
        UTUV = aUser,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab ;
      else
        update LOG_REGISTRY
        set STATO_TRG_AD = aStato,
        DT_ATTIVAZIONE_AD = sysdate,
        DUVA = sysdate,
        UTUV = aUser,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab;
      end if;
    end if;

    if lTipoTrg = 'I' then
      if aStato ='D' then
        update LOG_REGISTRY
        set STATO_TRG_AI = aStato,
        DT_DISATTIVAZIONE_AI = sysdate,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab;
      else
        update LOG_REGISTRY
        set STATO_TRG_AI = aStato,
        DT_ATTIVAZIONE_AI = sysdate,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab;
      end if;
    end if;

    if lTipoTrg = 'U' then
      if aStato ='D' then
        update LOG_REGISTRY
        set STATO_TRG_AU = aStato,
        DT_DISATTIVAZIONE_AU = sysdate,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab;
      else
        update LOG_REGISTRY
        set STATO_TRG_AU = aStato,
        DT_ATTIVAZIONE_AU = sysdate,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
        where  nome_table_src = aNomeTab;
      end if;
    end if;
  end;

  procedure attivaTrg(aNomeTab varchar2, aNomeTrg varchar2, aUser varchar2 ) as
  lStrSql varchar2(100);
  begin
    lStrSql := 'ALTER TRIGGER ' || aNomeTrg || ' ENABLE';
    execute immediate lStrSql;
    CLASS_COMP_DOC_CONT_NMP.UpdRegStatoTrg(aNomeTab, aNomeTrg, 'A', aUser );
  end;

  procedure disattivaTrg(aNomeTab varchar2, aNomeTrg varchar2, aUser varchar2 ) as
  lStrSql varchar2(100);
  begin
    lStrSql := 'ALTER TRIGGER ' || aNomeTrg || ' DISABLE';
    execute immediate lStrSql;
    CLASS_COMP_DOC_CONT_NMP.UpdRegStatoTrg(aNomeTab, aNomeTrg, 'D', aUser );
  end;

  procedure INSERTRIGA(aTbName varchar2, aL_ASS_COMP_DOC_CONT_NMP L_ASS_COMP_DOC_CONT_NMP%rowtype) is
  lUser varchar2(20);
  lLogRegistry LOG_REGISTRY%rowtype;
  begin
    select user into lUser from dual;
    select * into lLogRegistry from LOG_REGISTRY
    where nome_table_src = aTbName;
    if lLogRegistry.NUM_MAX_RIGHE_TABLE_LOG >= aL_ASS_COMP_DOC_CONT_NMP.PG_STORICO_ then
      insert into L_ASS_COMP_DOC_CONT_NMP(
        PG_STORICO_,
        CD_CDS_COMPENSO,
        ESERCIZIO_COMPENSO,
        CD_UO_COMPENSO,
        PG_COMPENSO,
        CD_CDS_DOC,
        ESERCIZIO_DOC,
        PG_DOC,
        CD_TIPO_DOC,
        UTCR,
        UTUV,
        DACR,
        DUVA,
        PG_VER_REC,
        USER_ ,
        DT_TRANSACTION_,
        ACTION_
      ) VALUES (
        aL_ASS_COMP_DOC_CONT_NMP.PG_STORICO_,
        aL_ASS_COMP_DOC_CONT_NMP.CD_CDS_COMPENSO,
        aL_ASS_COMP_DOC_CONT_NMP.ESERCIZIO_COMPENSO,
        aL_ASS_COMP_DOC_CONT_NMP.CD_UO_COMPENSO,
        aL_ASS_COMP_DOC_CONT_NMP.PG_COMPENSO,
        aL_ASS_COMP_DOC_CONT_NMP.CD_CDS_DOC,
        aL_ASS_COMP_DOC_CONT_NMP.ESERCIZIO_DOC,
        aL_ASS_COMP_DOC_CONT_NMP.PG_DOC,
        aL_ASS_COMP_DOC_CONT_NMP.CD_TIPO_DOC,
        aL_ASS_COMP_DOC_CONT_NMP.UTCR,
        aL_ASS_COMP_DOC_CONT_NMP.UTUV,
        aL_ASS_COMP_DOC_CONT_NMP.DACR,
        aL_ASS_COMP_DOC_CONT_NMP.DUVA,
        aL_ASS_COMP_DOC_CONT_NMP.PG_VER_REC,
        aL_ASS_COMP_DOC_CONT_NMP.USER_ ,
        aL_ASS_COMP_DOC_CONT_NMP.DT_TRANSACTION_,
        aL_ASS_COMP_DOC_CONT_NMP.ACTION_
      );
    else
      null;
    end if;
  end;

  procedure updateRegistry(aTbName varchar2, aTipoTrg char, aUser varchar2 ) as
  begin
    if aTipoTrg = 'D' then
      update LOG_REGISTRY
      set STATO_TRG_AD = 'A',
        DT_ULTIMO_START_AD = sysdate,
        NUM_RIGHE_IN_TABLE_LOG = nvl(NUM_RIGHE_IN_TABLE_LOG,0) + 1,
        DUVA = sysdate,
        UTUV = aUser,
        PG_VER_REC = PG_VER_REC + 1
      where  nome_table_src = aTbName ;
    end if;

    if aTipoTrg = 'I' then
      update LOG_REGISTRY
      set STATO_TRG_AI = 'A',
        DT_ULTIMO_START_AI = sysdate,
        NUM_RIGHE_IN_TABLE_LOG = nvl(NUM_RIGHE_IN_TABLE_LOG,0) + 1,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
      where  nome_table_src = aTbName;
    end if;

    if aTipoTrg = 'U' then
      update LOG_REGISTRY
      set STATO_TRG_AU = 'A',
        DT_ULTIMO_START_AU = sysdate,
        NUM_RIGHE_IN_TABLE_LOG = nvl(NUM_RIGHE_IN_TABLE_LOG,0) + 1,
        DUVA = sysdate,
        UTUV = aUser ,
        PG_VER_REC = PG_VER_REC + 1
      where  nome_table_src = aTbName;
    end if;
  end;
end;
/


