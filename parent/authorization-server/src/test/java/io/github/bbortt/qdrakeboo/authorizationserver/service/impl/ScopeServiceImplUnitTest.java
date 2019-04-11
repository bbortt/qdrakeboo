package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ScopeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

public class ScopeServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  ScopeCRUDRepository scopeCRUDRepositoryMock;

  ScopeServiceImpl fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new ScopeServiceImpl(scopeCRUDRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(ScopeServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(ScopeService.class).isAssignableFrom(ScopeServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ScopeServiceImpl(scopeCRUDRepositoryMock))
        .hasFieldOrPropertyWithValue("scopeCRUDRepository", scopeCRUDRepositoryMock);
  }

  @Test
  public void getScopesTransformsRepositoryResult() {
    Scope scope = new Scope();
    Iterable<Scope> scopes = Collections.singletonList(scope);
    doReturn(scopes).when(scopeCRUDRepositoryMock).findAll();

    assertThat(fixture.getScopes()).containsExactly(scope);

    verify(scopeCRUDRepositoryMock).findAll();
  }

  @Test
  public void findByNameQueriesRepository() {
    String nameParameter = "name-parameter";

    Scope scope = new Scope();
    doReturn(Optional.of(scope)).when(scopeCRUDRepositoryMock).findByName(nameParameter);

    assertThat(fixture.findByName(nameParameter)).isEqualTo(scope);

    verify(scopeCRUDRepositoryMock).findByName(Mockito.eq(nameParameter));
  }

  @Test
  public void findByNameThrowsErrorIfNoScopeFound() {
    String nameParameter = "name-parameter";

    doReturn(Optional.empty()).when(scopeCRUDRepositoryMock).findByName(nameParameter);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Scope 'name-parameter' does not exist!");
    fixture.findByName(nameParameter);
  }
}
