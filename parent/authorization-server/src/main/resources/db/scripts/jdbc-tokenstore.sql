-- create database
CREATE DATABASE bbortt_tv_tokenstore;

-- user configuration
CREATE USER bbortt_tv_tokenstore WITH PASSWORD 'bbortt_tv_tokenstore';
GRANT ALL PRIVILEGES ON DATABASE bbortt_tv_tokenstore TO bbortt_tv_tokenstore;

-- connect to the database
\connect bbortt_tv_tokenstore

-----------------------------------
---         TOKEN STORE         ---
-----------------------------------
CREATE TABLE oauth_access_token (
	token_id character varying(256) NOT NULL,
	token bytea NOT NULL,
	authentication_id character varying(256) NOT NULL,
	user_name character varying(256) NOT NULL,
	client_id character varying(256) NOT NULL,
	authentication bytea NOT NULL,
	refresh_token character varying(256) NOT NULL
);

ALTER TABLE ONLY oauth_access_token
    ADD CONSTRAINT oauth_access_token_pkey PRIMARY KEY (token_id);

-----------------------------------
---       REFRESH TOKENS        ---
-----------------------------------
CREATE TABLE oauth_refresh_token (
	token_id character varying(256) NOT NULL,
	token bytea NOT NULL,
	authentication bytea NOT NULL
);

ALTER TABLE ONLY oauth_refresh_token
    ADD CONSTRAINT oauth_refresh_token_pkey PRIMARY KEY (token_id);
