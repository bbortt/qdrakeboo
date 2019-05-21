-- create database
CREATE DATABASE graphql_account_api;

-- user configuration
CREATE USER graphql_account_api WITH PASSWORD 'graphql_account_api';
GRANT ALL PRIVILEGES ON DATABASE graphql_account_api TO graphql_account_api;
