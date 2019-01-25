package io.github.bbortt.tv.core.authorizationserver.util;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class DublicateAwareHashSetUnitTest {

  DublicateAwareHashSet<Object> fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new DublicateAwareHashSet<>();
  }

  @Test
  public void addChecksExistingEntries() {
    Mock first = new Mock(1);
    Mock second = new Mock(1);
    Mock third = new Mock(2);

    fixture.add(first);

    assertThat(fixture.add(second)).isFalse();
    assertThat(fixture.add(third)).isTrue();
  }

  private static class Mock {

    private final int i;

    public Mock(int i) {
      this.i = i;
    }

    @Override
    public boolean equals(Object object) {
      if (!(object instanceof Mock))
        return false;
      return i == ((Mock) object).i;
    }
  }
}
