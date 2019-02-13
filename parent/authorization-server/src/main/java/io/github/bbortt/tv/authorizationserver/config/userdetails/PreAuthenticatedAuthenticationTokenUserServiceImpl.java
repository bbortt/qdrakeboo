package io.github.bbortt.tv.authorizationserver.config.userdetails;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class PreAuthenticatedAuthenticationTokenUserServiceImpl
    implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

  private final TokenStore tokenStore;
  private final UserDetailsService userDetailsService;

  public PreAuthenticatedAuthenticationTokenUserServiceImpl(TokenStore tokenStore,
      UserDetailsService userDetailsService) {
    this.tokenStore = tokenStore;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
    return userDetailsService
        .loadUserByUsername(tokenStore.readAuthentication((String) token.getPrincipal()).getName());
  }
}
