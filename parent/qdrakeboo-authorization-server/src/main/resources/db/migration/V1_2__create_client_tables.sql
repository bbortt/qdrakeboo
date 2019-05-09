-----------------------------------
---           CLIENTS           ---
-----------------------------------
CREATE TABLE client (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_id character(36) NOT NULL,
  secret character(60) NOT NULL,
  resource_ids character varying(256),
  secret_required boolean NOT NULL DEFAULT TRUE,
  auto_approve boolean NOT NULL DEFAULT FALSE,
  access_token_validity integer NOT NULL,
  refresh_token_validity integer NOT NULL,
  redirect_uris character varying(256)
);

ALTER TABLE ONLY client
  ADD CONSTRAINT client_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_client_id UNIQUE (client_id);

-----------------------------------
---         AUTHORITIES         ---
-----------------------------------
CREATE TABLE authority (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY authority
  ADD CONSTRAINT authority_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_authority_name UNIQUE (name);
  
-----------------------------------
---   CLIENT HAS AUTHORITIES    ---
-----------------------------------
CREATE TABLE client_has_authorities (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_uuid uuid NOT NULL,
  authority_uuid uuid NOT NULL
);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_pkey PRIMARY KEY (client_uuid, authority_uuid);

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_client FOREIGN KEY (client_uuid) REFERENCES client(uuid) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_authorities
  ADD CONSTRAINT client_has_authorities_authority FOREIGN KEY (authority_uuid) REFERENCES authority(uuid) ON DELETE RESTRICT;
 
-----------------------------------
---         GRANT TYPES         ---
-----------------------------------
CREATE TABLE grant_type (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(32) NOT NULL
);

ALTER TABLE ONLY grant_type
  ADD CONSTRAINT grant_type_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_grant_type_name UNIQUE (name);

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
  client_uuid uuid NOT NULL,
  grant_type_uuid uuid NOT NULL
);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_pkey PRIMARY KEY (client_uuid, grant_type_uuid);

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_client FOREIGN KEY (client_uuid) REFERENCES client(uuid) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_grant_types
  ADD CONSTRAINT client_has_grant_types_grant_type FOREIGN KEY (grant_type_uuid) REFERENCES grant_type(uuid) ON DELETE RESTRICT;

-----------------------------------
---           SCOPES            ---
-----------------------------------
CREATE TABLE scope (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(8) NOT NULL
);

ALTER TABLE ONLY scope
  ADD CONSTRAINT scope_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_scope_name unique (name);

-- Add default values
INSERT INTO scope (created, last_updated, name)
 VALUES (now(), now(), 'read'),
  (now(), now(), 'write'),
  (now(), now(), 'trust');

-----------------------------------
---           SCOPES            ---
-----------------------------------
CREATE TABLE client_has_scopes (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  client_uuid uuid NOT NULL,
  scope_uuid uuid NOT NULL
);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_pkey PRIMARY KEY (client_uuid, scope_uuid);

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_client FOREIGN KEY (client_uuid) REFERENCES client(uuid) ON DELETE CASCADE;

ALTER TABLE ONLY client_has_scopes
  ADD CONSTRAINT client_has_scopes_scope FOREIGN KEY (scope_uuid) REFERENCES scope(uuid) ON DELETE RESTRICT;
