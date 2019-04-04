package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;

@Repository
public interface AccountCRUDRepository extends CrudRepository<Account, UUID> {

  public Optional<Account> findOneByAccountname(String accountname);
}
