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
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;

public class ClientQueryResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  ClientService clientServiceMock;

  ClientQueryResolver fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new ClientQueryResolver(clientServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(ClientQueryResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLQueryResolver.class).isAssignableFrom(ClientQueryResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ClientQueryResolver(clientServiceMock))
        .hasFieldOrPropertyWithValue("clientService", clientServiceMock);
  }

  @Test
  public void getAllRolesCallsService() {
    fixture.getAllClients();

    verify(clientServiceMock).getClients();
  }
}
