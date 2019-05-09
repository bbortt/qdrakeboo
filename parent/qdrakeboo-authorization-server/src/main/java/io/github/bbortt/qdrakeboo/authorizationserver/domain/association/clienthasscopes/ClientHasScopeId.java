package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;

public class ClientHasScopeId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Client client;
  private Scope scope;

  @SuppressWarnings("unused")
  private ClientHasScopeId() {
    // Used by Hibernate
  }

  public ClientHasScopeId(Client client, Scope scope) {
    this.client = client;
    this.scope = scope;
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
    ClientHasScopeId clientHasScopeId = (ClientHasScopeId) object;
    return new EqualsBuilder().append(client, clientHasScopeId.client)
        .append(scope, clientHasScopeId.scope).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(client).append(scope).toHashCode();
  }
}
