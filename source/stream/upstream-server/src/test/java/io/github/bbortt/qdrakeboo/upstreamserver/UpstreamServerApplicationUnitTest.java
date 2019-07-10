package io.github.bbortt.qdrakeboo.upstreamserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class UpstreamServerApplicationUnitTest {

  @Test
  public void isSpringBootApplication() {
    assertThat(UpstreamServerApplication.class.getAnnotation(SpringBootApplication.class))
        .isNotNull();
  }
}
