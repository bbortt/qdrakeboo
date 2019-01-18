package io.github.bbortt.tv.core.authorizationserver.domain;

import java.util.HashSet;
import java.util.Set;

public class User extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "users";

  public static final String USER_CREATED_COLUMN_NAME = "user_created";
  public static final String USER_LAST_UPDATED_COLUMN_NAME = "user_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String USERNAME_COLUMN_NAME = "username";
  public static final String EMAIL_COLUMN_NAME = "email";
  public static final String PASSWORD_COLUMN_NAME = "password";
  public static final String IS_ENABLED_COLUMN_NAME = "is_enabled";
  public static final String IS_BLOCKED_COLUMN_NAME = "is_blocked";

  private long id;
  private String username;
  private String email;
  private String password;
  private boolean isEnabled = false;
  private boolean isBlocked = false;
  private Set<UserRole> roles = new HashSet<>();

  public User() {

  }

  public User(String username, String email, String password, boolean isEnabled, boolean isBlocked,
      Set<UserRole> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.isEnabled = isEnabled;
    this.isBlocked = isBlocked;
    this.roles = roles;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }
}
