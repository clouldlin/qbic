/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.BOARD
-- 만든 날짜: 2015-03-09 오전 11:21:02
-- 마지막으로 수정한 날짜: 2015-03-09 오전 11:21:11
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.BOARD CASCADE CONSTRAINTS;

CREATE TABLE QBIC.BOARD (
  BOARD_ID     CHAR(20 BYTE)          NOT NULL, 
  TITLE        VARCHAR2(200 BYTE)         NULL, 
  CONTENT      CLOB                       NULL, 
  REG_DATE     DATE                       NULL, 
  HIT_COUNT    NUMBER                     NULL, 
  FILE_ID      CHAR(20 BYTE)              NULL
)
LOB (CONTENT) STORE AS (
    TABLESPACE TS_QBIC_DB
    ENABLE STORAGE IN ROW
    CHUNK 8192
    NOCACHE
    LOGGING
    STORAGE (
        INITIAL     64K
        NEXT        1M
        MINEXTENTS  1
        MAXEXTENTS  UNLIMITED
    )
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     64K
    NEXT        1M
    MINEXTENTS  1
    MAXEXTENTS  UNLIMITED
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.BOARD ADD
(
    CONSTRAINT PK_BOARD
    PRIMARY KEY ( BOARD_ID )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 64K
            NEXT 1M
            MINEXTENTS 1
            MAXEXTENTS UNLIMITED
        )
);

/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.COMMON_CODE
-- 만든 날짜: 2015-03-09 오후 9:00:02
-- 마지막으로 수정한 날짜: 2015-03-09 오후 9:00:02
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.COMMON_CODE CASCADE CONSTRAINTS;

CREATE TABLE QBIC.COMMON_CODE (
  CODE_GROUP    VARCHAR2(20 BYTE)      NOT NULL, 
  CODE_NAME     VARCHAR2(20 BYTE)      NOT NULL, 
  CODE_DATA     VARCHAR2(200 BYTE)         NULL, 
  SORT_ORD      NUMBER(3)                  NULL, 
  USE_YN        CHAR(1 BYTE)          DEFAULT 'Y'               NULL
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     0M
    MINEXTENTS  0
    MAXEXTENTS  0
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.COMMON_CODE ADD
(
    CONSTRAINT PK_COMMON_CODE
    PRIMARY KEY ( CODE_GROUP, CODE_NAME )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 0M
            MINEXTENTS 0
            MAXEXTENTS 0
        )
);

/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.FILE_DETAIL
-- 만든 날짜: 2015-03-09 오후 4:30:08
-- 마지막으로 수정한 날짜: 2015-03-09 오후 5:35:06
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.FILE_DETAIL CASCADE CONSTRAINTS;

CREATE TABLE QBIC.FILE_DETAIL (
  FILE_ID           CHAR(20 BYTE)          NOT NULL, 
  FILE_SN           VARCHAR2(2 BYTE)       NOT NULL, 
  FILE_PATH         VARCHAR2(256 BYTE)         NULL, 
  FILE_UPLOAD_NM    VARCHAR2(256 BYTE)         NULL, 
  FILE_ORIGIN_NM    VARCHAR2(256 BYTE)         NULL, 
  FILE_EXT          VARCHAR2(4 BYTE)           NULL, 
  FILE_SIZE         NUMBER(10)                 NULL
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     64K
    NEXT        1M
    MINEXTENTS  1
    MAXEXTENTS  UNLIMITED
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.FILE_DETAIL ADD
(
    CONSTRAINT PK_FILE_DETAIL
    PRIMARY KEY ( FILE_ID, FILE_SN )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 64K
            NEXT 1M
            MINEXTENTS 1
            MAXEXTENTS UNLIMITED
        )
);

/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.FILE_MASTER
-- 만든 날짜: 2015-03-09 오후 4:26:32
-- 마지막으로 수정한 날짜: 2015-03-09 오후 5:20:46
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.FILE_MASTER CASCADE CONSTRAINTS;

CREATE TABLE QBIC.FILE_MASTER (
  FILE_ID     CHAR(20 BYTE)     NOT NULL, 
  REG_DATE    DATE                  NULL, 
  USE_AT      CHAR(1 BYTE)          NULL
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     64K
    NEXT        1M
    MINEXTENTS  1
    MAXEXTENTS  UNLIMITED
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.FILE_MASTER ADD
(
    CONSTRAINT PK_FILE_MASTER
    PRIMARY KEY ( FILE_ID )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 64K
            NEXT 1M
            MINEXTENTS 1
            MAXEXTENTS UNLIMITED
        )
);

/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.IDS
-- 만든 날짜: 2015-02-10 오후 5:24:32
-- 마지막으로 수정한 날짜: 2015-02-10 오후 5:24:33
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.IDS CASCADE CONSTRAINTS;

CREATE TABLE QBIC.IDS (
  TABLE_NAME    VARCHAR2(20 BYTE)     NOT NULL, 
  NEXT_ID       NUMBER(30)           DEFAULT 0            NOT NULL
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     64K
    NEXT        1M
    MINEXTENTS  1
    MAXEXTENTS  UNLIMITED
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.IDS ADD
(
    CONSTRAINT IDS_PK
    PRIMARY KEY ( TABLE_NAME )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 64K
            NEXT 1M
            MINEXTENTS 1
            MAXEXTENTS UNLIMITED
        )
);

/*------------------------------------------------------------------------------
-- 개체 이름: QBIC.USERS
-- 만든 날짜: 2015-02-26 오전 9:56:11
-- 마지막으로 수정한 날짜: 2015-02-26 오전 9:56:12
-- 상태: VALID
------------------------------------------------------------------------------*/
DROP TABLE QBIC.USERS CASCADE CONSTRAINTS;

CREATE TABLE QBIC.USERS (
  ID        VARCHAR2(10 BYTE)     NOT NULL, 
  PASSWD    VARCHAR2(10 BYTE)         NULL
)
TABLESPACE TS_QBIC_DB
PCTFREE    10
PCTUSED    0
INITRANS   1
MAXTRANS   255
STORAGE (
    INITIAL     0M
    MINEXTENTS  0
    MAXEXTENTS  0
)
LOGGING
NOCACHE
MONITORING
NOPARALLEL
;

ALTER TABLE QBIC.USERS ADD
(
    CONSTRAINT PK_USERS
    PRIMARY KEY ( ID )
        USING INDEX
        TABLESPACE TS_QBIC_DB 
        PCTFREE 10
        INITRANS 2
        MAXTRANS 255
        STORAGE (
            INITIAL 0M
            MINEXTENTS 0
            MAXEXTENTS 0
        )
);

