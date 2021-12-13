CREATE DATABASE stackoverflow;
CREATE USER 'toy-stackoverflow'@'localhost' IDENTIFIED BY 'stackoverflow';
GRANT ALL PRIVILEGES ON stackoverflow.* TO 'toy-stackoverflow'@'localhost';