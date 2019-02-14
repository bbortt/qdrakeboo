package io.github.bbortt.tv.authorizationserver.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfig {

  @Bean
  @Profile("!no-redis")
  public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
    return new RedisTokenStore(redisConnectionFactory);
  }

  @Configuration
  @Profile("dev")
  public static class DevTokenStoreConfig {

    @Bean
    @Profile("no-redis")
    public TokenStore tokenStore(DataSource jdbcDataSource) {
      return new JdbcTokenStore(jdbcDataSource);
    }
  }
}
