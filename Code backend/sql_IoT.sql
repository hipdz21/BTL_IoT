CREATE DATABASE btl_iot_httc
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;
-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE btl_iot_httc;

--
-- Create table `account`
--
CREATE TABLE account (
  id int NOT NULL AUTO_INCREMENT,
  authen_token varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  token varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = MYISAM,
AUTO_INCREMENT = 2,
AVG_ROW_LENGTH = 92,
CHARACTER SET utf8mb4,
CHECKSUM = 0,
COLLATE utf8mb4_0900_ai_ci;
-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE btl_iot_httc;

--
-- Create table `clocks`
--
CREATE TABLE clocks (
  id int NOT NULL AUTO_INCREMENT,
  day date DEFAULT NULL,
  hour time DEFAULT NULL,
  repeats bit(1) NOT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = MYISAM,
AUTO_INCREMENT = 16,
AVG_ROW_LENGTH = 15,
CHARACTER SET utf8mb4,
CHECKSUM = 0,
COLLATE utf8mb4_0900_ai_ci;

--
-- Create index `FKjqx1qguit7f4qfbk0n4dvyxx9` on table `clocks`
--
ALTER TABLE clocks
ADD INDEX FKjqx1qguit7f4qfbk0n4dvyxx9 (account_id);
-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE btl_iot_httc;

--
-- Create table `data_sensor`
--
CREATE TABLE data_sensor (
  id int NOT NULL AUTO_INCREMENT,
  date datetime DEFAULT NULL,
  huminity float NOT NULL,
  soil_huminity float NOT NULL,
  temperature float NOT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = MYISAM,
AUTO_INCREMENT = 401,
AVG_ROW_LENGTH = 26,
CHARACTER SET utf8mb4,
CHECKSUM = 0,
COLLATE utf8mb4_0900_ai_ci;

--
-- Create index `FKppwjvx0u0owik9vl5du18ihkw` on table `data_sensor`
--
ALTER TABLE data_sensor
ADD INDEX FKppwjvx0u0owik9vl5du18ihkw (account_id);
-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE btl_iot_httc;

--
-- Create table `parameters`
--
CREATE TABLE parameters (
  id varchar(100) NOT NULL,
  name varchar(255) DEFAULT NULL,
  value float DEFAULT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = MYISAM,
AVG_ROW_LENGTH = 44,
CHARACTER SET utf8mb4,
CHECKSUM = 0,
COLLATE utf8mb4_0900_ai_ci;

--
-- Create index `FKc2o1mle3dapkd24vd6wwoidyg` on table `parameters`
--
ALTER TABLE parameters
ADD INDEX FKc2o1mle3dapkd24vd6wwoidyg (account_id);
INSERT INTO btl_iot_httc.account(id, authen_token, password, token, username) VALUES
(, '6366270ea74472075a96442d', 'quang21', 'vVz50iBrhDuYOCJVJqfXWTp1Vxl9p8kyMEbo0lwp', 'quang21');
INSERT INTO btl_iot_httc.parameters(id, name, value, account_id) VALUES
('Lm2ioDsrGUUDBQnm', 'Sensor.Parameter1', NULL, 1),
('AosiNzTjBiWUYsLH', 'Sensor.Parameter2', NULL, 1),
('yiylmb8jxwLT2sxN', 'Sensor.Parameter3', NULL, 1),
('oKRW1OQWTThFrYSY', 'Sensor.Parameter4', NULL, 1),
('JNOcbC9jqJubnAqT', 'Sensor.Parameter5', NULL, 1),
('uJpObyPkRcRT321Z', 'Sensor.Parameter6', NULL, 1);