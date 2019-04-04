package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import org.junit.Test;

public class RoleUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    UUID uuid = UUID.randomUUID();
    String name = "scope-name";

    Role first = new Role();
    first.setUuid(uuid);
    first.setName(name);

    Role second = new Role();
    second.setUuid(uuid);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
