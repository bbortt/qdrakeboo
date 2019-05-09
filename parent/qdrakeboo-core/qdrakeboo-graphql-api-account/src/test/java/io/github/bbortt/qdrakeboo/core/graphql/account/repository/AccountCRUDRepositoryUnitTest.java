package io.github.bbortt.qdrakeboo.core.graphql.account.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.AccountCRUDRepository;

public class AccountCRUDRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountCRUDRepository.class).hasDeclaredMethods("findOneByAccountnameIgnoreCase");
  }
}
