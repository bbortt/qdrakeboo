package io.github.bbortt.tv.core.authorizationserver.domain;

public class ClientAuthority extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "client_authority";

  public static final String CLIENT_AUTHORITY_CREATED_COLUMN_NAME = "client_authority_created";
  public static final String CLIENT_AUTHORITY_LAST_UPDATED_COLUMN_NAME =
      "client_authority_last_updated";

  public static final String NAME_COLUMN_NAME = "name";

  private long id;
  private String name;

  public ClientAuthority() {

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
