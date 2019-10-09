package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityWebFilterChainConfiguration {

  @Bean
  public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
    // @formatter:off
    http
        .authorizeExchange()
        .pathMatchers("/graphql/**")
          .authenticated()
        .anyExchange()
          .permitAll()
        .and()
          .oauth2ResourceServer()
            .jwt();
    // @formatter:on

    return http.build();
  }
}
