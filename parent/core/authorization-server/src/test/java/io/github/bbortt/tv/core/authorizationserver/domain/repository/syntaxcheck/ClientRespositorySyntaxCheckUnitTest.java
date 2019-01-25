package io.github.bbortt.tv.core.authorizationserver.domain.repository.syntaxcheck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.ClientRepository;

public class ClientRespositorySyntaxCheckUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Spy
  ClientRepository clientRepositorySpy;

  ClientRepositorySyntaxCheck fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new ClientRepositorySyntaxCheck(clientRepositorySpy);
  }

  @Test
  public void isAnnotated() {
    assertThat(ClientRepositorySyntaxCheck.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(RepositorySyntaxCheck.class).isAssignableFrom(ClientRepositorySyntaxCheck.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ClientRepositorySyntaxCheck(clientRepositorySpy))
        .hasFieldOrPropertyWithValue("clientRepository", clientRepositorySpy);
  }

  @Test
  public void onApplicationEventCallsCheckSyntax() {
    ClientRepositorySyntaxCheck fixtureSpy = Mockito.spy(fixture);

    fixtureSpy.onApplicationEvent(Mockito.mock(ApplicationReadyEvent.class));

    verify(fixtureSpy).checkSyntax();
  }

  @Test
  public void checkSyntaxCallsRepositoryMethods() {
    fixture.checkSyntax();

    verify(clientRepositorySpy).findOneByClientId(Mockito.eq(""));
  }
}
