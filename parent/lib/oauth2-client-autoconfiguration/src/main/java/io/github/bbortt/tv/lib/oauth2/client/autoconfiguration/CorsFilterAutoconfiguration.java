package io.github.bbortt.tv.lib.oauth2.client.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.github.bbortt.tv.lib.oauth2.client.autoconfiguration.config.CorsFilterConfig;

@Configuration
@Import({CorsFilterConfig.class})
@ConditionalOnProperty(prefix = "oauth2.client.cors", name = "enabled", havingValue = "true",
    matchIfMissing = true)
public class CorsFilterAutoconfiguration {

}
