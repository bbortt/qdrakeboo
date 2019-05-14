package io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration;

import io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config.OAuth2ClientAutoconfigurationProperties;
import io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config.TestWebSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "oauth2.client.autoconfiguration", name = "enabled",
    havingValue = "false")
@Import({TestWebSecurityConfig.class})
@EnableConfigurationProperties({OAuth2ClientAutoconfigurationProperties.class})
public class TestWebSecurityAutoconfiguration {

}
