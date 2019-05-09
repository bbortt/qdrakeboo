package io.github.bbortt.qdrakeboo.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
public class AuthenticationManagerConfig {

  private final ObjectPostProcessor<Object> objectPostProcessor;
  private final PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider;
  private final UserDetailsService userDetailsService;

  public AuthenticationManagerConfig(ObjectPostProcessor<Object> objectPostProcessor,
      PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider,
      UserDetailsService userDetailsService) {
    this.objectPostProcessor = objectPostProcessor;
    this.preAuthenticatedAuthenticationProvider = preAuthenticatedAuthenticationProvider;
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);

    builder.authenticationProvider(preAuthenticatedAuthenticationProvider)
        .userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    return builder.build();
  }
}
