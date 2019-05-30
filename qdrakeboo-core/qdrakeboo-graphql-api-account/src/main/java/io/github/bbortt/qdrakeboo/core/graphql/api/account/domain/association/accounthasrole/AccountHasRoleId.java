package io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.association.accounthasrole;


import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Role;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AccountHasRoleId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Account account;
  private Role role;

  @SuppressWarnings("unused")
  private AccountHasRoleId() {
    // Used by Hibernate
  }

  public AccountHasRoleId(Account account, Role role) {
    this.account = account;
    this.role = role;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (object == this) {
      return true;
    }
    if (object.getClass() != getClass()) {
      return false;
    }
    AccountHasRoleId accountHasRolesId = (AccountHasRoleId) object;
    return new EqualsBuilder()
        .append(account, accountHasRolesId.account)
        .append(role, accountHasRolesId.role)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
        append(account)
        .append(role)
        .toHashCode();
  }
}
