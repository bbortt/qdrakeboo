package io.github.bbortt.tv.authorizationserver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.bbortt.tv.authorizationserver.AuthorizationServer;

public class AuthorizationServerUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(AuthorizationServer.class).hasAnnotation(SpringBootApplication.class);
  }

  @Test
  public void hasPublicConstructr() {
    assertThat(new AuthorizationServer()).isNotNull();
  }
}
