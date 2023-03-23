CREATE DATABASE `db_prueba`;
CREATE USER 'demo'@'%' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON `db_prueba`.* TO 'demo'@'%';
FLUSH PRIVILEGES;
USE `db_prueba`;
CREATE TABLE `paises` (
                             `id` SMALLINT NOT NULL AUTO_INCREMENT,
                             `nombre` varchar(255) NOT NULL,
                             `poblacion` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UK_1` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
USE `db_prueba`;
BEGIN;
INSERT INTO `paises` (`id`, `nombre`, `poblacion`) VALUES
(1, 'Mexico', 130497248),
(2, 'Espana', 49067981),
(3, 'Colombia', 46070146);

COMMIT;