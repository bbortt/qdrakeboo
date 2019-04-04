package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities.ClientHasAuthority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities.ClientHasAuthorityId;

@Repository
public interface ClientHasAuthoritiesCRUDRepository
    extends CrudRepository<ClientHasAuthority, ClientHasAuthorityId> {

  public Set<Authority> findAllByClient(Client client);

  public Set<Account> findAllByAuthority(Authority authority);
}
