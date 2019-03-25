package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;

public class Role extends AbstractAuditingEntity implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "role";

  public static final String ROLE_CREATED_COLUMN_NAME = "role_created";
  public static final String ROLE_LAST_UPDATED_RESULT_NAME = "role_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String ID_RESULT_NAME = "role_id";

  public static final String NAME_COLUMN_NAME = "name";
  public static final String NAME_RESULT_NAME = "role_name";

  private long id;
  private String name;

  public Role() {

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
    Role role = (Role) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(id, role.id)
        .append(name, role.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(id).append(name).build();
  }
}
