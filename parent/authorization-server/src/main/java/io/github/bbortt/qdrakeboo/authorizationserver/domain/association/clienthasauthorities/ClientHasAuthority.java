package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities;

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
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;

@Entity
@IdClass(ClientHasAuthorityId.class)
@Table(name = "client_has_authorities")
public class ClientHasAuthority extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "client_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.ALL})
  private Client client;

  @Id
  @ManyToOne
  @JoinColumn(name = "authority_uuid")
  @LazyCollection(LazyCollectionOption.FALSE)
  private Authority authority;

  @SuppressWarnings("unused")
  private ClientHasAuthority() {
    // Used by Hibernate
  }

  public ClientHasAuthority(Client client, Authority authority) {
    this.client = client;
    this.authority = authority;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
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
    ClientHasAuthority clientHasAuthority = (ClientHasAuthority) object;
    return new EqualsBuilder().appendSuper(super.equals(object))
        .append(client, clientHasAuthority.client).append(authority, clientHasAuthority.authority)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(client).append(authority)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper(super.toString()).append(client).append(authority)
        .build();
  }
}
