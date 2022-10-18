CREATE DATABASE buildabike;

CREATE USER 'client'@'%' IDENTIFIED BY 'password';

GRANT INSERT, UPDATE, DELETE, SELECT ON data.* TO 'client'@'%';
