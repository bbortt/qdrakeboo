package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class RouteConfiguration {

  @Configuration
  @Profile("!dev")
  @PropertySource({"config/route.properties"})
  public static class RouteProdConfiguration {

  }

  @Configuration
  @Profile("dev")
  @PropertySource({"classpath:config/route-dev.properties"})
  public static class RouteDevConfiguration {

  }
}
