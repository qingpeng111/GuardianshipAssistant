/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/9/1 13:30:51                            */
/*==============================================================*/
show databases;

CREATE DATABASE anomalous;

use anomalous;

show tables;





/*==============================================================*/
/* Table: t_anomalous                                           */
/*==============================================================*/
drop table if exists t_anomalous;
create table t_anomalous
(
   key_a                int not null auto_increment,
   time                 datetime,
   type                 varchar(8),
   source               varchar(20),
   target               varchar(20),
   port_s               varchar(8),
   port_t               varchar(8),
   details              varchar(500),
   is_abnormal          bool,
   primary key (key_a)
);


-- 插入试验数据格式如下：
--INSERT INTO reader(name,sex) VALUES('kusada','man');（格式）
-- INSERT INTO t_anomalous VALUES(1,"language",'skama')
-- select * from t_anomalous;
--UPDATE table_name SET field1=new-value1, field2=new-value2
--[WHERE Clause]
INSERT INTO t_anomalous VALUES(1,'2011-03-05 01:53:55.63','S7','192.168.1.1','215.271.189.261','8080','8356','dfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(2,'2019-03-05 01:53:55.63','S7','192.168.1.1','213.271.189.261','8080','8356','wgtegdfvfgnbfgjmcvjkghikmghikgiy',false);
INSERT INTO t_anomalous VALUES(3,'2019-03-05 01:53:55.63','S7','182.168.1.1','214.271.189.261','8080','8356','wgtegdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(4,'2013-03-05 01:53:55.63','S7','192.168.1.1','215.271.189.261','8080','8356','tegertdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(5,'2019-03-05 01:53:55.63','S7','192.168.1.1','214.271.189.261','8180','8326','wgtetdfvfgnbfgjmcvjkghikmghikgiy',false);
INSERT INTO t_anomalous VALUES(6,'2014-03-05 01:53:55.63','S7','192.16.1.1','215.171.189.261','8080','8356','wgtegdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(7,'2019-03-05 01:53:55.63','S7','192.128.1.1','215.271.189.261','8080','8356','wgetgdfvfgnbfgjmcvjkghikmghikgiy',false);
INSERT INTO t_anomalous VALUES(8,'2019-03-05 01:53:55.63','S7','192.138.1.1','215.241.189.261','8020','8356','wgteftgdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(9,'2014-03-05 01:53:55.63','S7','192.148.1.1','215.271.189.261','8080','832','wegtetdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(10,'2019-03-05 01:53:55.63','S7','192.158.1.1','215.273.189.261','8082','8356','wetgwetgdfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(11,'2019-03-05 01:53:55.63','S7','192.168.1.1','215.271.9.261','8080','8356','egwetdfvfgnbfgjmcvjkghikmghikgiy',false);
INSERT INTO t_anomalous VALUES(12,'2015-03-05 01:53:55.63','S7','192.168.11.1','215.271.139.261','8080','8352','dfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(13,'2019-03-05 01:53:55.63','S7','192.168.12.1','215.271.189.261','8280','8356','dfvfgnbfgjmcvjkghikmghikgiy',false);
INSERT INTO t_anomalous VALUES(14,'2079-03-05 01:53:55.63','S7','192.168.1.21','215.271.129.261','8080','8256','dfvfgnbfgjmcvjkghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(15,'2017-03-05 01:53:55.63','S7','192.168.1.21','215.271.189.261','8020','8336','dfvfgnbfgjmcvjkghikermghikgiy',false);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
INSERT INTO t_anomalous VALUES(null,'2017-03-05 01:53:55.63','S7','192.168.71.1','215.271.19.21','8280','8322','erewrwdfvfgnbfgjmcvjkerferghikmghikgiy',true);
/*==============================================================*/
/* Table: t_statistics                                          */
/*==============================================================*/
drop table if exists t_statistics;
create table t_statistics
(
   key_s                int not null auto_increment,
   time                 datetime,
   type                 varchar(10),
   count                numeric(15,0),
   count_anomalous                numeric(15,0),
   primary key (key_s)
);
INSERT INTO t_statistics VALUES('2017-03-05 01:53:55.63','S7',11,2);
INSERT INTO t_statistics VALUES(null,'2014-03-05 01:53:55.63','http',23,3);
INSERT INTO t_statistics VALUES(null,'2012-03-05 01:53:55.63','modbus',90,5);
INSERT INTO t_statistics VALUES(null,'2013-03-05 01:53:55.63','S7',11,8);
INSERT INTO t_statistics VALUES(null,'2017-04-05 01:53:55.63','http',23,3);
INSERT INTO t_statistics VALUES(null,'2015-03-05 01:53:55.63','modbus',90,6);
INSERT INTO t_statistics VALUES(null,'2017-06-05 01:53:55.63','S7',11,1);
INSERT INTO t_statistics VALUES(null,'2017-06-05 01:53:55.63','http',23,3);
INSERT INTO t_statistics VALUES(null,'2017-03-05 01:53:55.63','modbus',90,6);
INSERT INTO t_statistics VALUES(null,'2015-03-05 01:53:55.63','S7',11,9);
INSERT INTO t_statistics VALUES(null,'2017-03-05 01:53:55.63','http',23,1);
INSERT INTO t_statistics VALUES(null,'2017-06-05 01:53:55.63','modbus',90,5);
INSERT INTO t_statistics VALUES(null,'2017-03-05 01:53:55.63','S7',11,4);
INSERT INTO t_statistics VALUES(null,'2017-06-05 01:53:55.63','http',23,6);
INSERT INTO t_statistics VALUES(null,'2015-03-05 01:53:55.63','modbus',90,3);


