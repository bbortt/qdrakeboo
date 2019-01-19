package io.github.bbortt.tv.core.authorizationserver.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@Profile("prod")
public class CachingConfig {

}
