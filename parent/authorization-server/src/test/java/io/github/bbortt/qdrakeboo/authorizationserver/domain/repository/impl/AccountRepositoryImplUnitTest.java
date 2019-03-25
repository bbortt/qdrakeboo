package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
import org.springframework.stereotype.Repository;
import org.springframework.test.util.ReflectionTestUtils;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.factory.AccountFactory;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.impl.AccountRepositoryImpl;

public class AccountRepositoryImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  DataSource dataSourceMock;

  @Mock
  AccountFactory accountFactoryMock;

  AccountRepositoryImpl fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AccountRepositoryImpl(dataSourceMock);
    ReflectionTestUtils.setField(fixture, "accountFactory", accountFactoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AccountRepositoryImpl.class).hasAnnotation(Repository.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AccountRepositoryImpl(dataSourceMock)).hasFieldOrPropertyWithValue("dataSource",
        dataSourceMock);
  }

  @Test
  public void findOneByAccountnameReturnsAccount() throws SQLException {
    String accountname = "this-is-an-accountname";

    Connection connectionMock = Mockito.mock(Connection.class);
    doReturn(connectionMock).when(dataSourceMock).getConnection();

    PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    doReturn(preparedStatementMock).when(connectionMock).prepareStatement(Mockito.anyString());

    ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    doReturn(true).when(resultSetMock).next();
    doReturn(resultSetMock).when(preparedStatementMock).executeQuery();

    Account account = new Account();
    doReturn(account).when(accountFactoryMock).fromResultSet(resultSetMock);

    assertThat(fixture.findOneByAccountname(accountname)).isEqualTo(Optional.of(account));

    verify(preparedStatementMock).setString(Mockito.eq(1), Mockito.eq(accountname));
    verify(preparedStatementMock).executeQuery();
    verify(accountFactoryMock).fromResultSet(Mockito.eq(resultSetMock));
  }

  @Test
  public void findOneByAccountnameReturnsEmptyOptional() throws SQLException {
    String accountname = "this-is-a-nonexisting-accountname";

    Connection connectionMock = Mockito.mock(Connection.class);
    doReturn(connectionMock).when(dataSourceMock).getConnection();

    PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
    doReturn(preparedStatementMock).when(connectionMock).prepareStatement(Mockito.anyString());

    ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    doReturn(resultSetMock).when(preparedStatementMock).executeQuery();

    assertThat(fixture.findOneByAccountname(accountname)).isEqualTo(Optional.empty());

    verify(preparedStatementMock).setString(Mockito.eq(1), Mockito.eq(accountname));
    verify(preparedStatementMock).executeQuery();
    verify(accountFactoryMock, never()).fromResultSet(Mockito.eq(resultSetMock));
  }

  @Test
  public void findOneByAccountnameThrowsIllegalArgumentException() throws SQLException {
    String accountname = "this-is-a-nonexisting-accountname";

    doThrow(new SQLException()).when(dataSourceMock).getConnection();

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("An unknown error has occured!");
    fixture.findOneByAccountname(accountname);
  }
}
