package io.github.bbortt.tv.core.authorizationserver.domain.repository.syntaxcheck;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.context.ApplicationListener;

public class RepositorySyntaxCheckUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(RepositorySyntaxCheck.class).hasDeclaredMethods("checkSyntax");
  }

  @Test
  public void extendsInterface() {
    assertThat(ApplicationListener.class).isAssignableFrom(RepositorySyntaxCheck.class);
  }
}
