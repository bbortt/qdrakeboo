package io.github.bbortt.tv.dev.eureka.autoconfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class EurekaClientAutoconfiguration {

  public static final String PROFILE_DEV = "dev";

  @Configuration
  @Profile("!" + PROFILE_DEV)
  @PropertySource({"classpath:config/eureka-autoconfiguration.properties"})
  public static class EurekaProdConfiguration {

  }

  @Configuration
  @Profile(PROFILE_DEV)
  @PropertySource({"classpath:config/eureka-autoconfiguration-dev.properties"})
  public static class EurekaDevConfiguration {

  }
}
