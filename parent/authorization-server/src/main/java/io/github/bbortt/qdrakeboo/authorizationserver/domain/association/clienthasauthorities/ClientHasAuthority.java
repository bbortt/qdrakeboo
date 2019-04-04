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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.AbstractAuditingEntity;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;

@Entity
@IdClass(ClientHasAuthorityId.class)
@Table(name = "client_has_authorities")
public class ClientHasAuthority extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "client_id")
  @JsonBackReference("client_has_authorities")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.ALL})
  public Client client;

  @Id
  @JoinColumn(name = "authority_id")
  @JsonBackReference("authority_has_clients")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  public Authority authority;

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
}
