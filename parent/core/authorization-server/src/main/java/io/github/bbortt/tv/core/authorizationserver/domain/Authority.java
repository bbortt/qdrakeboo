package io.github.bbortt.tv.core.authorizationserver.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;

public class Authority extends AbstractAuditingEntity implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "authority";

  public static final String AUTHORITY_CREATED_RESULT_NAME = "authority_created";
  public static final String AUTHORITY_LAST_UPDATED_RESULT_NAME = "authority_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String ID_RESULT_NAME = "authority_id";

  public static final String NAME_COLUMN_NAME = "name";
  public static final String NAME_RESULT_NAME = "authority_name";

  private long id;
  private String name;

  public Authority() {

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
    Authority authority = (Authority) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(id, authority.id)
        .append(name, authority.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(name).build();
  }
}
