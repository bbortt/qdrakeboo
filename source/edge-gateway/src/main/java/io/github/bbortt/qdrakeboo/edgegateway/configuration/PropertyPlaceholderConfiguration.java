package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class PropertyPlaceholderConfiguration {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(PropertyPlaceholderConfiguration.class);

  @Bean
  @ConditionalOnProperty(name = "${AUTH0_PROPERTIES_PLACEHOLDER}")
  public PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
      @Value("${AUTH0_PROPERTIES_PLACEHOLDER}") String auth0PropertiesPlaceholder)
      throws IOException {
    LOGGER.info("Initializing property source from '{}'", auth0PropertiesPlaceholder);

    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
    propertySourcesPlaceholderConfigurer.setLocations(
        new PathMatchingResourcePatternResolver().getResources(auth0PropertiesPlaceholder));
    return propertySourcesPlaceholderConfigurer;
  }

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
