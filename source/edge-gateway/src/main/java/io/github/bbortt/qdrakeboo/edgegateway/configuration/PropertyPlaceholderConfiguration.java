package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyPlaceholderConfiguration {

  @Configuration
  @ConfigurationProperties("auth0")
  public static class Auth0 {

    private String audience;

    public String getAudience() {
      return audience;
    }

    public void setAudience(String audience) {
      this.audience = audience;
    }
  }

  @Configuration
  @ConfigurationProperties("spring.security.oauth2.resourceserver.jwt")
  public static class Jwt {

    private String issuerUri;

    public String getIssuerUri() {
      return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
      this.issuerUri = issuerUri;
    }
  }
}
