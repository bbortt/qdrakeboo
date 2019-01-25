package io.github.bbortt.tv.core.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final Environment environment;
  private final AuthenticationManager authenticationManager;

  public WebSecurityConfig(Environment environment, AuthenticationManager authenticationManager) {
    this.environment = environment;
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected AuthenticationManager authenticationManager() {
    return authenticationManager;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    if (environment.acceptsProfiles(Profiles.of("dev"))) {
      web.debug(true);
    }
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
