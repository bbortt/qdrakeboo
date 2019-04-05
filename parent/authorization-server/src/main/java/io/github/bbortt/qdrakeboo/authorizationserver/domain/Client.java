package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities.ClientHasAuthority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes.ClientHasGrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes.ClientHasScope;

@Table
@Entity
public class Client extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Type(type = "pg-uuid")
  @GeneratedValue(generator = "client-uuid")
  @GenericGenerator(name = "client-uuid",
      strategy = "io.github.bbortt.qdrakeboo.authorizationserver.domain.postgresql.PostgreSQLUUIDGenerationStrategy")
  @Column(unique = true, nullable = false, columnDefinition = "uuid")
  private UUID uuid;

  @NotEmpty
  @Size(max = 36)
  @Column(nullable = false, unique = true)
  private String clientId;

  @Size(min = 60, max = 60)
  @Column(nullable = false, columnDefinition = "bpchar(60)")
  private String secret;

  @Size(max = 256)
  @Column(nullable = false)
  private String resourceIds;

  @Column(nullable = false)
  private boolean secretRequired = true;

  @Column(nullable = false)
  private boolean autoApprove = false;

  @Column(name = "access_token_validity", nullable = false)
  private int accessTokenValiditySeconds;

  @Column(name = "refresh_token_validity", nullable = false)
  private int refreshTokenValiditySeconds;

  @Size(max = 256)
  private String redirectUris;

  @JsonManagedReference("client_has_authorities")
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
  private Set<ClientHasAuthority> authorities = new HashSet<>();

  @JsonManagedReference("client_has_grant_types")
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
  private Set<ClientHasGrantType> grantTypes = new HashSet<>();

  @JsonManagedReference("client_has_scopes")
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
  private Set<ClientHasScope> scopes = new HashSet<>();

  public Client() {

  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(String resourceIds) {
    this.resourceIds = resourceIds;
  }

  public boolean isSecretRequired() {
    return secretRequired;
  }

  public void setSecretRequired(boolean isSecretRequired) {
    this.secretRequired = isSecretRequired;
  }

  public boolean isAutoApprove() {
    return autoApprove;
  }

  public void setAutoApprove(boolean isAutoApprove) {
    this.autoApprove = isAutoApprove;
  }

  public int getAccessTokenValiditySeconds() {
    return accessTokenValiditySeconds;
  }

  public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
    this.accessTokenValiditySeconds = accessTokenValiditySeconds;
  }

  public int getRefreshTokenValiditySeconds() {
    return refreshTokenValiditySeconds;
  }

  public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
    this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
  }

  public String getRedirectUris() {
    return redirectUris;
  }

  public void setRedirectUris(String redirectUrl) {
    this.redirectUris = redirectUrl;
  }

  public Set<ClientHasAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<ClientHasAuthority> authorities) {
    this.authorities = authorities;
  }

  public Set<ClientHasGrantType> getGrantTypes() {
    return grantTypes;
  }

  public void setGrantTypes(Set<ClientHasGrantType> grantTypes) {
    this.grantTypes = grantTypes;
  }

  public Set<ClientHasScope> getScopes() {
    return scopes;
  }

  public void setScopes(Set<ClientHasScope> scopes) {
    this.scopes = scopes;
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
    Client client = (Client) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(uuid, client.uuid)
        .append(clientId, client.clientId).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(uuid).append(clientId)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(uuid).append(clientId).append(secretRequired)
        .append(autoApprove).append(accessTokenValiditySeconds).append(refreshTokenValiditySeconds)
        .append(redirectUris).append(authorities).append(grantTypes).append(scopes).build();
  }
}
