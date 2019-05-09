package io.github.bbortt.qdrakeboo.authorizationserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class GrantTypeServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(GrantTypeService.class).hasDeclaredMethods("getGrantTypes", "findByName");
  }
}
