package io.github.bbortt.tv.core.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.Test;

public class RoleUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    long id = 1L;
    Date created = new Date();
    Date lastUpdated = new Date();
    String name = "scope-name";

    Role first = new Role();
    first.setId(id);
    first.setCreated(created);
    first.setLastUpdated(lastUpdated);
    first.setName(name);

    Role second = new Role();
    second.setId(id);
    second.setCreated(created);
    second.setLastUpdated(lastUpdated);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
