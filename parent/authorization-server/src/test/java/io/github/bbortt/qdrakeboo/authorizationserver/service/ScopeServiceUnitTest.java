package io.github.bbortt.qdrakeboo.authorizationserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ScopeServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(ScopeService.class).hasDeclaredMethods("getScopes", "findByName");
  }
}
