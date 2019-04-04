package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.accounthasroles.AccountHasRole;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.accounthasroles.AccountHasRoleId;

@Repository
public interface AccountHasRolesCRUDRepository
    extends CrudRepository<AccountHasRole, AccountHasRoleId> {

  public Set<Role> findAllByAccount(Account account);

  public Set<Account> findAllByRole(Role role);
}
