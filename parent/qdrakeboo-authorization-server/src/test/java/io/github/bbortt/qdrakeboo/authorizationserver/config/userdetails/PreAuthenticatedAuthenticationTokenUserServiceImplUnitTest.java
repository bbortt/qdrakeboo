package io.github.bbortt.qdrakeboo.authorizationserver.config.userdetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

public class PreAuthenticatedAuthenticationTokenUserServiceImplUnitTest {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  TokenStore tokenStoreMock;

  @Mock
  UserDetailsService userDetailsServiceMock;

  PreAuthenticatedAuthenticationTokenUserServiceImpl fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new PreAuthenticatedAuthenticationTokenUserServiceImpl(tokenStoreMock,
        userDetailsServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(PreAuthenticatedAuthenticationTokenUserServiceImpl.class)
        .hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(AuthenticationUserDetailsService.class)
        .isAssignableFrom(PreAuthenticatedAuthenticationTokenUserServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new PreAuthenticatedAuthenticationTokenUserServiceImpl(tokenStoreMock,
        userDetailsServiceMock)).isNotNull()
            .hasFieldOrPropertyWithValue("tokenStore", tokenStoreMock)
            .hasFieldOrPropertyWithValue("userDetailsService", userDetailsServiceMock);
  }

  @Test
  public void loadUserDetailsReadsPrincipalFromToken() {
    String username = "this-is-a-username";
    String principal = "this-is-a-principal";

    PreAuthenticatedAuthenticationToken tokenMock =
        Mockito.mock(PreAuthenticatedAuthenticationToken.class);
    doReturn(principal).when(tokenMock).getPrincipal();

    OAuth2Authentication authenticationMock = Mockito.mock(OAuth2Authentication.class);
    doReturn(username).when(authenticationMock).getName();
    doReturn(authenticationMock).when(tokenStoreMock).readAuthentication(Mockito.eq(principal));

    fixture.loadUserDetails(tokenMock);

    verify(userDetailsServiceMock).loadUserByUsername(Mockito.eq(username));
  }
}
