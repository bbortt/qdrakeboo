package io.github.bbortt.tv.core.authorizationserver.domain.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.test.util.ReflectionTestUtils;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;
import io.github.bbortt.tv.core.authorizationserver.domain.factory.ClientFactory;

public class ClientRepositoryImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  DataSource dataSourceMock;

  @Mock
  ClientFactory clientFactoryMock;

  ClientRepositoryImpl fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new ClientRepositoryImpl(dataSourceMock);
    ReflectionTestUtils.setField(fixture, "clientFactory", clientFactoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(ClientRepositoryImpl.class).hasAnnotation(Repository.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new ClientRepositoryImpl(dataSourceMock)).hasFieldOrPropertyWithValue("dataSource",
        dataSourceMock);
  }

  @Test
  public void findOneByClientIdIsCacheable() throws NoSuchMethodException, SecurityException {
    Method findOneByClientId =
        ClientRepositoryImpl.class.getDeclaredMethod("findOneByClientId", String.class);

    Cacheable cacheable = findOneByClientId.getDeclaredAnnotation(Cacheable.class);
    assertThat(cacheable).isNotNull();
    assertThat(cacheable.key()).isEqualTo("#result.clientId");
    assertThat(cacheable.cacheNames()).containsExactly(Client.CACHE_NAME);
  }

  @Test
  public void findOneByAccountnameReturnsAccount() throws SQLException {
    String clientId = "this-is-an-accountname";

    Connection connectionMock = Mockito.mock(Connection.class);
    doReturn(connectionMock).when(dataSourceMock).getConnection();

    PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    doReturn(preparedStatementMock).when(connectionMock).prepareStatement(Mockito.anyString());

    ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    doReturn(true).when(resultSetMock).next();
    doReturn(resultSetMock).when(preparedStatementMock).executeQuery();

    Client client = new Client();
    doReturn(client).when(clientFactoryMock).fromResultSet(resultSetMock);

    assertThat(fixture.findOneByClientId(clientId)).isEqualTo(Optional.of(client));

    verify(preparedStatementMock).setString(Mockito.eq(1), Mockito.eq(clientId));
    verify(preparedStatementMock).executeQuery();
    verify(clientFactoryMock).fromResultSet(Mockito.eq(resultSetMock));
  }

  @Test
  public void findOneByAccountnameReturnsEmptyOptional() throws SQLException {
    String clientId = "this-is-a-nonexisting-client-id";

    Connection connectionMock = Mockito.mock(Connection.class);
    doReturn(connectionMock).when(dataSourceMock).getConnection();

    PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    doReturn(preparedStatementMock).when(connectionMock).prepareStatement(Mockito.anyString());

    ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    doReturn(resultSetMock).when(preparedStatementMock).executeQuery();

    assertThat(fixture.findOneByClientId(clientId)).isEqualTo(Optional.empty());

    verify(preparedStatementMock).setString(Mockito.eq(1), Mockito.eq(clientId));
    verify(preparedStatementMock).executeQuery();
    verify(clientFactoryMock, never()).fromResultSet(Mockito.eq(resultSetMock));
  }

  @Test
  public void findOneByClientIdThrowsIllegalArgumentException() throws SQLException {
    String clientId = "this-is-a-nonexisting-client-id";

    doThrow(new SQLException()).when(dataSourceMock).getConnection();

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("An unknown error has occured!");
    fixture.findOneByClientId(clientId);
  }
}
