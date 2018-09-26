DROP DATABASE IF EXISTS admgc;
CREATE DATABASE admgc CHARACTER SET UTF8;
USE admgc;

CREATE TABLE member(

    id      VARCHAR(20) comment 'identifier',  
    nickname    VARCHAR(20),
    username    VARCHAR(20),
    reg_at      datetime,
    CONSTRAINT PRIMARY KEY (id)
)ENGINE = innodb;

INSERT INTO member(id,nickname,username,reg_at) VALUES("1","Astronic","Astronic","2018-09-26");
INSERT INTO member(id,nickname,username,reg_at) VALUES("2","MegaAstronic","myusername","2018-09-24");
