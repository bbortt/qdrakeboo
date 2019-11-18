package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import io.github.bbortt.qdrakeboo.edgegateway.auth0.validator.AudienceValidator;
import io.github.bbortt.qdrakeboo.edgegateway.configuration.PropertyPlaceholderConfiguration.Auth0;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfiguration {

  private Auth0 auth0;
  private PropertyPlaceholderConfiguration.Jwt jwt;

  public JwtDecoderConfiguration(Auth0 auth0, PropertyPlaceholderConfiguration.Jwt jwt) {
    this.auth0 = auth0;
    this.jwt = jwt;
  }

  @Bean
  JwtDecoder jwtDecoder() {
    NimbusJwtDecoder nimbusJwtDecoder = (NimbusJwtDecoder) JwtDecoders
        .fromOidcIssuerLocation(jwt.getIssuerUri());

    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(auth0.getAudience());
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators
        .createDefaultWithIssuer(jwt.getIssuerUri());
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer,
        audienceValidator);

    nimbusJwtDecoder.setJwtValidator(withAudience);

    return nimbusJwtDecoder;
  }
}
