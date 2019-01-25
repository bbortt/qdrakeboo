package io.github.bbortt.tv.core.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class AuthorizationServerConfigUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  ClientDetailsService clientDetailsServiceMock;

  @Mock
  AuthenticationManager authenticationManagerMock;

  @Mock
  TokenStore tokenStoreMock;

  AuthorizationServerConfig fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AuthorizationServerConfig(clientDetailsServiceMock, authenticationManagerMock,
        tokenStoreMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AuthorizationServerConfig.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void extendsAuthorizationServerConfigurerAdapter() {
    assertThat(fixture).isInstanceOf(AuthorizationServerConfigurerAdapter.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AuthorizationServerConfig(clientDetailsServiceMock, authenticationManagerMock,
        tokenStoreMock))
            .hasFieldOrPropertyWithValue("clientDetailsService", clientDetailsServiceMock)
            .hasFieldOrPropertyWithValue("authenticationManager", authenticationManagerMock)
            .hasFieldOrPropertyWithValue("tokenStore", tokenStoreMock);
  }

  @Test
  public void configureAuthorizationServerSecurityConfigurer() throws Exception {
    AuthorizationServerSecurityConfigurer securitySpy =
        Mockito.spy(AuthorizationServerSecurityConfigurer.class);

    fixture.configure(securitySpy);

    verify(securitySpy).tokenKeyAccess(Mockito.eq("permitAll()"));
    verify(securitySpy).checkTokenAccess(Mockito.eq("isAuthenticated()"));
  }

  @Test
  public void configureClientDetailsServiceConfigurer() throws Exception {
    ClientDetailsServiceConfigurer clientsMock = Mockito.mock(ClientDetailsServiceConfigurer.class);

    fixture.configure(clientsMock);
    verify(clientsMock).withClientDetails(Mockito.eq(clientDetailsServiceMock));
  }

  @Test
  public void configureAuthorizationServerEndpointsConfigurer() throws Exception {
    AuthorizationServerEndpointsConfigurer endpointsSpy =
        Mockito.spy(AuthorizationServerEndpointsConfigurer.class);

    fixture.configure(endpointsSpy);
    verify(endpointsSpy).authenticationManager(Mockito.eq(authenticationManagerMock));
    verify(endpointsSpy).tokenStore(Mockito.eq(tokenStoreMock));
  }
}
