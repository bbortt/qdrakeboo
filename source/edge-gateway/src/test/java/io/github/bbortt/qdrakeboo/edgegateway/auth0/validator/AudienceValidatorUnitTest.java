package io.github.bbortt.qdrakeboo.edgegateway.auth0.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidatorUnitTest {

  static final String TEST_AUDIENCE = "test-audience";
  static final OAuth2Error O_AUTH_2_ERROR = new OAuth2Error("invalid_token",
      String.format("The required audience '%s' is missing", TEST_AUDIENCE), null);

  AudienceValidator fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AudienceValidator(TEST_AUDIENCE);
  }

  @Test
  public void constructorInstantiatesClass() {
    assertThat(fixture).hasFieldOrPropertyWithValue("audience", TEST_AUDIENCE)
        .extracting("oAuth2Error").first()
        .isEqualToComparingFieldByField(O_AUTH_2_ERROR);
  }

  @Test
  public void validateReturnsSuccessOnMatch() {
    Jwt jwt = Mockito.mock(Jwt.class);
    doReturn(Collections.singletonList(TEST_AUDIENCE)).when(jwt).getAudience();

    assertThat(fixture.validate(jwt)).isEqualTo(OAuth2TokenValidatorResult.success());
  }

  @Test
  public void validateReturnsSuccessOnMatchInOneOf() {
    Jwt jwt = Mockito.mock(Jwt.class);
    doReturn(Arrays.asList("invalid-audience", TEST_AUDIENCE)).when(jwt).getAudience();

    assertThat(fixture.validate(jwt)).isEqualTo(OAuth2TokenValidatorResult.success());
  }

  @Test
  public void validateReturnsFailureOnNoneMatch() {
    Jwt jwt = Mockito.mock(Jwt.class);
    doReturn(Collections.singletonList("invalid-audience")).when(jwt).getAudience();

    assertThat(fixture.validate(jwt)).isEqualToComparingFieldByFieldRecursively(
        OAuth2TokenValidatorResult.failure(O_AUTH_2_ERROR));
  }
}
