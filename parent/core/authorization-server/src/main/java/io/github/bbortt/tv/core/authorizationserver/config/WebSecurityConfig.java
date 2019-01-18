package io.github.bbortt.tv.core.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private AuthenticationManager authenticationManager;

  public WebSecurityConfig(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected AuthenticationManager authenticationManager() {
    return authenticationManager;
  }
}
