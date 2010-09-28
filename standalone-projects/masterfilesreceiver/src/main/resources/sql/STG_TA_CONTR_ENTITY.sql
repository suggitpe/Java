create table STG_TA_CONTR_ENTITY
(
  TRADING_ACC_NK         VARCHAR2(255),
  TRADING_ACC_DOMAIN     VARCHAR2(255),
  TRADING_ACC_VERSION    NUMBER(20),
  TRADING_ACC_ID         NUMBER(10),
  CONTR_ENTITY_ID        NUMBER(10),
  CONTR_ENTITY_DOMAIN    VARCHAR2(255),
  CONTR_ENTITY_NAME      VARCHAR2(255),
  CONTR_ENTITY_UPDT_DATE DATE,
  CONTR_ENTITY_STATUS    VARCHAR2(255),
  STG_EFFECTIVE_DATE     DATE,
  STG_LOAD_DATE          DATE,
  STG_LOAD_ID            NUMBER(20),
  STG_JOB_RUN_ID         NUMBER(20)
);
