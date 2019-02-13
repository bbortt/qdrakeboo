package io.github.bbortt.tv.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import io.github.bbortt.tv.authorizationserver.config.AuthenticationManagerConfig;

public class AuthenticationManagerConfigUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProviderMock;

  @Mock
  UserDetailsService userDetailsServiceMock;

  ObjectPostProcessor<Object> objectPostProcessor;

  AuthenticationManagerConfig fixture;

  @Before
  public void beforeTestSetup() {
    objectPostProcessor = new ObjectPostProcessor<Object>() {
      @Override
      public <O> O postProcess(O object) {
        return object;
      }
    };

    fixture = new AuthenticationManagerConfig(objectPostProcessor,
        preAuthenticatedAuthenticationProviderMock, userDetailsServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AuthenticationManagerConfig.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AuthenticationManagerConfig(objectPostProcessor,
        preAuthenticatedAuthenticationProviderMock, userDetailsServiceMock))
            .hasFieldOrPropertyWithValue("objectPostProcessor", objectPostProcessor)
            .hasFieldOrPropertyWithValue("preAuthenticatedAuthenticationProvider",
                preAuthenticatedAuthenticationProviderMock)
            .hasFieldOrPropertyWithValue("userDetailsService", userDetailsServiceMock);
  }

  @Test
  public void authenticationManagerIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method authenticationManager =
        AuthenticationManagerConfig.class.getDeclaredMethod("authenticationManager");

    assertThat(authenticationManager.getDeclaredAnnotation(Bean.class)).isNotNull();
  }

  @Test
  public void authenticationManagerIsConfigured() throws Exception {
    assertThat(((ProviderManager) fixture.authenticationManager()).getProviders()).isNotNull()
        .hasSize(2).contains(preAuthenticatedAuthenticationProviderMock);

    Optional<AuthenticationProvider> daoProvider =
        ((ProviderManager) fixture.authenticationManager()).getProviders().stream()
            .filter(
                provider -> DaoAuthenticationProvider.class.isAssignableFrom(provider.getClass()))
            .findFirst();

    assertThat(daoProvider.isPresent()).isTrue();
    assertThat((DaoAuthenticationProvider) daoProvider.get())
        .hasFieldOrPropertyWithValue("userDetailsService", userDetailsServiceMock)
        .extracting("passwordEncoder").first().isInstanceOf(BCryptPasswordEncoder.class);
  }
}
