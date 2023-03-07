###### Autor: marcio28costa@hotmail.com | exportação de usúarios v.1 | Java 11 ########
###### host: localhost | porta: 3306 | versão : 5.7.28 #########
###### Geração da consulta: Sun Mar 05 01:36:46 BRT 2023 ########
-- `marcio`@`%` --
CREATE USER 'marcio'@'%' IDENTIFIED WITH 'mysql_native_password' AS '*495D2939D3D3FFE62C19FC84D44F0AF190D27192' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'%';
-- `monitor`@`%` --
CREATE USER 'monitor'@'%' IDENTIFIED WITH 'mysql_native_password' AS '*1975D095AC033CAF4E1BF94F7202A9BBFEEB66F1' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT ALL PRIVILEGES ON *.* TO 'monitor'@'%';
-- `sistema`@`%` --
CREATE USER 'sistema'@'%' IDENTIFIED WITH 'mysql_native_password' AS '*3F4EEB4B3017A959FC6E794B13E460E39CE7E2BB' REQUIRE NONE WITH MAX_USER_CONNECTIONS 10 PASSWORD EXPIRE INTERVAL 10 DAY ACCOUNT UNLOCK;
GRANT USAGE ON *.* TO 'sistema'@'%';
GRANT ALL PRIVILEGES ON `sistema`.* TO 'sistema'@'%';
GRANT ALL PRIVILEGES ON `sakila`.* TO 'sistema'@'%';
-- `world`@`%` --
CREATE USER 'world'@'%' IDENTIFIED WITH 'mysql_native_password' AS '*70BB17D9634310906050FED37E38259FFAE578B8' REQUIRE NONE WITH MAX_USER_CONNECTIONS 20 PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT USAGE ON *.* TO 'world'@'%';
GRANT SELECT ON `world`.* TO 'world'@'%';
GRANT UPDATE ON `sakila`.* TO 'world'@'%';
-- `marcio`@`127.0.0.1` --
CREATE USER 'marcio'@'127.0.0.1' IDENTIFIED WITH 'mysql_native_password' AS '*495D2939D3D3FFE62C19FC84D44F0AF190D27192' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'127.0.0.1';
-- `marcio`@`localhost` --
CREATE USER 'marcio'@'localhost' IDENTIFIED WITH 'mysql_native_password' AS '*495D2939D3D3FFE62C19FC84D44F0AF190D27192' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'localhost';
-- `root`@`localhost` --
CREATE USER 'root'@'localhost' IDENTIFIED WITH 'mysql_native_password' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
GRANT PROXY ON ''@'' TO 'root'@'localhost' WITH GRANT OPTION;
-- `sistema`@`localhost` --
CREATE USER 'sistema'@'localhost' IDENTIFIED WITH 'mysql_native_password' AS '*07558786B86003A2E3140B8A4931E28A272918E7' REQUIRE NONE PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT SHUTDOWN, PROCESS ON *.* TO 'sistema'@'localhost';
GRANT SELECT ON `world`.* TO 'sistema'@'localhost';
GRANT SELECT, CREATE ON `treinamento`.* TO 'sistema'@'localhost';
GRANT SELECT ON `sys`.* TO 'sistema'@'localhost';
-- exportação feita com sucesso -- 