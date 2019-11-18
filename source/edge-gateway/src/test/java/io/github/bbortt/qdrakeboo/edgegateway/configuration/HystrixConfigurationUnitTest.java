package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

public class HystrixConfigurationUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(HystrixConfiguration.class)
        .hasAnnotations(Configuration.class, EnableHystrix.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new HystrixConfiguration())
        .isNotNull();
  }
}
