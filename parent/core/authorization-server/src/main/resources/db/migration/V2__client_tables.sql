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
CREATE TABLE authority (
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY authority
  ADD CONSTRAINT authority_pkey PRIMARY KEY (id);
  
-----------------------------------
---   CLIENT HAS AUTHORITIES    ---
-----------------------------------
CREATE TABLE client_has_authorities (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_id bigint NOT NULL,
  authority_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_pkey PRIMARY KEY (client_id, authority_id);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_authority FOREIGN KEY (authority_id) REFERENCES authority(id) ON DELETE RESTRICT;
 
-----------------------------------
---         GRANT TYPES         ---
-----------------------------------
CREATE TABLE grant_type (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 name character varying(32) NOT NULL
);

ALTER TABLE ONLY grant_type
  ADD CONSTRAINT grant_type_pkey PRIMARY KEY (id);

-- Add default values
INSERT INTO grant_type (created, last_updated, name)
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
  grant_type_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_pkey PRIMARY KEY (client_id, grant_type_id);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_grant_type FOREIGN KEY (grant_type_id) REFERENCES grant_type(id) ON DELETE RESTRICT;

-----------------------------------
---           SCOPES            ---
-----------------------------------
CREATE TABLE scope (
 id bigserial NOT NULL,
 created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
 name character varying(8) NOT NULL
);

ALTER TABLE ONLY scope
  ADD CONSTRAINT scope_pkey PRIMARY KEY (id);

-- Add default values
INSERT INTO scope (created, last_updated, name)
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
  scope_id bigint NOT NULL
);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_pkey PRIMARY KEY (client_id, scope_id);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_scope FOREIGN KEY (scope_id) REFERENCES scope(id) ON DELETE RESTRICT;
