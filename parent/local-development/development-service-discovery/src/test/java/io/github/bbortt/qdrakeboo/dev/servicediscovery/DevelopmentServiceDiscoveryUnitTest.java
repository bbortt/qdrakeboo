package io.github.bbortt.qdrakeboo.dev.servicediscovery;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

public class DevelopmentServiceDiscoveryUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(DevelopmentServiceDiscovery.class).hasAnnotations(SpringBootApplication.class,
        EnableEurekaServer.class);
  }

  @Test
  public void hasPublicConstructr() {
    assertThat(new DevelopmentServiceDiscovery()).isNotNull();
  }
}
