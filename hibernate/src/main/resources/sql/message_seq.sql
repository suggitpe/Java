drop sequence "MESSAGES_SEQ";

create sequence "MESSAGES_SEQ"
start with 1
increment by 1
maxvalue 4000000000
cache 10
nocycle
order;
