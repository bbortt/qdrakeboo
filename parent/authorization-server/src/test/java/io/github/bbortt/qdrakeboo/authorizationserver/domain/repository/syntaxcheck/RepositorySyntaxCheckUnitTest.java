package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.syntaxcheck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.syntaxcheck.RepositorySyntaxCheck;

public class RepositorySyntaxCheckUnitTest {

  @Test
  public void definesMethodsForImplementation() {
    assertThat(RepositorySyntaxCheck.class).hasDeclaredMethods("checkSyntax");
  }

  @Test
  public void extendsInterface() {
    assertThat(ApplicationListener.class).isAssignableFrom(RepositorySyntaxCheck.class);
  }

  @Test
  public void onApplicationEventCallsCheckSyntax() {
    RepositorySyntaxCheck repositorySyntaxCheckSpy = Mockito.spy(RepositorySyntaxCheck.class);

    repositorySyntaxCheckSpy.onApplicationEvent(Mockito.mock(ApplicationReadyEvent.class));

    verify(repositorySyntaxCheckSpy).checkSyntax();
  }
}
