package io.github.bbortt.qdrakeboo.core.graphql.api.account.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({GraphQLApiAccountProperties.class})
public class GraphQLApiAccountConfiguration {

}
