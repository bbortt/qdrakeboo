package io.github.bbortt.tv.core.userapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// @Configuration
public class OAuth2ClientConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  OAuth2ClientContext oauth2ClientContext;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**")

        .authorizeRequests().anyRequest().authenticated()

        .and().addFilterBefore(authorizationServerSSOFilter(), BasicAuthenticationFilter.class);
  }

  private OAuth2ClientAuthenticationProcessingFilter authorizationServerSSOFilter() {
    OAuth2ClientAuthenticationProcessingFilter authorizationServerFilter =
        new OAuth2ClientAuthenticationProcessingFilter("/login");
    OAuth2RestTemplate authorizationServerTemplate =
        new OAuth2RestTemplate(authorizationServer(), oauth2ClientContext);
    authorizationServerFilter.setRestTemplate(authorizationServerTemplate);
    UserInfoTokenServices tokenServices = new UserInfoTokenServices(
        authorizationServerResource().getUserInfoUri(), authorizationServer().getClientId());
    tokenServices.setRestTemplate(authorizationServerTemplate);
    authorizationServerFilter.setTokenServices(tokenServices);
    return authorizationServerFilter;
  }

  @Bean
  @ConfigurationProperties("security.oauth2.client")
  public AuthorizationCodeResourceDetails authorizationServer() {
    return new AuthorizationCodeResourceDetails();
  }

  @Bean
  @ConfigurationProperties("security.oauth.resource")
  public ResourceServerProperties authorizationServerResource() {
    return new ResourceServerProperties();
  }
}
