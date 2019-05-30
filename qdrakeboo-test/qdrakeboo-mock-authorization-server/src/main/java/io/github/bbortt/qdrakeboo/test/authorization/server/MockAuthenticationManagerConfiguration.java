package io.github.bbortt.qdrakeboo.test.authorization.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class MockAuthenticationManagerConfiguration {

  private UserDetailsService userDetailsService;
  private ObjectPostProcessor<Object> objectPostProcessor;

  public MockAuthenticationManagerConfiguration(UserDetailsService userDetailsService,
      ObjectPostProcessor<Object> objectPostProcessor) {
    this.userDetailsService = userDetailsService;
    this.objectPostProcessor = objectPostProcessor;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(
        objectPostProcessor);

    authenticationManagerBuilder.userDetailsService(userDetailsService);

    return authenticationManagerBuilder.build();
  }
}
