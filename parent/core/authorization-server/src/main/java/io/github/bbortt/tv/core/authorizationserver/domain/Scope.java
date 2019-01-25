package io.github.bbortt.tv.core.authorizationserver.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Scope extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "scope";

  public static final String SCOPE_CREATED_RESULT_NAME = "scope_created";
  public static final String SCOPE_LAST_UPDATED_RESULT_NAME = "scope_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String ID_RESULT_NAME = "scope_id";

  public static final String NAME_COLUMN_NAME = "name";
  public static final String NAME_RESULT_NAME = "scope_name";

  private long id;
  private String name;

  public Scope() {

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
    Scope scope = (Scope) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(id, scope.id)
        .append(name, scope.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(id).append(name).build();
  }
}
