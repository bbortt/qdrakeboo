package io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration;

import io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config.CorsFilterConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CorsFilterConfig.class})
@ConditionalOnProperty(prefix = "oauth2.client.cors", name = "enabled", havingValue = "true",
    matchIfMissing = true)
public class CorsFilterAutoconfiguration {

}
