package io.github.bbortt.qdrakeboo.model.account;

import io.github.bbortt.qdrakeboo.model.account.association.accounthasrole.AccountHasRole;
import io.github.bbortt.qdrakeboo.model.autoconfiguration.domain.AbstractAuditingEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Table
@Entity
public class Account extends AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Type(type = "pg-uuid")
  @GeneratedValue(generator = "account-uuid")
  @GenericGenerator(name = "account-uuid",
      strategy = "io.github.bbortt.qdrakeboo.model.autoconfiguration.domain.PostgreSQLUUIDGenerationStrategy")
  @Column(nullable = false, unique = true, columnDefinition = "uuid")
  private UUID uuid;

  @Size(min = 5, max = 64)
  @Column(nullable = false, unique = true)
  private String accountname;

  @Email
  @NotEmpty
  @Size(max = 128)
  @Column(nullable = false, unique = true)
  private String email;

  @Size(min = 60, max = 60)
  @Column(nullable = false, columnDefinition = "bpchar(60)")
  private String password;

  @Transient
  private transient String confirmation;

  @Column(nullable = false)
  private boolean enabled = false;

  @Column(nullable = false)
  private boolean blocked = false;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
  private Set<AccountHasRole> roles = new HashSet<>();

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getAccountname() {
    return accountname;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmation() {
    return confirmation;
  }

  public void setConfirmation(String confirmation) {
    this.confirmation = confirmation;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.enabled = isEnabled;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean isBlocked) {
    this.blocked = isBlocked;
  }

  public List<Role> getRoles() {
    return roles.stream().map(AccountHasRole::getRole).collect(Collectors.toList());
  }

  public void setRoles(List<Role> roles) {
    this.roles =
        roles.stream().map(role -> new AccountHasRole(this, role)).collect(Collectors.toSet());
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
    Account account = (Account) object;
    return new EqualsBuilder()
        .appendSuper(super.equals(object))
        .append(uuid, account.uuid)
        .append(accountname, account.accountname)
        .append(email, account.email)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .appendSuper(super.hashCode())
        .append(uuid)
        .append(accountname)
        .append(email)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .appendSuper(super.toString())
        .append("uuid", uuid)
        .append("accountname", accountname)
        .append("email", email)
        .append("enabled", enabled)
        .append("blocked", blocked)
        .append("roles", getRoles())
        .build();
  }
}
