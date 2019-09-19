package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

public class SecurityWebFilterChainConfigurationUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(SecurityWebFilterChainConfiguration.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new SecurityWebFilterChainConfiguration()).isNotNull();
  }
}
