DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('role-query-user', 'role-query-user@bbortt.github.io', crypt('role-query-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'role-query-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));
