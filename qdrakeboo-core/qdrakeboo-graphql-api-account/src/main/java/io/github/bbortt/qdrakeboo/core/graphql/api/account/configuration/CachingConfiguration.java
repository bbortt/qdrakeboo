package io.github.bbortt.qdrakeboo.core.graphql.api.account.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@ConditionalOnProperty(prefix = "graphql-api-account.caching", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CachingConfiguration {

}
