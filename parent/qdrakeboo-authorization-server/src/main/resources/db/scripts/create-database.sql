-- create database
CREATE DATABASE qdrakeboo_authorization;

-- user configuration
CREATE USER qdrakeboo_authorization WITH PASSWORD 'qdrakeboo_authorization';
GRANT ALL PRIVILEGES ON DATABASE qdrakeboo_authorization TO qdrakeboo_authorization;
