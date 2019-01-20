-----------------------------------
---          ACCOUNTS           ---
-----------------------------------
CREATE TABLE account (
    id bigserial NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
    last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
    accountname character varying(64) NOT NULL,
    email character varying(128) NOT NULL,
    password character(60) NOT NULL,
    is_enabled boolean NOT NULL DEFAULT FALSE,
    is_blocked boolean NOT NULL DEFAULT FALSE
);

ALTER TABLE ONLY account
  ADD CONSTRAINT account_pkey PRIMARY KEY (id),
  ADD CONSTRAINT unique_accountname UNIQUE (accountname),
  ADD CONSTRAINT unique_email UNIQUE (email);

-----------------------------------
---         PRIVILEGES          ---
-----------------------------------
CREATE TABLE role (
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT now()::timestamp,
  name character varying(16) NOT NULL
);

ALTER TABLE ONLY role
  ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 
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
  account_id bigint NOT NULL,
  role_id bigint NOT NULL
);

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_pkey PRIMARY KEY (account_id, role_id);

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_account FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE;

ALTER TABLE ONLY account_has_roles
  ADD CONSTRAINT account_has_roles_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT;
