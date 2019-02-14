package io.github.bbortt.tv.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Method;
import javax.sql.DataSource;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import io.github.bbortt.tv.authorizationserver.config.TokenStoreConfig.DevTokenStoreConfig;

public class TokenStoreConfigUnitTest {

  
  @Test
  public void isAnnotated() {
    assertThat(TokenStoreConfig.class).hasAnnotation(Configuration.class);
  }


  @Test
  public void hasPublicConstructor() {
    assertThat(new TokenStoreConfig()).isNotNull();
  }

  @Test
  public void redisTokenStoreIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method tokenStore =
        TokenStoreConfig.class.getDeclaredMethod("tokenStore", RedisConnectionFactory.class);
    assertThat(tokenStore.getDeclaredAnnotation(Bean.class)).isNotNull();
    assertThat(tokenStore.getDeclaredAnnotation(Profile.class).value())
        .containsExactly("!no-redis");
  }

  @Test
  public void redisTokenStoreIsConfigured() {
    RedisConnectionFactory redisConnectionFactoryMock = Mockito.mock(RedisConnectionFactory.class);

    assertThat(new TokenStoreConfig().tokenStore(redisConnectionFactoryMock)).isInstanceOf(RedisTokenStore.class)
        .hasFieldOrPropertyWithValue("connectionFactory", redisConnectionFactoryMock);
  }

  @Test
  public void jdbcTokenStoreIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method tokenStore = DevTokenStoreConfig.class.getDeclaredMethod("tokenStore", DataSource.class);
    assertThat(tokenStore.getDeclaredAnnotation(Bean.class)).isNotNull();
    assertThat(tokenStore.getDeclaredAnnotation(Profile.class).value())
        .containsExactly("no-redis");
  }

  @Test
  public void devTokenStoreConfigHasPublicConstructor() {
    assertThat(new DevTokenStoreConfig()).isNotNull();
  }
  
  @Test
  public void jdbcTokenStoreIsConfigured() {
    DataSource dataSourceMock = Mockito.mock(DataSource.class);

    assertThat(new DevTokenStoreConfig().tokenStore(dataSourceMock)).isInstanceOf(JdbcTokenStore.class);
  }
}
