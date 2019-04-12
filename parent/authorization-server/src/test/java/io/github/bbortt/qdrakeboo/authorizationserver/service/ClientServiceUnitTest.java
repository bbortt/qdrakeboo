package io.github.bbortt.qdrakeboo.authorizationserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ClientServiceUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(ClientService.class).hasDeclaredMethods("getClients");
  }
}
