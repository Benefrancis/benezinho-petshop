--------------------------------------------------------
--  Arquivo criado - sexta-feira-outubro-06-2023   
--------------------------------------------------------
REM INSERTING into TB_ANIMAL
SET DEFINE OFF;
Insert into TB_ANIMAL (DT_NASCIMENTO,DONO,ID_ANIMAL,DS_ANIMAL,NM_ANIMAL,TP_ANIMAL,OBSERVACAO,RACA,SEXO) values (to_date('05/10/23','DD/MM/RR'),'1','1',null,'Pith Bitoca','CACHORRO',null,'Bull Terrier','MASCULINO');
Insert into TB_ANIMAL (DT_NASCIMENTO,DONO,ID_ANIMAL,DS_ANIMAL,NM_ANIMAL,TP_ANIMAL,OBSERVACAO,RACA,SEXO) values (to_date('05/10/23','DD/MM/RR'),'1','2',null,'Ciam�s','GATO',null,'Rasga Saco','MASCULINO');
REM INSERTING into TB_AUTHORITY
SET DEFINE OFF;
Insert into TB_AUTHORITY (ID_AUTHORITY,NM_AUTHORITY) values ('1','cliente');
REM INSERTING into TB_AUTHORITY_USUARIO
SET DEFINE OFF;
Insert into TB_AUTHORITY_USUARIO (AUTHORITY,USUARIO) values ('1','1');
REM INSERTING into TB_BANHO
SET DEFINE OFF;
Insert into TB_BANHO (ID_SERVICO) values ('1');
REM INSERTING into TB_CACHORRO
SET DEFINE OFF;
Insert into TB_CACHORRO (ID_ANIMAL) values ('1');
REM INSERTING into TB_CONSULTA
SET DEFINE OFF;
Insert into TB_CONSULTA (ID_SERVICO) values ('3');
REM INSERTING into TB_DOCUMENTO
SET DEFINE OFF;
REM INSERTING into TB_GATO
SET DEFINE OFF;
Insert into TB_GATO (ID_ANIMAL) values ('2');
REM INSERTING into TB_PESSOA
SET DEFINE OFF;
Insert into TB_PESSOA (DT_NASCIMENTO,ID_PESSOA,TP_PESSOA,EMAIL,NM_PESSOA) values (to_date('08/03/77','DD/MM/RR'),'1','PF','benefrancis@gmail.com','Benefrancis do Nascimento');
REM INSERTING into TB_PF
SET DEFINE OFF;
Insert into TB_PF (ID_PESSOA,NR_CPF,SEXO) values ('1','24878891864','MASCULINO');
REM INSERTING into TB_PJ
SET DEFINE OFF;
REM INSERTING into TB_SERVICO
SET DEFINE OFF;
Insert into TB_SERVICO (VALOR,ANIMAL,DT_ABERTURA,DT_AUTORIZACAO,DT_CONCLUSAO,ID_SERVICO,TP_SERVICO,DS_SERVICO,OBS_SERVICO) values ('150','1',to_timestamp('06/10/23 00:53:15,758662000','DD/MM/RR HH24:MI:SSXFF'),null,null,'1','BANHO','Banho de Perfume de laranja',null);
Insert into TB_SERVICO (VALOR,ANIMAL,DT_ABERTURA,DT_AUTORIZACAO,DT_CONCLUSAO,ID_SERVICO,TP_SERVICO,DS_SERVICO,OBS_SERVICO) values ('49','1',to_timestamp('06/10/23 00:54:10,402363000','DD/MM/RR HH24:MI:SSXFF'),null,null,'2','TOSA','Tosa na R�gua e no compasso',null);
Insert into TB_SERVICO (VALOR,ANIMAL,DT_ABERTURA,DT_AUTORIZACAO,DT_CONCLUSAO,ID_SERVICO,TP_SERVICO,DS_SERVICO,OBS_SERVICO) values ('300','1',to_timestamp('06/10/23 00:52:09,478286000','DD/MM/RR HH24:MI:SSXFF'),null,null,'3','CONSULTA','Consulta com o Dr. Dolittle',null);
REM INSERTING into TB_TELEFONE
SET DEFINE OFF;
REM INSERTING into TB_TOSA
SET DEFINE OFF;
Insert into TB_TOSA (ID_SERVICO) values ('2');
REM INSERTING into TB_USER
SET DEFINE OFF;
Insert into TB_USER (ID_USUARIO,PESSOA,USER_EMAIL,USER_PASSWORD) values ('1','1','benefrancis@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92');
