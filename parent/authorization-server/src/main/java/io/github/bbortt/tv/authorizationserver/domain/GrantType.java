package io.github.bbortt.tv.authorizationserver.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GrantType extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "grant_type";

  public static final String GRANT_TYPE_CREATED_RESULT_NAME = "grant_type_created";
  public static final String GRANT_TYPE_LAST_UPDATED_RESULT_NAME = "grant_type_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String ID_RESULT_NAME = "grant_type_id";

  public static final String NAME_COLUMN_NAME = "name";
  public static final String NAME_RESULT_NAME = "grant_type_name";

  private long id;
  private String name;

  public GrantType() {

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
    GrantType grantType = (GrantType) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(id, grantType.id)
        .append(name, grantType.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(id).append(name).build();
  }
}
