package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import org.junit.Test;

public class GrantTypeUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    UUID uuid = UUID.randomUUID();
    String name = "scope-name";

    GrantType first = new GrantType();
    first.setUuid(uuid);
    first.setName(name);

    GrantType second = new GrantType();
    second.setUuid(uuid);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
