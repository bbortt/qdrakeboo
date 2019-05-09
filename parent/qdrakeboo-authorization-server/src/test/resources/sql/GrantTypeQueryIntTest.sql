DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('grant-type-query-user', 'grant-type-query-user@bbortt.github.io', crypt('grant-type-query-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'grant-type-query-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));
