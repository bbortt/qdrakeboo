package io.github.bbortt.qdrakeboo.core.graphql.api.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class AccountServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountService.class).hasDeclaredMethods("getCurrentAccount", "getAccounts",
        "saveNewAccount");
  }
}
