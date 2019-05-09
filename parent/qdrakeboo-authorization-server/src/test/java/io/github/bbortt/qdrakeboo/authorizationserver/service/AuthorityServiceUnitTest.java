package io.github.bbortt.qdrakeboo.authorizationserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class AuthorityServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(AuthorityService.class).hasDeclaredMethods("getAuthorities", "findByName",
        "saveNewAuthority", "updateAuthority", "deleteByUUID");
  }
}
