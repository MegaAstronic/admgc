DROP DATABASE IF EXISTS admgc;
CREATE DATABASE admgc CHARACTER SET UTF8;
USE admgc;


-- 1.用户表
CREATE TABLE auth_member(
mid INT AUTO_INCREMENT ,
username VARCHAR(50),
password VARCHAR(32),
name VARCHAR(20) ,
locked INT DEFAULT 0,
CONSTRAINT pk_mid PRIMARY KEY (mid),
CONSTRAINT uk_username UNIQUE (username)
)ENGINE = innodb;


-- 2.角色表
CREATE TABLE auth_role(
rid INT AUTO_INCREMENT ,
title VARCHAR(50) ,
flag VARCHAR(50) ,
CONSTRAINT pk_rid PRIMARY KEY (rid)
) ENGINE = innodb;

-- 3. 用户-角色关系表
CREATE TABLE auth_member_role(
mid INT,
rid INT,
CONSTRAINT fk_mid FOREIGN KEY (mid) REFERENCES auth_member(mid) ,
CONSTRAINT fk_rid FOREIGN KEY (rid) REFERENCES auth_role(rid)
) ENGINE = innodb ; 

-- 4.权限表
CREATE TABLE auth_permit(
pid INT AUTO_INCREMENT ,
title VARCHAR(50),
flag VARCHAR(50),
CONSTRAINT pk_pid PRIMARY KEY(pid)
)ENGINE= innodb;

-- 5.角色-权限关系表
CREATE TABLE auth_role_permit(
rid INT ,
pid INT,
CONSTRAINT fk_rid_tb_role_permit FOREIGN KEY (rid) REFERENCES auth_role(rid) ,
CONSTRAINT fk_pid_tb_role_permit FOREIGN KEY (pid) REFERENCES auth_permit(pid) 
) ENGINE=innodb;

INSERT INTO auth_member(mid,username,password,name) VALUES(1,'root','admin','astronic');
INSERT INTO auth_role(rid,flag) VALUES(1,'article');
INSERT INTO auth_permit(pid,flag) VALUES(1,'article:add');

INSERT INTO auth_member_role(mid,rid) VALUES(1,1);
INSERT INTO auth_role_permit(rid,pid) VALUES(1,1);