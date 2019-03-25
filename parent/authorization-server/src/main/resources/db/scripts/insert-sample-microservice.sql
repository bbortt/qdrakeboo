-- RUN `insert-sample-ui-client.sql` first!

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
    (client_id, grant_type_id)
  VALUES
    (2, 1);

INSERT INTO authority
    (name)
  VALUES
    ('principal');

INSERT INTO client_has_authorities
    (client_id, authority_id)
  VALUES
    (2, 2);

INSERT INTO client_has_scopes
    (client_id, scope_id)
  VALUES
    (2, 1), (2, 2), (2, 3);
