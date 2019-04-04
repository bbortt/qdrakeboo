package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import org.junit.Test;

public class AuthorityUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    UUID uuid = UUID.randomUUID();
    String name = "scope-name";

    Authority first = new Authority();
    first.setUuid(uuid);
    first.setName(name);

    Authority second = new Authority();
    second.setUuid(uuid);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
