package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class AccountCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountCRUDRepository.class).hasDeclaredMethods("findOneByAccountnameIgnoreCase");
  }
}
