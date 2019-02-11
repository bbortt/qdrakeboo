package io.github.bbortt.tv.core.userapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
public class RestTemplateConfig {

  @Bean
  public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
    return new OAuth2RestTemplate(remote(), oauth2ClientContext);
  }

  @Bean
  @ConfigurationProperties("security.oauth2.client")
  public AuthorizationCodeResourceDetails remote() {
    return new AuthorizationCodeResourceDetails();
  }
}
