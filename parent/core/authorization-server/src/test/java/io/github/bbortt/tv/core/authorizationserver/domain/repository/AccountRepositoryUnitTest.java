package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class AccountRepositoryUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountRepository.class).hasDeclaredMethods("findOneByAccount");
  }
}
