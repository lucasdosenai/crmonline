DROP DATABASE banco;

Create database banco;
use banco;

Create table USUARIO(
	ID int primary key auto_increment not null,
	NOME varchar(64) not null,
	NIF int not null not null,
	EMAIL varchar(64) not null,
	SENHA varchar(16) not null,
	STATU integer default 1,
	TIPO_USUARIO integer default 0 not null

-- Statu :         1 para Habilitado
--                 0 para Desabilitado
 
-- TIPO_USUARIO :  0 USUARIO COMUM
  --               1 USUARIO ROOT
);
-- CRIANDO USUARIO
INSERT INTO USUARIO(NOME,NIF,EMAIL,SENHA,STATU,TIPO_USUARIO) VALUES ('Nailton',01012490,'nana@gay.usa','123',1,1);
-- LOGIN USUARIO
SELECT USUARIO.NOME FROM USUARIO WHERE USUARIO.NIF = "01012490" AND USUARIO.SENHA = "123";
-- MOSTRA TODOS USUARIOS
SELECT * FROM USUARIO;


create table CATEGORIA(
	ID	int primary key auto_increment not null,
	NOME	varchar(24)
);
-- CRIA CATEGORIAS
INSERT INTO CATEGORIA(NOME) VALUES('Eletrônica');
-- MOSTRA TODAS CATEGORIAS

create table CURSO(
	ID	         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	NOME	     VARCHAR(64) NOT NULL,
	VAGAS_TOTAL	 INT DEFAULT 0,
	DESCRI	     VARCHAR(264) DEFAULT 'SEM DESCRIÇÃO',
	DURACAO	     VARCHAR(64) DEFAULT 'SEM DESCRIÇÃO',
    ID_CATEGORIA INT
);

create table CLIENTE(
	ID	int primary key auto_increment not null,
	NOME Varchar(64),
	TELEFONE Varchar(64),
	EMAIL	VARCHAR(64),
    ID_AREA INT,

foreign key (ID_AREA) references CATEGORIA(ID)

-- CAPTURAR CATEGORIA DO CLIENTE, AREA QUE ATUA
);

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

create table RECUPERA_USUARIO(
	ID	int primary key auto_increment not null,
    EMAIL VARCHAR(64) NOT NULL,
    ID_USER INTEGER,
    CODIGO VARCHAR(64),
    
    FOREIGN KEY (ID_USER) REFERENCES USUARIO(ID)
);

