DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('client-mutation-user', 'client-mutation-user@bbortt.github.io', crypt('client-mutation-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'client-mutation-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));

DELETE FROM client_has_authorities;
DELETE FROM client;
DELETE FROM authority;
