package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.accounthasroles;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;

public class AccountHasRoleId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Account account;
  private Role role;

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
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
    return new EqualsBuilder().append(account, accountHasRolesId.account)
        .append(role, accountHasRolesId.role).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(account).append(role)
        .toHashCode();
  }
}
