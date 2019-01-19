-----------------------------------
---          ACCOUNTS           ---
-----------------------------------
CREATE TABLE user (
    id bigserial NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
    last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
    username character varying(64) NOT NULL,
    email character varying(128) NOT NULL,
    password character(60) NOT NULL,
    is_enabled boolean NOT NULL DEFAULT FALSE,
    is_blocked boolean NOT NULL DEFAULT FALSE
);

ALTER TABLE ONLY user
  ADD CONSTRAINT user_pkey PRIMARY KEY (id),
  ADD CONSTRAINT unique_username UNIQUE (username),
  ADD CONSTRAINT unique_email UNIQUE (email);

-----------------------------------
---         PRIVILEGES          ---
-----------------------------------
CREATE TABLE user_role (
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY user_role
  ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);
 
-- add default roles
INSERT INTO user_role(created, last_updated, name)
 VALUES (now(), now(), 'GANDALF'),
   (now(), now(), 'SERVER_ADMIN'),
   (now(), now(), 'SERVER_SUPPORT'),
   (now(), now(), 'CONTENT_ADMIN'),
   (now(), now(), 'CONTENT_SUPPORT'),
   (now(), now(), 'PREMIUM_USER'),
   (now(), now(), 'USER'),
   (now(), now(), 'GUEST');

-----------------------------------
---       USER HAS ROLES        ---
-----------------------------------
CREATE TABLE user_has_roles (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  user_id bigint NOT NULL,
  user_role_id bigint NOT NULL
);

ALTER TABLE ONLY user_has_roles
  ADD CONSTRAINT user_has_roles_pkey PRIMARY KEY (user_id, user_role_id);

ALTER TABLE ONLY user_has_roles
  ADD CONSTRAINT user_has_roles_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE ONLY user_has_roles
  ADD CONSTRAINT user_has_roles_role FOREIGN KEY (user_role_id) REFERENCES user_role(id) ON DELETE RESTRICT;
 
-----------------------------------
---           CLIENTS           ---
-----------------------------------
CREATE TABLE client (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 client_id character(36) NOT NULL,
 secret character(36),
 secret_required boolean NOT NULL DEFAULT TRUE,
 auto_approve boolean NOT NULL DEFAULT FALSE,
 access_token_validity integer NOT NULL,
 refresh_token_validity integer NOT NULL,
 redirect_url character varying(256)
);

ALTER TABLE ONLY client
  ADD CONSTRAINT client_pkey PRIMARY KEY (id),
  ADD CONSTRAINT unique_client_id UNIQUE (client_id);

-----------------------------------
---         AUTHORITIES         ---
-----------------------------------
CREATE TABLE client_authority (
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY client_authority
  ADD CONSTRAINT client_authority_pkey PRIMARY KEY (id);
  
-----------------------------------
---   CLIENT HAS AUTHORITIES    ---
-----------------------------------
CREATE TABLE client_has_authorities (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_id bigint NOT NULL,
  client_authority_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_pkey PRIMARY KEY (client_id, client_authority_id);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_authority FOREIGN KEY (client_authority_id) REFERENCES client_authority(id) ON DELETE RESTRICT;
 
-----------------------------------
---         GRANT TYPES         ---
-----------------------------------
CREATE TABLE client_grant_type (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 name character varying(32) NOT NULL
);

ALTER TABLE ONLY client_grant_type
  ADD CONSTRAINT client_grant_type_pkey PRIMARY KEY (id);

-- Add default values
INSERT INTO client_grant_type (created, last_updated, name)
 VALUES (now(), now(), 'authorization_code'),
 (now(), now(), 'refresh_token'),
 (now(), now(), 'implicit'),
 (now(), now(), 'password');

-----------------------------------
---   CLIENT HAS GRANT TYPES    ---
-----------------------------------
CREATE TABLE client_has_grant_types (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_id bigint NOT NULL,
  client_grant_type_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_pkey PRIMARY KEY (client_id, client_grant_type_id);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_grant_type FOREIGN KEY (client_grant_type_id) REFERENCES client_grant_type(id) ON DELETE RESTRICT;

-----------------------------------
---           SCOPES            ---
-----------------------------------
CREATE TABLE client_scope (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 name character varying(8) NOT NULL
);

ALTER TABLE ONLY client_scope
  ADD CONSTRAINT client_scope_pkey PRIMARY KEY (id);

-- Add default values
INSERT INTO client_scope (created, last_updated, name)
 VALUES (now(), now(), 'read'),
  (now(), now(), 'write'),
  (now(), now(), 'trust');

-----------------------------------
---           SCOPES            ---
-----------------------------------
CREATE TABLE client_has_scopes (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 client_id bigint NOT NULL,
  client_scope_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_pkey PRIMARY KEY (client_id, client_scope_id);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_scope FOREIGN KEY (client_scope_id) REFERENCES client_scope(id) ON DELETE RESTRICT;
