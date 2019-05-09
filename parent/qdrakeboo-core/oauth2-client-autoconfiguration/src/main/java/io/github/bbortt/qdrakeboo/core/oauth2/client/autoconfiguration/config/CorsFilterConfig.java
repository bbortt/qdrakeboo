package io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config;

import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.filter.CorsFilter;

@Configuration
public class CorsFilterConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public Filter corsFilter() {
    return new CorsFilter();
  }
}
