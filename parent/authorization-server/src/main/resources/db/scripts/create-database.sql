-- create database
CREATE DATABASE bbortt_tv_authorization;

-- user configuration
CREATE USER bbortt_tv_authorization WITH PASSWORD 'uwillneverguessthisone';
GRANT ALL PRIVILEGES ON DATABASE bbortt_tv_authorization TO bbortt_tv_authorization;
