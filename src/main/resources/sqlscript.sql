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


-- example
CREATE TABLE IF NOT EXISTS project_info (
  `proid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `content` TEXT NOT NULL,
  `teachers` TEXT NOT NULL,
  `partner` VARCHAR(200) NOT NULL,
  `period` VARCHAR(200) NOT NULL,
  `price` DOUBLE(10,2) NOT NULL DEFAULT 0,
  `group_name` VARCHAR(20) NOT NULL COMMENT '\'primary;junior-high;senior-high\'',
  `place` INT NOT NULL COMMENT '总人数',
  `type` VARCHAR(40) NOT NULL COMMENT '\'例如：计算机类、农业类\'',
  PRIMARY KEY (`proid`))
ENGINE = InnoDB;
INSERT INTO `project_info` (`proid`, `name`, `content`, `teachers`, `partner`, `period`, `price`, `group_name`, `place`,`type`) VALUES (1, '自动开门装置', '<h1>content test</h1>', '<h3>陈先生</h3><img  src="/laughman.jpg" width="100%">', 'partner1、partner2、partner3', '2019年5月-2019年6月', 1000, 'junior-high', 50,'工程类');
INSERT INTO `project_info` (`proid`, `name`, `content`, `teachers`, `partner`, `period`, `price`, `group_name`, `place`,`type`) VALUES (2, '四轴飞行器', '<h1>content test</h1>', '<h3>陈先生</h3><img  src="/laughman.jpg" width="100%">', 'partner1、partner2、partner3', '2019年5月-2019年6月', 1000, 'junior-high', 50,'工程类');
INSERT INTO `project_info` (`proid`, `name`, `content`, `teachers`, `partner`, `period`, `price`, `group_name`, `place`,`type`) VALUES (3, '自动开门装置', '<h1>content test</h1>', '<h3>陈先生</h3><img src="/laughman.jpg" width="100%">', 'partner1、partner2、partner3', '2019年5月-2019年6月', 1000, 'junior-high', 0,'工程类');
INSERT INTO `project_info` (`proid`, `name`, `content`, `teachers`, `partner`, `period`, `price`, `group_name`, `place`,`type`) VALUES (4, '自动开门装置', '<h1>content test</h1>', '<h3>陈先生</h3><img src="/laughman.jpg" width="100%">', 'partner1、partner2、partner3', '2019年5月-2019年6月', 1000, 'primary', 0,'工程类');
INSERT INTO `project_info` (`proid`, `name`, `content`, `teachers`, `partner`, `period`, `price`, `group_name`, `place`,`type`) VALUES (5, '自动开门装置', '<h1>content test</h1>', '<h3>陈先生</h3><img src="/laughman.jpg" width="100%">', 'partner1、partner2、partner3', '2019年5月-2019年6月', 1000, 'senior-high', 0,'工程类');