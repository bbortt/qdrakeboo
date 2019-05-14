package io.github.bbortt.qdrakeboo.model.account;

import io.github.bbortt.qdrakeboo.model.autoconfiguration.domain.AbstractAuditingEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

@Table
@Entity
public class Role extends AbstractAuditingEntity implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  public static final String CACHE_NAME = "roles-cache";

  @Id
  @Type(type = "pg-uuid")
  @GeneratedValue(generator = "role-uuid")
  @GenericGenerator(name = "role-uuid",
      strategy = "io.github.bbortt.qdrakeboo.model.autoconfiguration.domain.PostgreSQLUUIDGenerationStrategy")
  @Column(nullable = false, unique = true, columnDefinition = "uuid")
  private UUID uuid;

  @NotEmpty
  @Size(max = 16)
  @Column(nullable = false, unique = true)
  private String name;

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return getName();
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
    Role role = (Role) object;
    return new EqualsBuilder()
        .appendSuper(super.equals(object))
        .append(uuid, role.uuid)
        .append(name, role.name)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .appendSuper(super.hashCode())
        .append(uuid).append(name)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .appendSuper(super.toString())
        .append("uuid", uuid)
        .append("name", name)
        .build();
  }
}
