package io.github.bbortt.tv.core.authorizationserver.domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;
import io.github.bbortt.tv.core.authorizationserver.config.clientdetails.ClientDetailsImpl;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;
import io.github.bbortt.tv.core.authorizationserver.domain.factory.ClientFactory;

@Repository
public class ClientRepositoryImpl {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryImpl.class);

  private static final String FIND_ONE_BY_CLIENT_ID_QUERY =
      "SELECT * FROM Clients WHERE client_id LIKE '?'";

  private final DataSource dataSource;
  private final ClientFactory clientFactory;

  public ClientRepositoryImpl(DataSource dataSource) {
    this.dataSource = dataSource;
    this.clientFactory = ClientFactory.getInstance();
  }

  @Cacheable(cacheNames = {Client.CACHE_NAME}, key = "#result.clientId")
  public Optional<ClientDetails> findOneByClientId(String clientId) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement =
          connection.prepareStatement(FIND_ONE_BY_CLIENT_ID_QUERY);
      preparedStatement.setString(1, clientId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (!resultSet.next()) {
        return Optional.empty();
      }

      return Optional.of(new ClientDetailsImpl(clientFactory.fromResultSet(resultSet)));
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getLocalizedMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
