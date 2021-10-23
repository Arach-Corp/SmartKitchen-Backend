alter table t_smk_info_nutri drop constraint FKrwi0gj80r1fyk6slqtn54vxeb

alter table t_smk_lote drop constraint FK9g0xuocibj6775xtyspgb7o5f

alter table t_smk_notificacao drop constraint FKcauy76tcufyemsbbegj5tpg5j

alter table t_smk_produto_dispensa drop constraint FK94a3j6eragns2bsvncw7wtvjc

alter table t_smk_produto_dispensa drop constraint FKj0souky5plpy667tk5dx7tvnv

alter table t_smk_user_dispositivo drop constraint FKcf7vpvygcqsj3bjli0t6xcipg

alter table t_smk_user_dispositivo drop constraint FKjk04dvdlgmvildy78os5vuiem

alter table t_smk_user_roles drop constraint FKa4u7atvp60hw3o156yxky0al1

alter table t_smk_user_roles drop constraint FKg6j6jvbe6dn44otrlqku8of8b

drop table if exists t_smk_dispositivos cascade

drop table if exists t_smk_info_nutri cascade

drop table if exists t_smk_lote cascade

drop table if exists t_smk_notificacao cascade

drop table if exists t_smk_produto cascade

drop table if exists t_smk_produto_dispensa cascade

drop table if exists t_smk_roles cascade

drop table if exists t_smk_user cascade

drop table if exists t_smk_user_dispositivo cascade

drop table if exists t_smk_user_roles cascade

create table t_smk_dispositivos (
		id_dispositivo  bigserial not null,
		ds_dispositivo varchar(50) not null,
		ds_key varchar(64) not null,
		dt_cadastro timestamp,
		primary key (id_dispositivo)
	)
  
create table t_smk_info_nutri (
		id_info_nutri  bigserial not null,
		ds_carboidratos varchar(20),
		ds_fibra_alimentar varchar(20),
		ds_gordura_saturadas varchar(20),
		ds_gordura_totais varchar(20),
		ds_gorduras_trans varchar(20),
		ds_porcao varchar(20),
		ds_proteinas varchar(20),
		ds_sodio varchar(20),
		dt_cadastro timestamp,
		produto_id int8,
		primary key (id_info_nutri)
	)
  
create table t_smk_lote (
		id_lote  bigserial not null,
		dt_fabricacao timestamp not null,
		dt_validade timestamp,
		dt_cadastro timestamp not null,
		produto_id int8,
		primary key (id_lote)
	)
  
create table t_smk_notificacao (
		id_notificacao  bigserial not null,
		ds_mensagem varchar(255) not null,
		dt_notificacao timestamp,
		ds_titulo varchar(80) not null,
		user_id int8,
		primary key (id_notificacao)
	)
  
create table t_smk_produto (
		id_produto  bigserial not null,
		ds_produto varchar(200) not null,
		ds_marca varchar(40) not null,
		nm_produto varchar(40) not null,
		ds_perecidade varchar(255) not null,
		dt_cadastro timestamp,
		url_foto varchar(128),
		primary key (id_produto)
	)
  
create table t_smk_produto_dispensa (
		id_produto_dispensa  bigserial not null,
		bl_ativo boolean,
		dt_retirada timestamp,
		nr_quantidade int4,
		dt_cadastro timestamp,
		dispositivo_id int8,
		lote_id int8,
		primary key (id_produto_dispensa)
	)
  
create table t_smk_roles (
		id_role  bigserial not null,
		ds_role varchar(40) not null,
		primary key (id_role)
	)
  
create table t_smk_user (
		id_user  bigserial not null,
		ds_email varchar(60) not null,
		ds_nome varchar(80) not null,
		ds_password varchar(255) not null,
		dt_cadastro timestamp,
		url_foto varchar(128),
		primary key (id_user)
	)
  
create table t_smk_user_dispositivo (
		bl_principal boolean,
		dt_cadastro timestamp,
		user_id int8 not null,
		dispositivo_id int8 not null,
		primary key (dispositivo_id,
		user_id)
	)
	
create table t_smk_user_roles (
		user_id int8 not null,
		role_id int8 not null
	)
  
alter table t_smk_dispositivos add constraint UK_dw1adwyym5lbp0wgdx1u8xmtr unique (ds_key)

alter table t_smk_roles add constraint UK_ooe188w8bagxr9k1q8syjov8t unique (ds_role)

alter table t_smk_user add constraint UK_i822n0ocwlunmkkqghd7fxbr3 unique (ds_email)

alter table t_smk_info_nutri add constraint FKrwi0gj80r1fyk6slqtn54vxeb foreign key (produto_id) references t_smk_produto

alter table t_smk_lote add constraint FK9g0xuocibj6775xtyspgb7o5f foreign key (produto_id) references t_smk_produto

alter table t_smk_notificacao add constraint FKcauy76tcufyemsbbegj5tpg5j foreign key (user_id) references t_smk_user

alter table t_smk_produto_dispensa add constraint FK94a3j6eragns2bsvncw7wtvjc foreign key (dispositivo_id) references t_smk_dispositivos

alter table t_smk_produto_dispensa add constraint FKj0souky5plpy667tk5dx7tvnv foreign key (lote_id) references t_smk_lote

alter table t_smk_user_dispositivo add constraint FKcf7vpvygcqsj3bjli0t6xcipg foreign key (user_id) references t_smk_user

alter table t_smk_user_dispositivo add constraint FKjk04dvdlgmvildy78os5vuiem foreign key (dispositivo_id) references t_smk_dispositivos

alter table t_smk_user_roles add constraint FKa4u7atvp60hw3o156yxky0al1 foreign key (role_id) references t_smk_roles

alter table t_smk_user_roles add constraint FKg6j6jvbe6dn44otrlqku8of8b foreign key (user_id) references t_smk_user
