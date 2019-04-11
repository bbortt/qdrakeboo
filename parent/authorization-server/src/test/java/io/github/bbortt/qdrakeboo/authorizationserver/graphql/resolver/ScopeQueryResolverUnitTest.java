package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

public class ScopeQueryResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  ScopeService scopeServiceMock;

  ScopeQueryResolver fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new ScopeQueryResolver(scopeServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(ScopeQueryResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLQueryResolver.class).isAssignableFrom(ScopeQueryResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ScopeQueryResolver(scopeServiceMock)).hasFieldOrPropertyWithValue("scopeService",
        scopeServiceMock);
  }

  @Test
  public void getAllScopesCallsService() {
    fixture.getAllScopes();

    verify(scopeServiceMock).getScopes();
  }
}
