package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

public class ClientServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  ClientCRUDRepository clientCRUDRepositoryMock;

  @Mock
  AuthorityService authorityServiceMock;
  @Mock
  GrantTypeService grantTypeServiceMock;
  @Mock
  ScopeService scopeServiceMock;

  ClientServiceImpl fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new ClientServiceImpl(clientCRUDRepositoryMock, authorityServiceMock,
        grantTypeServiceMock, scopeServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(ClientServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(ClientService.class).isAssignableFrom(ClientServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ClientServiceImpl(clientCRUDRepositoryMock, authorityServiceMock,
        grantTypeServiceMock, scopeServiceMock))
            .hasFieldOrPropertyWithValue("clientCRUDRepository", clientCRUDRepositoryMock)
            .hasFieldOrPropertyWithValue("authorityService", authorityServiceMock)
            .hasFieldOrPropertyWithValue("grantTypeService", grantTypeServiceMock)
            .hasFieldOrPropertyWithValue("scopeService", scopeServiceMock);
  }

  @Test
  public void getClientsTransformsRepositoryResult() {
    Client client = new Client();
    Iterable<Client> clients = Collections.singletonList(client);

    doReturn(clients).when(clientCRUDRepositoryMock).findAll();

    assertThat(fixture.getClients()).containsExactly(client);

    verify(clientCRUDRepositoryMock).findAll();
  }
}
