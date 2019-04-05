DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('some-accountname', 'some-account@bbortt.github.io', crypt('some-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid as account_uuid FROM account WHERE accountname = 'some-accountname'), (SELECT uuid as role_uuid FROM role WHERE name = 'GUEST'));
