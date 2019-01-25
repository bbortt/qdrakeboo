package io.github.bbortt.tv.core.authorizationserver.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;
import io.github.bbortt.tv.core.authorizationserver.util.DublicateCheckingSet;

public class Account extends AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "account";

  public static final String ACCOUNT_CREATED_RESULT_NAME = "account_created";
  public static final String ACCOUNT_LAST_UPDATED_RESULT_NAME = "account_last_updated";

  public static final String ID_RESULT_NAME = "id";
  public static final String ACCOUNTNAME_RESULT_NAME = "accountname";
  public static final String EMAIL_RESULT_NAME = "email";
  public static final String PASSWORD_RESULT_NAME = "password";
  public static final String IS_ENABLED_RESULT_NAME = "is_enabled";
  public static final String IS_BLOCKED_RESULT_NAME = "is_blocked";

  private long id;
  private String accountname;
  private String email;
  private String password;
  private boolean isEnabled = false;
  private boolean isBlocked = false;
  private Set<Role> roles = new DublicateCheckingSet<>();

  public Account() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void setBlocked(boolean isBlocked) {
    this.isBlocked = isBlocked;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles.clear();
    this.roles.addAll(roles);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(id).append(accountname).append(email).append(isEnabled)
        .append(isBlocked).append(roles).build();
  }
}
