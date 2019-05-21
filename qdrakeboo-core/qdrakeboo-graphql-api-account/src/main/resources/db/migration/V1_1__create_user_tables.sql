-----------------------------------
---          ACCOUNTS           ---
-----------------------------------
CREATE TABLE account (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  accountname character varying(64) NOT NULL,
  email character varying(128) NOT NULL,
  password character(60) NOT NULL,
  enabled boolean NOT NULL DEFAULT FALSE,
  blocked boolean NOT NULL DEFAULT FALSE
);

ALTER TABLE ONLY account
  ADD CONSTRAINT account_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_accountname UNIQUE (accountname),
  ADD CONSTRAINT unique_email UNIQUE (email);

-----------------------------------
---         PRIVILEGES          ---
-----------------------------------
CREATE TABLE role (
  uuid uuid NOT NULL DEFAULT uuid_generate_v1(),
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY role
  ADD CONSTRAINT role_pkey PRIMARY KEY (uuid),
  ADD CONSTRAINT unique_role_name UNIQUE (name);
 
-- add default roles
INSERT INTO role(created, last_updated, name)
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
CREATE TABLE account_has_roles (
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  account_uuid uuid NOT NULL,
  role_uuid uuid NOT NULL
);

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_pkey PRIMARY KEY (account_uuid, role_uuid);

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_account FOREIGN KEY (account_uuid) REFERENCES account(uuid) ON DELETE CASCADE;

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_role FOREIGN KEY (role_uuid) REFERENCES role(uuid) ON DELETE RESTRICT;
