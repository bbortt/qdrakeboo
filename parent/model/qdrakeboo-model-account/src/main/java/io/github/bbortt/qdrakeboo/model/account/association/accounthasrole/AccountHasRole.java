package io.github.bbortt.qdrakeboo.model.account.association.accounthasrole;

import io.github.bbortt.qdrakeboo.model.account.Account;
import io.github.bbortt.qdrakeboo.model.account.Role;
import io.github.bbortt.qdrakeboo.model.autoconfiguration.domain.AbstractAuditingEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@IdClass(AccountHasRoleId.class)
@Table(name = "account_has_roles")
public class AccountHasRole extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "account_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.ALL})
  private Account account;

  @Id
  @JoinColumn(name = "role_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  private Role role;

  @SuppressWarnings("unused")
  private AccountHasRole() {
    // Used by Hibernate
  }

  public AccountHasRole(Account account, Role role) {
    this.account = account;
    this.role = role;
  }

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
    AccountHasRole accountHasRole = (AccountHasRole) object;
    return new EqualsBuilder()
        .appendSuper(super.equals(object))
        .append(account, accountHasRole.account)
        .append(role, accountHasRole.role)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .appendSuper(super.hashCode())
        .append(account)
        .append(role)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .appendSuper(super.toString())
        .append("account", account)
        .append("role", role)
        .build();
  }
}
