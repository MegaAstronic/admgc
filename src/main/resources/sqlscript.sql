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

select COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_DEFAULT,COLUMN_COMMENT from information_schema.COLUMNS where TABLE_NAME='member' ;