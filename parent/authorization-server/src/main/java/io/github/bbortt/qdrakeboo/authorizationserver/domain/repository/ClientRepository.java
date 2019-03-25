package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;

public interface ClientRepository {

  public Optional<Client> findOneByClientId(String clientId);
}
