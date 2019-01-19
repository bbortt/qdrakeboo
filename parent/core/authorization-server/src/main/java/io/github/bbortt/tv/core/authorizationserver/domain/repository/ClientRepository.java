package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import org.springframework.security.oauth2.provider.ClientDetails;

public interface ClientRepository {

  public Optional<ClientDetails> findOneByClientId(String clientId);
}
