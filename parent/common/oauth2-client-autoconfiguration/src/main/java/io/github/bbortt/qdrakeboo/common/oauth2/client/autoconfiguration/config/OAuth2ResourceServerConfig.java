package io.github.bbortt.qdrakeboo.common.oauth2.client.autoconfiguration.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final OAuth2ClientAutoconfigurationProperties oAuth2ClientAutoconfigurationProperties;

  public OAuth2ResourceServerConfig(
      OAuth2ClientAutoconfigurationProperties oAuth2ClientAutoconfigurationProperties) {
    this.oAuth2ClientAutoconfigurationProperties = oAuth2ClientAutoconfigurationProperties;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(oAuth2ClientAutoconfigurationProperties.getResourceId()).stateless(true);
  }
}
