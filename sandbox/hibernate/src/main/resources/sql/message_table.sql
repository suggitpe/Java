DROP table "MESSAGES" cascade constraints ;


CREATE table "MESSAGES"
(
    "MESSAGE_ID"      NUMBER(10,0) NOT NULL,
    "MESSAGE_TEXT"    VARCHAR2(255) NOT NULL,
    "NEXT_MESSAGE_ID" NUMBER(10,0),
    constraint  
    	"MESSAGES_PK" primary key ("MESSAGE_ID")
);


