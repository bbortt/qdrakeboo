package io.github.bbortt.qdrakeboo.authorizationserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.Test;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

public class GrantTypeUnitTest {

  @Test
  public void equalsReturnsTrueOnSameObjects() {
    long id = 1L;
    Date created = new Date();
    Date lastUpdated = new Date();
    String name = "scope-name";

    GrantType first = new GrantType();
    first.setId(id);
    first.setCreated(created);
    first.setLastUpdated(lastUpdated);
    first.setName(name);

    GrantType second = new GrantType();
    second.setId(id);
    second.setCreated(created);
    second.setLastUpdated(lastUpdated);
    second.setName(name);

    assertThat(first.equals(second)).isTrue();
  }
}
