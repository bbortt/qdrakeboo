package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class RouteConfigurationUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(RouteConfiguration.class).hasAnnotation(Configuration.class);
    assertThat(RouteConfiguration.class.getAnnotation(PropertySource.class).value())
        .isEqualTo(new String[]{"${routes.path}"});
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new RouteConfiguration()).isNotNull();
  }
}
