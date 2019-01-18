package io.github.bbortt.tv.core.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
public class AuthenticationManagerConfig {

  private final ObjectPostProcessor<Object> objectPostProcessor;
  private final PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider;
  private final UserDetailsService userDetailsService;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public AuthenticationManagerConfig(ObjectPostProcessor<Object> objectPostProcessor,
      PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider,
      UserDetailsService userDetailsService) {
    this.objectPostProcessor = objectPostProcessor;
    this.preAuthenticatedAuthenticationProvider = preAuthenticatedAuthenticationProvider;
    this.userDetailsService = userDetailsService;

    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    final AuthenticationManagerBuilder builder =
        new AuthenticationManagerBuilder(objectPostProcessor);

    builder.authenticationProvider(preAuthenticatedAuthenticationProvider)
        .userDetailsService(userDetailsService)
        // TODO: .passwordEncoder(bCryptPasswordEncoder);
        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

    return builder.build();
  }
}
