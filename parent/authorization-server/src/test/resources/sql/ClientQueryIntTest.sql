DELETE FROM account_has_roles;
DELETE FROM account;

INSERT INTO account (accountname, email, password, enabled)
  VALUES
    ('client-query-user', 'client-query-user@bbortt.github.io', crypt('client-query-password', gen_salt('bf')), true);

INSERT INTO account_has_roles (account_uuid, role_uuid)
  VALUES
    ((SELECT uuid AS account_uuid FROM account WHERE accountname = 'client-query-user'), (SELECT uuid as role_uuid FROM role WHERE name = 'SERVER_SUPPORT'));

DELETE FROM client_has_authorities;
DELETE FROM client_has_grant_types;
DELETE FROM client_has_scopes;
DELETE FROM client;

INSERT INTO client (client_id, secret, resource_ids, access_token_validity, refresh_token_validity, redirect_uris)
  VALUES
    ('8f1de566-beed-4b9a-9e1c-32b4b381e737', crypt('45722944-3a1c-4c03-ad04-e5db245acd7d', gen_salt('bf')), 'test-resource', 3600, 7200, 'https://no.where');

DELETE FROM authority;

INSERT INTO authority (name)
  VALUES
    ('client-test');

INSERT INTO client_has_authorities (client_uuid, authority_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = '8f1de566-beed-4b9a-9e1c-32b4b381e737'), (SELECT uuid as authority_uuid FROM authority WHERE name = 'client-test'));

INSERT INTO client_has_grant_types (client_uuid, grant_type_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = '8f1de566-beed-4b9a-9e1c-32b4b381e737'), (SELECT uuid as grant_type_uuid FROM grant_type WHERE name = 'implicit'));

INSERT INTO client_has_scopes (client_uuid, scope_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = '8f1de566-beed-4b9a-9e1c-32b4b381e737'), (SELECT uuid as scope_uuid FROM scope WHERE name = 'read'));
