package io.github.bbortt.tv.core.authorizationserver.domain;

import java.util.HashSet;
import java.util.Set;

public class Client extends AbstractAuditingEntity {

  public static final String TABLE_NAME = "client";
  public static final String CACHE_NAME = "client";

  public static final String CLIENT_CREATED_COLUMN_NAME = "client_created";
  public static final String CLIENT_LAST_UPDATED_COLUMN_NAME = "client_last_updated";

  public static final String ID_COLUMN_NAME = "id";
  public static final String CLIENT_ID_COLUMN_NAME = "client_id";
  public static final String SECRET_COLUMN_NAME = "secret";
  public static final String IS_SECRET_REQUIRED_COLUMN_NAME = "is_secret_required";
  public static final String IS_AUTO_APPROVE_COLUMN_NAME = "is_auto_approve";
  public static final String ACCESS_TOKEN_VALIDITY_SECONDS_COLUMN_NAME = "access_token_validity";
  public static final String REFRESH_TOKEN_VALIDITY_SECONDS_COLUMN_NAME = "refresh_token_validity";
  public static final String REDIRECT_URIS_COLUMN_NAME = "redirect_uris";

  private long id;
  private String clientId;
  private String secret;
  private boolean isSecretRequired = true;
  private boolean isAutoApprove = false;
  private int accessTokenValiditySeconds;
  private int refreshTokenValiditySeconds;
  private String redirectUris;
  private Set<GrantType> grantTypes = new HashSet<>();
  private Set<Authority> authorities = new HashSet<>();
  private Set<Scope> scopes = new HashSet<>();

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
    this.grantTypes = grantTypes;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  public Set<Scope> getScopes() {
    return scopes;
  }

  public void setScopes(Set<Scope> scopes) {
    this.scopes = scopes;
  }
}
