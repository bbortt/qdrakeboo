package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;

@Repository
public interface ClientCRUDRepository extends CrudRepository<Client, UUID> {

  public Optional<Client> findOneByClientId(String clientId);
}
