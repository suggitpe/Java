create table STG_LE_CROSS_REF
(
  LE_DOMAIN                  VARCHAR2(255),
  LE_VERSION                 NUMBER(20),
  LE_ID                      NUMBER(20),
  LEGAL_NAME                 VARCHAR2(255),
  ALT_IDENTIFIER_ID          VARCHAR2(255),
  ALT_IDENTIFIER_DOMAIN      VARCHAR2(255),
  ALT_DOMAIN_CODE            VARCHAR2(255),
  ALT_IDENTIFIER_STATUS_DATE DATE,
  ALT_IDENTIFIER_STATUS      VARCHAR2(50),
  STG_EFFECTIVE_DATE         DATE,
  STG_LOAD_DATE              DATE,
  STG_LOAD_ID                NUMBER(20),
  STG_JOB_RUN_ID             NUMBER(20)
);
