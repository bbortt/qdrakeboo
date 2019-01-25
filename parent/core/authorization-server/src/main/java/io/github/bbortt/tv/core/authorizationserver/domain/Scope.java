package io.github.bbortt.tv.core.authorizationserver.domain;

import java.util.Comparator;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Scope extends AbstractAuditingEntity {

  public static final Comparator<Scope> COMPARATOR = Comparator.comparingLong(Scope::getId);

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
  public String toString() {
    return new ToStringBuilder(this).append(name).build();
  }
}
