package io.github.bbortt.qdrakeboo.core.graphql.account.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.model.account.Account;
import io.github.bbortt.qdrakeboo.model.account.Role;
import io.github.bbortt.qdrakeboo.model.account.association.accounthasrole.AccountHasRole;
import io.github.bbortt.qdrakeboo.model.account.association.accounthasrole.AccountHasRoleId;

@Repository
public interface AccountHasRolesCRUDRepository
    extends CrudRepository<AccountHasRole, AccountHasRoleId> {

  public Set<Role> findAllByAccount(Account account);

  public Set<Account> findAllByRole(Role role);
}
