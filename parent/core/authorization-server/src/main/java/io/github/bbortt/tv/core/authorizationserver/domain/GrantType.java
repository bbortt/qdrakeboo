package io.github.bbortt.tv.core.authorizationserver.domain;

public class GrantType extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "grant_type";

  public static final String GRANT_TYPE_CREATED_COLUMN_NAME = "grant_type_created";
  public static final String GRANT_TYPE_LAST_UPDATED_COLUMN_NAME = "grant_type_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

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
}
