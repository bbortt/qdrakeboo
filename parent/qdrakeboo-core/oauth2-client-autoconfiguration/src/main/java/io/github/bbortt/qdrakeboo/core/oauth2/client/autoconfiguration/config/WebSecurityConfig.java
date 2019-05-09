package io.github.bbortt.qdrakeboo.core.oauth2.client.autoconfiguration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .csrf().disable()

        .authorizeRequests().anyRequest().permitAll();
    // @formatter:on
  }
}
