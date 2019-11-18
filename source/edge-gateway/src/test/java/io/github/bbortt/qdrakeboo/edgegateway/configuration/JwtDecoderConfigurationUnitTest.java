package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bbortt.qdrakeboo.edgegateway.configuration.PropertyPlaceholderConfiguration.Auth0;
import io.github.bbortt.qdrakeboo.edgegateway.configuration.PropertyPlaceholderConfiguration.Jwt;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

public class JwtDecoderConfigurationUnitTest {

  final Auth0 auth0 = new Auth0();
  final Jwt jwt = new Jwt();

  JwtDecoderConfiguration fixture;

  @Before
  public void beforeTest() {

    fixture = new JwtDecoderConfiguration(auth0, jwt);
  }

  @Test
  public void isAnnotated() {
    assertThat(fixture.getClass())
        .hasAnnotation(Configuration.class);
  }

  @Test
  public void constructorInstantiatesClass() {
    assertThat(fixture)
        .hasFieldOrPropertyWithValue("auth0", auth0)
        .hasFieldOrPropertyWithValue("jwt", jwt);
  }
}
