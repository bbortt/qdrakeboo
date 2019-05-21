package io.github.bbortt.qdrakeboo.core.graphql.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class RoleServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(RoleService.class).hasDeclaredMethods("getRoles", "findByName");
  }
}
