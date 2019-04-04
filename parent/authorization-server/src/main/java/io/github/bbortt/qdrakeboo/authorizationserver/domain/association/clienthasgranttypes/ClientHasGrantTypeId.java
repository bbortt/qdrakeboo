package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

public class ClientHasGrantTypeId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Client client;
  private GrantType grantType;

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
    ClientHasGrantTypeId clientHasGrantTypeId = (ClientHasGrantTypeId) object;
    return new EqualsBuilder().append(client, clientHasGrantTypeId.client)
        .append(grantType, clientHasGrantTypeId.grantType).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(client).append(grantType).toHashCode();
  }
}
