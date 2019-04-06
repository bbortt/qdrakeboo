package io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes;

import java.io.Serializable;
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
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;

@Entity
@IdClass(ClientHasScopeId.class)
@Table(name = "client_has_scopes")
public class ClientHasScope implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "client_uuid")
  @JsonBackReference("client_has_scopes")
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.ALL})
  private Client client;

  @Id
  @ManyToOne
  @JoinColumn(name = "scope_uuid")
  @JsonBackReference("scope_has_clients")
  @LazyCollection(LazyCollectionOption.FALSE)
  private Scope scope;

  @SuppressWarnings("unused")
  private ClientHasScope() {
    // Used by Hibernate
  }

  public ClientHasScope(Client client, Scope scope) {
    this.client = client;
    this.scope = scope;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
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
    ClientHasScope clientHasScope = (ClientHasScope) object;
    return new EqualsBuilder().appendSuper(super.equals(object))
        .append(client, clientHasScope.client).append(scope, clientHasScope.scope).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(client).append(scope)
        .toHashCode();
  }
}
