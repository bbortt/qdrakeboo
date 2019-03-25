package io.github.bbortt.qdrakeboo.dev.eureka.autoconfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import io.github.bbortt.qdrakeboo.dev.eureka.autoconfiguration.EurekaClientAutoconfiguration.EurekaProdConfiguration;

public class EurekaProdConfigurationUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(EurekaProdConfiguration.class).hasAnnotations(Configuration.class, Profile.class, PropertySource.class);

    
  }

  @Test
  public void hasPublicConstructor() {
	assertThat(new EurekaProdConfiguration()).isNotNull();
  }
}
