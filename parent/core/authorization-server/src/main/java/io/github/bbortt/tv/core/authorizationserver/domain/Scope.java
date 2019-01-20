package io.github.bbortt.tv.core.authorizationserver.domain;

public class Scope extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "scope";

  public static final String SCOPE_CREATED_COLUMN_NAME = "scope_created";
  public static final String SCOPE_LAST_UPDATED_COLUMN_NAME = "scope_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

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
}
