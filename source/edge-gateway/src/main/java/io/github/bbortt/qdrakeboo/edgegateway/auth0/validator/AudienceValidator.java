package io.github.bbortt.qdrakeboo.edgegateway.auth0.validator;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  private final String audience;
  private final OAuth2Error oAuth2Error;

  public AudienceValidator(String audience) {
    this.audience = audience;
    this.oAuth2Error = new OAuth2Error("invalid_token",
        String.format("The required audience '%s' is missing", audience), null);
  }

  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    if (jwt.getAudience().contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    } else {
      return OAuth2TokenValidatorResult.failure(oAuth2Error);
    }
  }
}
