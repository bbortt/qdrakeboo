package io.github.bbortt.tv.core.authorizationserver.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;

public abstract class AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

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
    AbstractAuditingEntity abstractAuditingEntity = (AbstractAuditingEntity) object;
    return new EqualsBuilder().append(created, abstractAuditingEntity.created)
        .append(lastUpdated, abstractAuditingEntity.lastUpdated).isEquals();
  }
}
