Create database banco;
use banco;

Create table USUARIO(
	ID int primary key auto_increment not null,
	NOME varchar(64) not null,
	NIF varchar(7) not null unique,
    SEXO VARCHAR(1) not null,
	EMAIL varchar(64) not null,
	SENHA varchar(16) not null,
	STATU integer default 1,
	TIPO_USUARIO integer default 0

-- Statu :         1 para Habilitado
--                 0 para Desabilitado
 
-- TIPO_USUARIO :  0 USUARIO COMUM
  --               1 USUARIO ROOT
);
SELECT * FROM USUARIO;
-- CRIANDO USUARIO
INSERT INTO USUARIO(NOME,NIF,SEXO,EMAIL,SENHA,STATU,TIPO_USUARIO) VALUES ('AMD','1234569','M','nana@gay.usa','123',0,1);
-- LOGIN USUARIO
SELECT USUARIO.NOME FROM USUARIO WHERE USUARIO.NIF = "01012490" AND USUARIO.SENHA = "123";
-- MOSTRA TODOS USUARIOS
SELECT * FROM USUARIO;
SELECT * FROM USUARIO WHERE EMAIL = 'marcos150575@hotmail.com';
SELECT * FROM USUARIO WHERE NIF = '1234567' AND SENHA = '123';
delete from USUARIO WHERE 1 = 1;

SELECT USUARIO.NIF FROM USUARIO WHERE USUARIO.NIF = "01012490";
SELECT USUARIO.NIF FROM USUARIO WHERE USUARIO.NIF = "1234567";
UPDATE USUARIO SET STATU = 1, TIPO_USUARIO = 1 WHERE ID = 15;
create table CATEGORIA(
	ID	int primary key auto_increment not null,
	NOME	varchar(24)
);
-- CRIA CATEGORIAS
INSERT INTO CATEGORIA(NOME) VALUES('Eletrônica');
-- MOSTRA TODAS CATEGORIAS
SELECT * FROM CATEGORIA;
create table CURSO(
	ID	         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	NOME	     VARCHAR(64) NOT NULL,
	VAGAS_TOTAL	 INT DEFAULT 0,
	DESCRI	     VARCHAR(264) DEFAULT 'SEM DESCRIÇÃO',
	DURACAO	     VARCHAR(64) DEFAULT 'SEM DESCRIÇÃO',
    ID_CATEGORIA INT
);

create table CLIENTE(
	ID			  int primary key auto_increment not null,
    NOME 		  VARCHAR(64),
    N_FUNCIONARIO VARCHAR(64),
    CPNJ 		  VARCHAR(16),
	TELEFONE 	  VARCHAR(64),
	EMAIL		  VARCHAR(64),
    LOGRADOURO    VARCHAR(264),
    CIDADE        VARCHAR(64),
    ID_CATEGORIA  INT,

foreign key (ID_CATEGORIA) references CATEGORIA(ID)

-- CAPTURAR CATEGORIA DO CLIENTE, AREA QUE ATUA
);
INSERT INTO CLIENTE(NOME,N_FUNCIONARIO,CPNJ,TELEFONE,EMAIL,LOGRADOURO,CIDADE,ID_CATEGORIA) 
VALUES ('AMBEV','350','150250230250/00015','1382019','SDFSD@FSDFD.COM','R ENY PONCE','JAGUARIÚNA-SP',3);

create table AGENDA(
	ID	         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	ATENDENTE    VARCHAR(64) NOT NULL, -- Nome da pessoas que vai atender na hora da visita, reunião marcada com ?
	QUANDO	     TIMESTAMP NOT NULL, -- Dia da visita
	HORARIO	     TIMESTAMP NOT NULL, -- Hora da visita
	ESTADOS	     INT,
	CLASSFICACOES INT,
	OBSERVACOES	  VARCHAR(264),
    
    ID_VISITANTE  INT,
	ID_CLIENTE	  INT,
    ID_CURSO 	  INT,
    
	FOREIGN KEY (ID_CLIENTE)   REFERENCES CLIENTE(ID),
	FOREIGN KEY (ID_VISITANTE) REFERENCES USUARIO(ID),
	FOREIGN KEY (ID_CURSO)  REFERENCES CURSO(ID)
);

create table VENDA(
	ID	int primary key auto_increment not null,
	VAGAS	INT,
    
	ID_CURSO	INT,
    ID_VISITA	INT,
    
	foreign key (ID_VISITA) references AGENDA(ID),
	foreign key (ID_CURSO) references CURSO(ID)
--   
);
SELECT * FROM RECUPERA_USUARIO;
DELETE FROM RECUPERA_USUARIO WHERE ID_USER = 6;
create table RECUPERA_USUARIO(
	ID	int primary key auto_increment not null,
    ID_USER INTEGER,
    CODIGO VARCHAR(64),
    
    FOREIGN KEY (ID_USER) REFERENCES USUARIO(ID)
);
