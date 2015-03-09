CREATE TABLE BOARD (
  SEQ        		  NUMBER                 NOT NULL, 
  TITLE      		  VARCHAR2(200 BYTE)         NULL, 
  CONTENT    		  CLOB                       NULL, 
  REG_DATE   		  DATE                       NULL, 
  HIT_COUNT  		  NUMBER                     NULL, 
  ATTACH_FILE_ID      CHAR(20 BYTE)        		 NULL
);

ALTER TABLE BOARD ADD
(
    CONSTRAINT PK_BOARD
    PRIMARY KEY ( SEQ )
);

CREATE TABLE QBIC.USERS (
  ID        VARCHAR2(10 BYTE)     NOT NULL, 
  PASSWD    VARCHAR2(10 BYTE)         NULL
);

ALTER TABLE QBIC.USERS ADD
(
    CONSTRAINT PK_USERS
    PRIMARY KEY ( ID )
);

CREATE TABLE IDS (
  TABLE_NAME    VARCHAR2(20)     NOT NULL,
  NEXT_ID       NUMBER(30)      DEFAULT 0       NOT NULL
)
;

CREATE TABLE QBIC.ATTACH_FILE (
  ATTACH_FILE_ID    CHAR(20 BYTE)          NOT NULL, 
  FILE_PATH         VARCHAR2(256 BYTE)         NULL, 
  UPLOAD_FILE_NM    VARCHAR2(256 BYTE)         NULL, 
  ORIGIN_FILE_NM    VARCHAR2(256 BYTE)         NULL
);

ALTER TABLE QBIC.ATTACH_FILE ADD
(
    CONSTRAINT PK_ATTACH_FILE
    PRIMARY KEY ( ATTACH_FILE_ID )
);


ALTER TABLE IDS ADD
(
    CONSTRAINT IDS_PK
    PRIMARY KEY ( TABLE_NAME )
);

insert into IDS(TABLE_NAME,NEXT_ID) values ('FILE_ID',1);