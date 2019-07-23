package io.github.bbortt.qdrakeboo.edgegateway.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

public class UserIdFilterUnitTest {

  UserIdFilter fixture;

  @Before
  public void beforeClassSetup() {
    fixture = new UserIdFilter();
  }

  @Test
  public void isAnnotated() {
    assertThat(fixture.getClass()).hasAnnotation(Component.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new UserIdFilter()).isNotNull();
  }
}
