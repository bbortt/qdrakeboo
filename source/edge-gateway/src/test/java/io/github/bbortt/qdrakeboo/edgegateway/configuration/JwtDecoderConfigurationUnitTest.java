package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

public class JwtDecoderConfigurationUnitTest {

  static final String TEST_AUDIENCE = "test-audience";
  static final String TEST_ISSUER_URI = "http://test.issuer.uri";

  JwtDecoderConfiguration fixture;

  @Before
  public void beforeTest() {
    fixture = new JwtDecoderConfiguration(TEST_AUDIENCE, TEST_ISSUER_URI);
  }

  @Test
  public void isAnnotated() {
    assertThat(fixture.getClass()).hasAnnotation(Configuration.class);
  }

  @Test
  public void constructorInstantiatesClass() {
    assertThat(fixture).hasFieldOrPropertyWithValue("audience", TEST_AUDIENCE)
        .hasFieldOrPropertyWithValue("issuerUri", TEST_ISSUER_URI);
  }
}
