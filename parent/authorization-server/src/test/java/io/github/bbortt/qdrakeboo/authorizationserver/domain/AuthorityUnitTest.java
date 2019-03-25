package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.Test;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;

public class AuthorityUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    long id = 1L;
    Date created = new Date();
    Date lastUpdated = new Date();
    String name = "scope-name";

    Authority first = new Authority();
    first.setId(id);
    first.setCreated(created);
    first.setLastUpdated(lastUpdated);
    first.setName(name);

    Authority second = new Authority();
    second.setId(id);
    second.setCreated(created);
    second.setLastUpdated(lastUpdated);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
