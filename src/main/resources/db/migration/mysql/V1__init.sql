#drop table if exists empresa;

#drop table if exists funcionario;

#drop table if exists lancamento;

create table empresa (
    id bigint not null auto_increment,
    cnpj varchar(255) not null,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    razao_social varchar(255) not null,
    primary key (id)
);

create table funcionario (
    id bigint not null auto_increment,
    cpf varchar(255) not null,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    email varchar(255) not null,
    nome varchar(255) not null,
    perfil varchar(255) not null,
    quantidade_horas_almoco float not null,
    quantidade_horas_trabalho_dia float not null,
    senha varchar(255) not null,
    valor_hora decimal(19,2) not null,
    empresa_id bigint,
    primary key (id)
);

create table lancamento (
    id bigint not null auto_increment,
    data datetime,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    descricao varchar(255) not null,
    localizacao varchar(255) not null,
    tipo varchar(255),
    funcionario_id bigint,
    primary key (id)
);

alter table funcionario 
    add constraint funcionario_empresa_constraint 
    foreign key (empresa_id) 
    references empresa (id);

alter table lancamento 
    add constraint lancamento_funcionario_constraint 
    foreign key (funcionario_id)
	references funcionario (id);