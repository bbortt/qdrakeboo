package io.github.bbortt.tv.core.authorizationserver.domain;

import org.springframework.security.core.GrantedAuthority;

public class UserRole extends AbstractAuditingEntity implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "user_role";

  public static final String USER_ROLE_CREATED_COLUMN_NAME = "user_role_created";
  public static final String USER_ROLE_LAST_UPDATED_COLUMN_NAME = "user_role_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

  private long id;
  private String name;

  public UserRole() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return getName();
  }
}
