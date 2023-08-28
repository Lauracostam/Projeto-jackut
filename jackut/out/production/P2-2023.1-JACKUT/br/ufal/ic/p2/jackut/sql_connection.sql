CREATE DATABASE jackut;
USE jackut;

CREATE TABLE usuarios(
	login varchar(128),
    senha varchar(128),
    nome varchar(128),
    descricao varchar(128),
    estadoCivil varchar(128),
    aniversario varchar(128),
    filhos varchar(128),
    idiomas varchar(128),
    cidadeNatal varchar(128),
    estilo varchar(128),
    fumo varchar(128),
    bebo varchar(128),
    moro varchar(128),
    primary key (login)
    ) ENGINE InnoDB;
CREATE TABLE sessao(
	id varchar(128),
	loginSessao varchar(128)
) ENGINE InnoDB;
CREATE TABLE amigos(
    loginUsuario varchar(128),
    loginAmigo varchar(128),
    pedidoAceito int
) ENGINE InnoDB;
CREATE TABLE recados(
	login varchar(128),
    destinatario varchar(128),
    recado varchar(128)
) ENGINE InnoDB;
    