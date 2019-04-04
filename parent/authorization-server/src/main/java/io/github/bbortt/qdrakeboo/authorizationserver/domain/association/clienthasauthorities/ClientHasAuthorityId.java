package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;

public class ClientHasAuthorityId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Client client;
  private Authority authority;

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
    ClientHasAuthorityId clientHasAuthorityId = (ClientHasAuthorityId) object;
    return new EqualsBuilder().append(client, clientHasAuthorityId.client)
        .append(authority, clientHasAuthorityId.authority).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(client).append(authority)
        .toHashCode();
  }
}
