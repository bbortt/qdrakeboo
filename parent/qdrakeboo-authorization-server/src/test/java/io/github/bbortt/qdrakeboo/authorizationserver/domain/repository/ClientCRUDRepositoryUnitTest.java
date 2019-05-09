package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ClientCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(ClientCRUDRepository.class).hasDeclaredMethods("findOneByClientId");
  }
}
