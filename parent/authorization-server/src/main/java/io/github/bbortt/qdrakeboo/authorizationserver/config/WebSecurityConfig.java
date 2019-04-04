package io.github.bbortt.qdrakeboo.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
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
      // web.debug(true);
    }
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        // TODO: Potentially unsecure!
        .csrf().disable()

        .authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers("/user", "/me").permitAll()
//            .antMatchers("/graphiql","/graphql").permitAll()
//            .anyRequest().authenticated()
            .anyRequest().permitAll()

        .and().formLogin();
    // @formatter:on
  }
}
