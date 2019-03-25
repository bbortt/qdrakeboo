package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountRepository;

public class AccountRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountRepository.class).hasDeclaredMethods("findOneByAccountname");
  }
}
