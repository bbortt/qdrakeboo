package io.github.bbortt.tv.core.authorizationserver.checks;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class DatasourceConnectionCheck {

  public DatasourceConnectionCheck(DataSource dataSource) {
    try (Connection connection = dataSource.getConnection()) {
      connection.getClientInfo().keySet().forEach(System.out::println);
    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
