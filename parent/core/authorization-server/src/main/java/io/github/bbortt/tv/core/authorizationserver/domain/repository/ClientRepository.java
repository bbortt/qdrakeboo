package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);

  private static final String FIND_ONE_BY_CLIENT_ID_QUERY =
      "SELECT * FROM Clients WHERE client_id LIKE '?'";

  private final DataSource dataSource;

  public ClientRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Optional<ClientDetails> findOneByClientId(String clientId) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement =
          connection.prepareStatement(FIND_ONE_BY_CLIENT_ID_QUERY);
      preparedStatement.setString(1, clientId);
      ResultSet resultSet = preparedStatement.executeQuery();

      return null;
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getLocalizedMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
