package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

  private static final String FIND_ONE_BY_USERNAME_QUERY =
      "SELECT * FROM USERS WHERE username LIKE '?'";

  private final DataSource dataSource;

  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Optional<UserDetails> findOneByUsername(String username) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_BY_USERNAME_QUERY);
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();

      return null;
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getLocalizedMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
