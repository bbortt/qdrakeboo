package io.github.bbortt.qdrakeboo.authorizationserver.config.userdetails;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class PreAuthenticatedAuthenticationProviderImpl
    extends PreAuthenticatedAuthenticationProvider {

  public PreAuthenticatedAuthenticationProviderImpl(
      AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedAuthenticationTokenUserServiceImpl) {
    super();

    super.setPreAuthenticatedUserDetailsService(preAuthenticatedAuthenticationTokenUserServiceImpl);
  }
}
