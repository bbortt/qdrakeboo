package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import org.junit.Test;

public class ScopeUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    UUID uuid = UUID.randomUUID();
    String name = "scope-name";

    Scope first = new Scope();
    first.setUuid(uuid);
    first.setName(name);

    Scope second = new Scope();
    second.setUuid(uuid);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
