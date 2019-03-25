package io.github.bbortt.qdrakeboo.authorizationserver.check;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import io.github.bbortt.qdrakeboo.authorizationserver.check.DatasourceConnectionCheck;

public class DatasourceConnectionCheckUnitTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();;

  @Test
  public void isAnnotated() {
    assertThat(DatasourceConnectionCheck.class).hasAnnotation(Component.class);
  }

  @Test
  public void constructorReceivesNewConnection() throws SQLException {
    DataSource dataSourceSpy = Mockito.spy(DataSource.class);

    new DatasourceConnectionCheck(dataSourceSpy);

    verify(dataSourceSpy).getConnection();
  }

  @Test
  public void constructorCatchesSQLException() throws SQLException {
    String expectedErrorMessage = "error-because-no-datasource";

    DataSource dataSourceMock = Mockito.mock(DataSource.class);
    doThrow(new SQLException(expectedErrorMessage)).when(dataSourceMock).getConnection();

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectCause(is(instanceOf(SQLException.class)));
    expectedException.expectMessage(expectedErrorMessage);
    new DatasourceConnectionCheck(dataSourceMock);

    verify(dataSourceMock).getConnection();
  }
}
