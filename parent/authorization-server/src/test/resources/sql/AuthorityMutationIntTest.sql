DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('authority-mutation-user', 'authority-mutation-user@bbortt.github.io', crypt('authority-mutation-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'authority-mutation-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));

DELETE FROM authority;

INSERT INTO authority (uuid, name)
  VALUES
    ('ebe7611f-652e-4fa5-91fa-474be380488a', 'to-delete');
