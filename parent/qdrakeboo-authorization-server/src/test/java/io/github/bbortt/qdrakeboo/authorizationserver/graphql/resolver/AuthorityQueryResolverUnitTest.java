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
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

public class AuthorityQueryResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AuthorityService authorityServiceMock;

  AuthorityQueryResolver fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new AuthorityQueryResolver(authorityServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AuthorityQueryResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLQueryResolver.class).isAssignableFrom(AuthorityQueryResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AuthorityQueryResolver(authorityServiceMock))
        .hasFieldOrPropertyWithValue("authorityService", authorityServiceMock);
  }

  @Test
  public void getAllAuthoritiesCallsService() {
    fixture.getAllAuthorities();

    verify(authorityServiceMock).getAuthorities();
  }
}
