-- clean user data
DROP TABLE account_has_role;
DROP TABLE account;
DROP TABLE role;

-- client data
DROP TABLE client_has_authorities;
DROP TABLE client_has_grant_types;
DROP TABLE client_has_scopes;
DROP TABLE client;
DROP TABLE authority;
DROP TABLE grant_type;
DROP TABLE scope;

-- flyway data
DROP TABLE flyway_schema_history;
