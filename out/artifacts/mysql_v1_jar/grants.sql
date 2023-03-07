###### Autor: marcio28costa@hotmail.com | exportação de usúarios v.1 | Java 11 ########
###### host: localhost | porta: 3306 | versão : 5.6.22 #########
###### Geração da consulta: Sun Mar 05 01:40:41 BRT 2023 ########
-- `admin`@`%` --
GRANT SELECT, PROCESS ON *.* TO 'admin'@'%' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441';
GRANT SELECT ON `admin`.* TO 'admin'@'%';
GRANT ALL PRIVILEGES ON `test`.* TO 'admin'@'%';
-- `backup`@`%` --
GRANT SELECT ON *.* TO 'backup'@'%' IDENTIFIED BY PASSWORD '*1827DC630AAEB1E997DB2B212CC94EFD9C431555';
-- `marcio`@`%` --
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'%' IDENTIFIED BY PASSWORD '*495D2939D3D3FFE62C19FC84D44F0AF190D27192';
-- `root`@`%` --
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
-- `world`@`%` --
GRANT USAGE ON *.* TO 'world'@'%';
GRANT UPDATE ON `sakila`.* TO 'world'@'%';
-- `marcio`@`127.0.0.1` --
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'127.0.0.1' IDENTIFIED BY PASSWORD '*495D2939D3D3FFE62C19FC84D44F0AF190D27192';
-- `root`@`127.0.0.1` --
GRANT ALL PRIVILEGES ON *.* TO 'root'@'127.0.0.1' WITH GRANT OPTION;
-- `root`@`::1` --
GRANT ALL PRIVILEGES ON *.* TO 'root'@'::1' WITH GRANT OPTION;
-- `marcio`@`localhost` --
GRANT ALL PRIVILEGES ON *.* TO 'marcio'@'localhost' IDENTIFIED BY PASSWORD '*495D2939D3D3FFE62C19FC84D44F0AF190D27192';
-- `root`@`localhost` --
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
GRANT PROXY ON ''@'' TO 'root'@'localhost' WITH GRANT OPTION;
-- exportação feita com sucesso -- 