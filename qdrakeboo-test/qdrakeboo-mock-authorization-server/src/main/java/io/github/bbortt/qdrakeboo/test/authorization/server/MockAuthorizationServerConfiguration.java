package io.github.bbortt.qdrakeboo.test.authorization.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class MockAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private AuthenticationManager authenticationManager;

  public MockAuthorizationServerConfiguration(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.checkTokenAccess("permitAll()");
    oauthServer.allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    super.configure(clients);
    clients.inMemory()
        .withClient("user")
        .secret("password")
        .authorizedGrantTypes("password")
        .scopes("openid");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    super.configure(endpoints);
    endpoints.authenticationManager(this.authenticationManager);
  }
}
