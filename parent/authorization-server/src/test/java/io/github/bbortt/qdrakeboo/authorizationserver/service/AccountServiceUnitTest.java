package io.github.bbortt.qdrakeboo.authorizationserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

public class AccountServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AccountService.class).hasDeclaredMethods("getCurrentAccount");
  }
}
