package io.github.bbortt.qdrakeboo.core.graphql.account.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.RoleCRUDRepository;

public class RoleCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(RoleCRUDRepository.class).hasDeclaredMethods("findByName");
  }
}
