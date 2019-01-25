package io.github.bbortt.tv.core.authorizationserver.config;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;

public class WebSecurityConfigUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AuthenticationManager authenticationManagerMock;

  WebSecurityConfig fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new WebSecurityConfig(authenticationManagerMock);
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
    assertThat(new WebSecurityConfig(authenticationManagerMock))
        .hasFieldOrPropertyWithValue("authenticationManager", authenticationManagerMock);
  }

  @Test
  public void authenticationManagerReturnsBean() {
    assertThat(fixture.authenticationManager()).isEqualTo(authenticationManagerMock);
  }
}
