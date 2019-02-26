package io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class RemoteTokenServiceConfig {

  private final OAuth2ClientAutoconfigurationProperties oAuth2ClientAutoconfigurationProperties;

  public RemoteTokenServiceConfig(
      OAuth2ClientAutoconfigurationProperties oAuth2ClientAutoconfigurationProperties) {
    this.oAuth2ClientAutoconfigurationProperties = oAuth2ClientAutoconfigurationProperties;
  }

  @Bean
  public ResourceServerTokenServices resourceServerTokenServices() {
    RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
    remoteTokenServices.setClientId(oAuth2ClientAutoconfigurationProperties.getClientId());
    remoteTokenServices.setClientSecret(oAuth2ClientAutoconfigurationProperties.getClientSecret());
    remoteTokenServices
        .setCheckTokenEndpointUrl(oAuth2ClientAutoconfigurationProperties.getCheckTokenUri());
    return remoteTokenServices;
  }
}
