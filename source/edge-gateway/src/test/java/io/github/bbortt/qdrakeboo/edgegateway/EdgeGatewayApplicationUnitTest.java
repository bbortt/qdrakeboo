package io.github.bbortt.qdrakeboo.edgegateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class EdgeGatewayApplicationUnitTest {

  @Test
  public void isSpringBootApplication() {
    assertThat(EdgeGatewayApplication.class.getAnnotation(SpringBootApplication.class))
        .isNotNull();
  }
}
