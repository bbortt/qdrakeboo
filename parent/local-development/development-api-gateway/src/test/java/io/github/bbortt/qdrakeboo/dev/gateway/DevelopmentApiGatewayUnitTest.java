package io.github.bbortt.qdrakeboo.dev.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

public class DevelopmentApiGatewayUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(DevelopmentApiGateway.class).hasAnnotations(SpringBootApplication.class,
        EnableZuulProxy.class, EnableDiscoveryClient.class);
  }

  @Test
  public void hasPublicConstructr() {
    assertThat(new DevelopmentApiGateway()).isNotNull();
  }
}
