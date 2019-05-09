package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.AbstractAuditingEntity;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

@Entity
@IdClass(ClientHasGrantTypeId.class)
@Table(name = "client_has_grant_types")
public class ClientHasGrantType extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "client_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.ALL})
  private Client client;

  @Id
  @ManyToOne
  @JoinColumn(name = "grant_type_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  private GrantType grantType;

  @SuppressWarnings("unused")
  private ClientHasGrantType() {
    // Used by Hibernate
  }

  public ClientHasGrantType(Client client, GrantType grantType) {
    this.client = client;
    this.grantType = grantType;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public GrantType getGrantType() {
    return grantType;
  }

  public void setGrantType(GrantType grantType) {
    this.grantType = grantType;
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
    ClientHasGrantType clientHasGrantType = (ClientHasGrantType) object;
    return new EqualsBuilder().appendSuper(super.equals(object))
        .append(client, clientHasGrantType.client).append(grantType, clientHasGrantType.grantType)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(client).append(grantType)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper(super.toString()).append(client).append(grantType)
        .build();
  }
}
