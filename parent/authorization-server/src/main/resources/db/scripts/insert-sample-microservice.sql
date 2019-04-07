INSERT INTO client
    (client_id, secret, resource_ids, access_token_validity, refresh_token_validity)
  VALUES
    (
      'deea2da1-21a6-492c-a0f3-a5e9cd6301e2',
      crypt('54e76b2f-fc53-4ec2-aee5-8d9df2fff625', gen_salt('bf')),
      'qdrakeboo',
      3600,
      7200
    );

INSERT INTO client_has_grant_types
    (client_uuid, grant_type_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = 'deea2da1-21a6-492c-a0f3-a5e9cd6301e2'),
      (SELECT uuid AS grant_type_uuid FROM grant_type WHERE name = 'authorization_code'));

INSERT INTO authority
    (name)
  VALUES
    ('microservice');

INSERT INTO client_has_authorities
    (client_uuid, authority_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = 'deea2da1-21a6-492c-a0f3-a5e9cd6301e2'),
      (SELECT uuid AS authority_uuid FROM authority WHERE name = 'microservice'));

INSERT INTO client_has_scopes
    (client_uuid, scope_uuid)
  VALUES
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = 'deea2da1-21a6-492c-a0f3-a5e9cd6301e2'),
      (SELECT uuid AS scope_uuid FROM scope WHERE name = 'read')),
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = 'deea2da1-21a6-492c-a0f3-a5e9cd6301e2'),
      (SELECT uuid AS scope_uuid FROM scope WHERE name = 'write')),
    ((SELECT uuid AS client_uuid FROM client WHERE client_id = 'deea2da1-21a6-492c-a0f3-a5e9cd6301e2'),
      (SELECT uuid AS scope_uuid FROM scope WHERE name = 'trust'));
