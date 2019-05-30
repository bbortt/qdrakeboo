package io.github.bbortt.qdrakeboo.core.graphql.api.account.repository;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCRUDRepository extends CrudRepository<Account, UUID> {

  public Optional<Account> findOneByAccountnameIgnoreCase(String accountname);
}
