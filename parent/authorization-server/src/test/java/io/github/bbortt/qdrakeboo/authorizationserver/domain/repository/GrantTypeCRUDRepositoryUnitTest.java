package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class GrantTypeCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(GrantTypeCRUDRepository.class).hasDeclaredMethods("findByName");
  }
}
