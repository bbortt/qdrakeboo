package io.github.bbortt.qdrakeboo.authorizationserver.config;

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

import io.github.bbortt.qdrakeboo.authorizationserver.config.TokenStoreConfig;
import io.github.bbortt.qdrakeboo.authorizationserver.config.TokenStoreConfig.DevTokenStoreConfig;

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
  public void tokenStoreIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method tokenStore =
        TokenStoreConfig.class.getDeclaredMethod("tokenStore", RedisConnectionFactory.class);
    assertThat(tokenStore.getDeclaredAnnotation(Bean.class)).isNotNull();
  }

  @Test
  public void tokenStoreIsConfigured() {
    RedisConnectionFactory redisConnectionFactoryMock = Mockito.mock(RedisConnectionFactory.class);

    assertThat(new TokenStoreConfig().tokenStore(redisConnectionFactoryMock))
        .isInstanceOf(RedisTokenStore.class)
        .hasFieldOrPropertyWithValue("connectionFactory", redisConnectionFactoryMock);
  }

  @Test
  public void devTokenStoreConfigIsAnnotated() {
    assertThat(DevTokenStoreConfig.class).hasAnnotation(Configuration.class);

    assertThat(DevTokenStoreConfig.class.getDeclaredAnnotation(Profile.class).value())
        .containsExactly("dev");
  }

  @Test
  public void devTokenStoreConfigHasPublicConstructor() {
    assertThat(new DevTokenStoreConfig()).isNotNull();
  }

  @Test
  public void devTokenStoreIsConfigured() {
    DataSource dataSourcerMock = Mockito.mock(DataSource.class);

    assertThat(new DevTokenStoreConfig().tokenStore(dataSourcerMock))
        .isInstanceOf(JdbcTokenStore.class);
  }
}
