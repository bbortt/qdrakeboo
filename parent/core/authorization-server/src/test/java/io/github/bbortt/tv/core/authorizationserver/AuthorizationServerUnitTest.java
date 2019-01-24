package io.github.bbortt.tv.core.authorizationserver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AuthorizationServerUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(AuthorizationServer.class).hasAnnotation(SpringBootApplication.class);
  }
}
