package io.github.bbortt.tv.authorizationserver.domain;

import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;
import io.github.bbortt.tv.authorizationserver.util.DublicateAwareHashSet;

public class Client extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "client";
  public static final String CACHE_NAME = "client";

  public static final String CLIENT_CREATED_RESULT_NAME = "client_created";
  public static final String CLIENT_LAST_UPDATED_RESULT_NAME = "client_last_updated";

  public static final String ID_RESULT_NAME = "id";
  public static final String CLIENT_ID_RESULT_NAME = "client_id";
  public static final String SECRET_RESULT_NAME = "secret";
  public static final String RESOURCE_IDS_NAME = "resource_ids";
  public static final String SECRET_REQUIRED_RESULT_NAME = "secret_required";
  public static final String AUTO_APPROVE_RESULT_NAME = "auto_approve";
  public static final String ACCESS_TOKEN_VALIDITY_SECONDS_RESULT_NAME = "access_token_validity";
  public static final String REFRESH_TOKEN_VALIDITY_SECONDS_RESULT_NAME = "refresh_token_validity";
  public static final String REDIRECT_URIS_RESULT_NAME = "redirect_uris";

  private long id;
  private String clientId;
  private String secret;
  private String resourceIds;
  private boolean isSecretRequired = true;
  private boolean isAutoApprove = false;
  private int accessTokenValiditySeconds;
  private int refreshTokenValiditySeconds;
  private String redirectUris;
  private Set<GrantType> grantTypes = new DublicateAwareHashSet<>();
  private Set<Authority> authorities = new DublicateAwareHashSet<>();
  private Set<Scope> scopes = new DublicateAwareHashSet<>();

  public Client() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    return isSecretRequired;
  }

  public void setSecretRequired(boolean isSecretRequired) {
    this.isSecretRequired = isSecretRequired;
  }

  public boolean isAutoApprove() {
    return isAutoApprove;
  }

  public void setAutoApprove(boolean isAutoApprove) {
    this.isAutoApprove = isAutoApprove;
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

  public Set<GrantType> getGrantTypes() {
    return grantTypes;
  }

  public void setGrantTypes(Set<GrantType> grantTypes) {
    this.grantTypes.clear();
    this.grantTypes.addAll(grantTypes);
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities.clear();
    this.authorities.addAll(authorities);
  }

  public Set<Scope> getScopes() {
    return scopes;
  }

  public void setScopes(Set<Scope> scopes) {
    this.scopes.clear();
    this.scopes.addAll(scopes);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(id).append(clientId).append(isSecretRequired)
        .append(isAutoApprove).append(accessTokenValiditySeconds)
        .append(refreshTokenValiditySeconds).append(redirectUris).append(grantTypes)
        .append(authorities).append(scopes).build();
  }
}
