/*
 * 
 * 
 * 此文件夹的数据已废弃，转移到了yshang-framwork-doc 项目下
 * 
 * 
 * 
 * 
 * */




drop table if exists sys_permission;

drop table if exists sys_resource;

drop table if exists sys_role;

drop table if exists sys_role_auth;


drop table if exists sys_user;

drop table if exists sys_user_role;
drop table if exists hibernate_sequences;

/*==============================================================*/
/* Table: hibernate_sequences                                   */
/*==============================================================*/
create table hibernate_sequences
(
   sequence_name        varchar(255) not null,
   sequence_next_hi_value int comment '下一个sequence的值=sequence_next_hi_value*实体配置的max_lo',
   primary key (sequence_name)
);


/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   id                   int not null,
   name                 varchar(100),
   permission           varchar(200),
   memo                 varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: sys_resource                                          */
/*==============================================================*/
create table sys_resource
(
   id                   int not null,
   parent_id            int,
   name                 varchar(100),
   code                 varchar(100),
   url                  varchar(200),
   ico                  varchar(100),
   is_root              int,
   is_show              int,
   order_id             int,
   level                int,
   parent_ids           varchar(200),
   permission_ids       varchar(200),
   memo                 varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int not null,
   name                 varchar(100),
   code                 varchar(100),
   memo                 varchar(200),
   create_time          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: sys_role_auth                                         */
/*==============================================================*/
create table sys_role_auth
(
   id                   int not null,
   role_id              int,
   resource_id          int,
   permission_ids       varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int not null,
   creater_id           int,
   login_name           varchar(100) not null,
   password             varchar(40) not null,
   salt                 varchar(20),
   name                 varchar(50),
   email                varchar(200),
   create_time          datetime,
   status               smallint comment '1 正常 2 停用',
   last_login_time      datetime,
   last_login_ip        varchar(15),
   primary key (id)
);

/*==============================================================*/
/* Index: idx_sys_user_logname                                  */
/*==============================================================*/
create index idx_sys_user_logname on sys_user
(
   login_name
);

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   role_id              int not null,
   user_id              int not null,
   primary key (role_id, user_id)
);

create table sys_dictionary_type
(
   id                   bigint not null,
   name                 varchar(255),
   code                 varchar(255),
   memo                 varchar(255),
   primary key (id)
);

create table sys_dictionary
(
   id                   bigint not null,
   dictionary_type_id   bigint,
   name                 varchar(255),
   code                 varchar(255),
   value                varchar(255),
   order_id             int,
   extend_1             varchar(255),
   extend_2             varchar(255),
   extend_3             varchar(255),
   memo                 varchar(255),
   primary key (id)
);

alter table sys_dictionary add constraint FK_sys_dictionary_dictionary_type_id foreign key (dictionary_type_id)
      references sys_dictionary_type (id) on delete restrict on update restrict;

alter table sys_resource add constraint FK_sys_resource_parent_id foreign key (parent_id)
      references sys_resource (id) on delete restrict on update restrict;

alter table sys_role_auth add constraint FK_sys_role_auth_resource_id foreign key (resource_id)
      references sys_resource (id) on delete restrict on update restrict;

alter table sys_role_auth add constraint FK_sys_role_auth_role_id foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

alter table sys_user add constraint FK_sys_user_creater_id foreign key (creater_id)
      references sys_user (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_sys_user_role_role_id foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_sys_user_role_user_id foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;
