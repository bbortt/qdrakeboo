package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes.ClientHasGrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes.ClientHasGrantTypeId;

@Repository
public interface ClientHasGrantTypesCRUDRepository
    extends CrudRepository<ClientHasGrantType, ClientHasGrantTypeId> {

  public Set<GrantType> findAllByClient(Client client);

  public Set<Client> findAllByGrantType(GrantType grantType);
}
