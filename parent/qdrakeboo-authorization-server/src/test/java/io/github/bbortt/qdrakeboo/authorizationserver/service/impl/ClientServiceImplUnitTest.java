package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Predicate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
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

    doAnswer(new Answer<Client>() {
      @Override
      public Client answer(InvocationOnMock invocation) throws Throwable {
        return invocation.getArgument(0);
      }
    }).when(clientCRUDRepositoryMock).save(Mockito.any(Client.class));
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

  @Test
  public void saveNewClientChecksForExistingUUID() {
    Client client = new Client();
    client.setUuid(UUID.fromString("986c40b5-1738-4823-880e-ab553e3ad6e2"));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot save an existing Client!");
    fixture.saveNewClient(client);
  }

  @Test
  public void saveNewClientHashesSecret() {
    String secret = "secret";

    Client client = new Client();
    client.setSecret(secret);

    assertThat(fixture.saveNewClient(client).getSecret()).isNotEqualTo(secret);
  }

  @Test
  public void saveNewClientSavesNewAuthority() {
    String authorityName = "new-authority";

    Authority authority = new Authority();
    authority.setName(authorityName);

    Client client = new Client();
    client.setSecret("918afe76-2f76-45d6-9ea1-b761ba4dcb3b");
    client.setAuthorities(Collections.singletonList(authority));

    ReflectionTestUtils.setField(fixture, "doesAuthorityExist",
        (Predicate<Authority>) (Authority invocation) -> {
          return false;
        });

    doAnswer(new Answer<Authority>() {
      @Override
      public Authority answer(InvocationOnMock invocation) throws Throwable {
        return authority;
      }
    }).when(authorityServiceMock).findByName(authorityName);

    doAnswer(new Answer<Authority>() {
      @Override
      public Authority answer(InvocationOnMock invocation) throws Throwable {
        return invocation.getArgument(0);
      }
    }).when(authorityServiceMock).saveNewAuthority(authority);

    fixture.saveNewClient(client);

    verify(authorityServiceMock).saveNewAuthority(authority);
  }

  @Test
  public void saveNewClientCompletesEntity() {
    String secret = "16a8db9d-b041-4d6c-869c-b720aab7de52";
    String authorityName = "authorityName";
    String grantTypeName = "grantTypeName";
    String scopeName = "scopeName";

    Authority authority = new Authority();
    authority.setName(authorityName);
    doReturn(authority).when(authorityServiceMock).findByName(authorityName);

    GrantType grantType = new GrantType();
    grantType.setName(grantTypeName);
    doReturn(grantType).when(grantTypeServiceMock).findByName(grantTypeName);

    Scope scope = new Scope();
    scope.setName(scopeName);
    doReturn(scope).when(scopeServiceMock).findByName(scopeName);

    Client client = new Client();
    client.setSecret(secret);
    client.setAuthorities(Collections.singletonList(authority));
    client.setGrantTypes(Collections.singletonList(grantType));
    client.setScopes(Collections.singletonList(scope));

    Client newClient = fixture.saveNewClient(client);

    verify(clientCRUDRepositoryMock).save(Mockito.eq(client));

    assertThat(newClient.isAutoApprove()).isFalse();
    assertThat(newClient.isSecretRequired()).isTrue();
    assertThat(newClient.getAuthorities()).containsExactly(authority);
    assertThat(newClient.getGrantTypes()).containsExactly(grantType);
    assertThat(newClient.getScopes()).containsExactly(scope);
  }
}
