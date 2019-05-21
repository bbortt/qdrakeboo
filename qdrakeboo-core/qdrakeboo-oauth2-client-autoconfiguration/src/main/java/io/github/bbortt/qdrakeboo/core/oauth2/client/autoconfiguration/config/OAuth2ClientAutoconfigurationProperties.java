package io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.client")
public class OAuth2ClientAutoconfigurationProperties {

  private String clientId;
  private String clientSecret;
  private String resourceId;
  private String checkTokenUri;

  private Autoconfiguration autoconfiguration;
  private Cors cors;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public String getCheckTokenUri() {
    return checkTokenUri;
  }

  public void setCheckTokenUri(String checkTokenUri) {
    this.checkTokenUri = checkTokenUri;
  }

  public Autoconfiguration getAutoconfiguration() {
    return autoconfiguration;
  }

  public void setAutoconfiguration(Autoconfiguration autoconfiguration) {
    this.autoconfiguration = autoconfiguration;
  }

  public Cors getCors() {
    return cors;
  }

  public void setCors(Cors cors) {
    this.cors = cors;
  }

  public static class Autoconfiguration {

    private boolean enabled;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }
  }

  public static class Cors {

    private boolean enabled;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }
  }
}