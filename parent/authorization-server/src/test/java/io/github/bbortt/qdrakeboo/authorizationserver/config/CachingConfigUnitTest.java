package io.github.bbortt.qdrakeboo.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class CachingConfigUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(CachingConfig.class).hasAnnotation(Configuration.class)
        .hasAnnotation(EnableCaching.class);
  }

  @Test
  public void runsInProdProfileOnly() {
    Profile profile = CachingConfig.class.getDeclaredAnnotation(Profile.class);
    assertThat(profile.value()).containsExactly("prod");
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new CachingConfig()).isNotNull();
  }
}
