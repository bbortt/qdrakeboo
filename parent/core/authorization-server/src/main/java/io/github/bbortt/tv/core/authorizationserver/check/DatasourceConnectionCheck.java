package io.github.bbortt.tv.core.authorizationserver.check;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DatasourceConnectionCheck {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatasourceConnectionCheck.class);

  public DatasourceConnectionCheck(DataSource dataSource) {
    try (Connection connection = dataSource.getConnection()) {
      LOGGER.info("Database connection to successfully established");
    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
