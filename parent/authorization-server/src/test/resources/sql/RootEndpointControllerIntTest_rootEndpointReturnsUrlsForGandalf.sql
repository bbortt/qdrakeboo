DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('gandalf-user', 'gandalf-user@bbortt.github.io', crypt('gandalf-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid as account_uuid FROM account WHERE accountname = 'gandalf-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'GANDALF'));
