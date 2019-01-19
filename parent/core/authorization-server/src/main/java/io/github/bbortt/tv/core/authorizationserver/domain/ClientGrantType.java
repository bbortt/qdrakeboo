package io.github.bbortt.tv.core.authorizationserver.domain;

public class ClientGrantType extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "client_grant_type";

  public static final String CLIENT_GRANT_TYPE_CREATED_COLUMN_NAME = "client_grant_type_created";
  public static final String CLIENT_GRANT_TYPE_LAST_UPDATED_COLUMN_NAME =
      "client_grant_type_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

  private long id;
  private String name;

  public ClientGrantType() {

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
