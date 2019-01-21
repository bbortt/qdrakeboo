package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;

public interface ClientRepository {

  public Optional<Client> findOneByClientId(String clientId);
}
