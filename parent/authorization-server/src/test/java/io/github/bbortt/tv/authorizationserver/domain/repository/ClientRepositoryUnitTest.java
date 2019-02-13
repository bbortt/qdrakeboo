package io.github.bbortt.tv.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import io.github.bbortt.tv.authorizationserver.domain.repository.ClientRepository;

public class ClientRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(ClientRepository.class).hasDeclaredMethods("findOneByClientId");
  }
}
