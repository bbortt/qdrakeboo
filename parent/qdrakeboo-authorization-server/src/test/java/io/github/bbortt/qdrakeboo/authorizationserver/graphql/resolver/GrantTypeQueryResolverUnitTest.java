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
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;

public class GrantTypeQueryResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  GrantTypeService grantTypeServiceMock;

  GrantTypeQueryResolver fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new GrantTypeQueryResolver(grantTypeServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(GrantTypeQueryResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLQueryResolver.class).isAssignableFrom(GrantTypeQueryResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new GrantTypeQueryResolver(grantTypeServiceMock))
        .hasFieldOrPropertyWithValue("grantTypeService", grantTypeServiceMock);
  }

  @Test
  public void getAllGrantTypesCallsService() {
    fixture.getAllGrantTypes();

    verify(grantTypeServiceMock).getGrantTypes();
  }
}
