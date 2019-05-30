package io.github.bbortt.qdrakeboo.core.graphql.api.account.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class RoleCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(RoleCRUDRepository.class).hasDeclaredMethods("findByName");
  }
}
