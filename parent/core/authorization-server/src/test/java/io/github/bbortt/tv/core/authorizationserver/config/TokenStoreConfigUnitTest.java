package io.github.bbortt.tv.core.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class TokenStoreConfigUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  RedisConnectionFactory redisConnectionFactoryMock;

  TokenStoreConfig fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new TokenStoreConfig(redisConnectionFactoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(TokenStoreConfig.class).hasAnnotation(Configuration.class);
  }


  @Test
  public void constructorAcceptsArguments() {
    assertThat(new TokenStoreConfig(redisConnectionFactoryMock))
        .hasFieldOrPropertyWithValue("redisConnectionFactory", redisConnectionFactoryMock);
  }

  @Test
  public void tokenStoreIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method tokenStore = TokenStoreConfig.class.getDeclaredMethod("tokenStore");
    assertThat(tokenStore.getDeclaredAnnotation(Bean.class)).isNotNull();
  }

  @Test
  public void tokenStoreIsConfigured() {
    assertThat(fixture.tokenStore()).isInstanceOf(RedisTokenStore.class)
        .hasFieldOrPropertyWithValue("connectionFactory", redisConnectionFactoryMock);
  }
}
