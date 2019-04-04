package io.github.bbortt.qdrakeboo.authorizationserver.config.clientdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities.ClientHasAuthority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes.ClientHasGrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes.ClientHasScope;

public class ClientDetailsImpl implements ClientDetails {

  private static final long serialVersionUID = 1L;

  private Client client;

  public ClientDetailsImpl(Client client) {
    this.client = client;
  }

  @Override
  public String getClientId() {
    return client.getClientId();
  }

  @Override
  public Set<String> getResourceIds() {
    return Set.of(client.getResourceIds());
  }

  @Override
  public boolean isSecretRequired() {
    return client.isSecretRequired();
  }

  @Override
  public String getClientSecret() {
    return client.getSecret();
  }

  @Override
  public boolean isScoped() {
    return client.getScopes() != null && !client.getScopes().isEmpty();
  }

  @Override
  public Set<String> getScope() {
    return client.getScopes().stream().map(ClientHasScope::getScope).map(Scope::getName)
        .collect(Collectors.toSet());
  }

  @Override
  public Set<String> getAuthorizedGrantTypes() {
    return client.getGrantTypes().stream().map(ClientHasGrantType::getGrantType)
        .map(GrantType::getName).collect(Collectors.toSet());
  }

  @Override
  public Set<String> getRegisteredRedirectUri() {
    return new HashSet<String>(Collections.singletonList(client.getRedirectUris()));
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return client.getAuthorities().stream().map(ClientHasAuthority::getAuthority)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public Integer getAccessTokenValiditySeconds() {
    return client.getAccessTokenValiditySeconds();
  }

  @Override
  public Integer getRefreshTokenValiditySeconds() {
    return client.getRefreshTokenValiditySeconds();
  }

  @Override
  public boolean isAutoApprove(String scope) {
    return client.isAutoApprove();
  }

  @Override
  public Map<String, Object> getAdditionalInformation() {
    return new HashMap<>();
  }
}
