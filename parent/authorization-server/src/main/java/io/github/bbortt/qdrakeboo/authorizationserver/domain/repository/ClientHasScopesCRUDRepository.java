package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes.ClientHasScope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes.ClientHasScopeId;

@Repository
public interface ClientHasScopesCRUDRepository
    extends CrudRepository<ClientHasScope, ClientHasScopeId> {

  public Set<Scope> findAllByClient(Client client);

  public Set<Account> findAllByScope(Scope scope);
}
