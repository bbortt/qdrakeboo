package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class AuthorityCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AuthorityCRUDRepository.class).hasDeclaredMethods("findByName");
  }
}
