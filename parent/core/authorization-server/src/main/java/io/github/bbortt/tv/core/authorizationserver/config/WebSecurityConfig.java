package io.github.bbortt.tv.core.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationManager authenticationManager;

  public WebSecurityConfig(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected AuthenticationManager authenticationManager() {
    return authenticationManager;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.debug(true);
  }

  @Override
  // @formatter:off
  protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers("/user", "/me").permitAll()
              .anyRequest().authenticated()

            .and().formLogin();
  }
  // @formatter:on
}
