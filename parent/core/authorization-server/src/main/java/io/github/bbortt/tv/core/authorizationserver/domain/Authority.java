package io.github.bbortt.tv.core.authorizationserver.domain;

public class Authority extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "authority";

  public static final String AUTHORITY_CREATED_COLUMN_NAME = "authority_created";
  public static final String AUTHORITY_LAST_UPDATED_COLUMN_NAME = "authority_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

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
}
