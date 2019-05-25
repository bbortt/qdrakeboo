DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('io.github.bbortt.qdrakeboo.core.graphql.account-mutation-user', 'io.github.bbortt.qdrakeboo.core.graphql.account-mutation-user@bbortt.github.io', crypt('io.github.bbortt.qdrakeboo.core.graphql.account-mutation-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'io.github.bbortt.qdrakeboo.core.graphql.account-mutation-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));
