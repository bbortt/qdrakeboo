package io.github.bbortt.tv.lib.oauth2.client.autoconfiguration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config.GlobalMethodSecurityConfig;
import io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config.OAuth2ClientAutoconfigurationProperties;
import io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config.OAuth2ResourceServerConfig;
import io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config.RemoteTokenServiceConfig;

@Configuration
@Import({GlobalMethodSecurityConfig.class, OAuth2ResourceServerConfig.class,
    RemoteTokenServiceConfig.class})
@EnableConfigurationProperties({OAuth2ClientAutoconfigurationProperties.class})
public class OAuth2ClientAutoconfiguration {

}
