/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/9/8 19:50:57                            */
/*==============================================================*/


drop table if exists t_anomalous;



drop table if exists t_statistics2;

/*==============================================================*/
/* Table: t_anomalous                                           */
/*==============================================================*/
create table t_anomalous
(
   key_a                int not null auto_increment,
   time                 datetime,
   type                 varchar(5),
   source               varchar(20),
   target               varchar(20),
   port_s               varchar(8),
   port_t               varchar(8),
   details              varbinary(1000),
   is_abnormal          bool,
   primary key (key_a)
);

/*==============================================================*/
/* Table: t_statistics                                          */
/*==============================================================*/
create table t_statistics
(
   key_s                int not null auto_increment,
   time                 datetime,
   type                 varchar(10),
   count                numeric(15,0),
   primary key (key_s)
);

/*==============================================================*/
/* Table: t_statistics2                                         */
/*==============================================================*/
drop table if exists t_statistics;
create table t_statistics
(
   key_s                int not null auto_increment,
   time                 datetime,
   type                 varchar(10),
   total                numeric(15,0),
   abnomal_total        numeric(10,0),
   attack_total         numeric(10,0),
   primary key (key_s)
);

