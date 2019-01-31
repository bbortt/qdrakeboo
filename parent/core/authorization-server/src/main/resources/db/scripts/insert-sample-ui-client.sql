INSERT INTO client
    (client_id, secret, access_token_validity, refresh_token_validity, redirect_uris)
  VALUES
    (
      'cea19e6f-fa31-407f-a05a-a20d28c4c74d',
      crypt('8779af4a-d120-4bea-9993-dea2fbc4b571', gen_salt('bf')),
      3600,
      7200,
      'http://localhost:3000'
    );

INSERT INTO client_has_grant_types
    (client_id, grant_type_id)
  VALUES
    (1, 1);

INSERT INTO authority
    (name)
  VALUES
    ('ui');

INSERT INTO client_has_authorities
    (client_id, authority_id)
  VALUES
    (1, 1);

INSERT INTO client_has_scopes
    (client_id, scope_id)
  VALUES
    (1, 1);
