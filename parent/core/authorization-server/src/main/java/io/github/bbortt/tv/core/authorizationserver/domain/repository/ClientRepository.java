package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

  private final DataSource dataSource;

  public ClientRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Optional<ClientDetails> findOneByClientId(String clientId) {
    // TODO Auto-generated method stub
    return null;
  }
}
