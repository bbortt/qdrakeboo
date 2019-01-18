package io.github.bbortt.tv.core.authorizationserver.domain;

import java.util.Date;

public abstract class AbstractAuditingEntity {

  public static final String CREATED_COLUMN_NAME = "created";
  public static final String LAST_UPDATED_COLUMN_NAME = "last_updated";

  private Date created;
  private Date lastUpdated;

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
