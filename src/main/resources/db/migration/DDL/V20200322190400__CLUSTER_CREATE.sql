CREATE TABLE cluster
(
id           INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '自增ID',
clusterName  varchar(255) NOT NULL,
IPs          varchar(255) NOT NULL COMMENT 'master node ip'
);