package io.github.bbortt.tv.dev.eureka.autoconfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

public class EurekaClientAutoconfigurationUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(EurekaClientAutoconfiguration.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void hasPublicConstructor() {
	assertThat(new EurekaClientAutoconfiguration()).isNotNull();
  }

  @Test
  public void devProfileIsPublicStatic() {
	assertThat(EurekaClientAutoconfiguration.PROFILE_DEV).isEqualTo("dev");
  }
}
