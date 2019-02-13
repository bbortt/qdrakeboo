package io.github.bbortt.tv.core.userapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  // @Value("${security.oauth2.client.clientId}")
  // private String clientId;
  //
  // @Override
  // public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
  // resources.resourceId(clientId);
  // }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated();
  }
}
