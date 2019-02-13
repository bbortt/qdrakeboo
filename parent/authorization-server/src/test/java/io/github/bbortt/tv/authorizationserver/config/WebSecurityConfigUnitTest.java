package io.github.bbortt.tv.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import io.github.bbortt.tv.authorizationserver.config.WebSecurityConfig;

public class WebSecurityConfigUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();


  @Mock
  AuthenticationManager authenticationManagerMock;

  MockEnvironment mockEnvironment;

  WebSecurityConfig fixture;

  @Before
  public void beforeTestSetup() {
    mockEnvironment = new MockEnvironment();

    fixture = new WebSecurityConfig(mockEnvironment, authenticationManagerMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(WebSecurityConfig.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void extendsWebSecurityConfigurerAdapter() {
    assertThat(fixture).isInstanceOf(WebSecurityConfigurerAdapter.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new WebSecurityConfig(mockEnvironment, authenticationManagerMock))
        .hasFieldOrPropertyWithValue("environment", mockEnvironment)
        .hasFieldOrPropertyWithValue("authenticationManager", authenticationManagerMock);
  }

  @Test
  public void authenticationManagerReturnsBean() {
    assertThat(fixture.authenticationManager()).isEqualTo(authenticationManagerMock);
  }

  @Test
  public void configureWebSecurityOnDevProfileOnly() throws Exception {
    WebSecurity webSecurityMock = Mockito.mock(WebSecurity.class);

    mockEnvironment.setActiveProfiles("dev");

    fixture.configure(webSecurityMock);

    verify(webSecurityMock).debug(Mockito.eq(true));
  }

  @Test

  public void doNotConfigureWebSecurityOnProdProfile() throws Exception {
    WebSecurity webSecurityMock = Mockito.mock(WebSecurity.class);

    mockEnvironment.setActiveProfiles("prod");

    fixture.configure(webSecurityMock);

    verify(webSecurityMock, never()).debug(Mockito.eq(true));
  }
}
