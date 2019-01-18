package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import io.github.bbortt.tv.core.authorizationserver.config.userdetails.UserDetailsImpl;
import io.github.bbortt.tv.core.authorizationserver.domain.AbstractAuditingEntity;
import io.github.bbortt.tv.core.authorizationserver.domain.User;
import io.github.bbortt.tv.core.authorizationserver.domain.UserRole;
import io.github.bbortt.tv.core.authorizationserver.domain.factory.UserFactory;

@Repository
public class UserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

  // @formatter:off
  private static final String SELECT_FROM_USERS_QUERY =
      "SELECT u." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + User.USER_CREATED_COLUMN_NAME + "," +
      "     u." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + User.USER_LAST_UPDATED_COLUMN_NAME + "," +
      "     ur." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + UserRole.USER_ROLE_CREATED_COLUMN_NAME + "," +
      "     ur." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + UserRole.USER_ROLE_LAST_UPDATED_COLUMN_NAME + "," +
      " * FROM " + User.TABLE_NAME + " AS u" +
      "   JOIN user_has_roles AS uhr" +
      "     ON u.id = uhr.user_id" +
      "   JOIN " + UserRole.TABLE_NAME + " AS ur" +
      "     ON uhr.user_role_id = ur.id";

  private static final String WHERE_USERNAME_CLAUSE = " WHERE u.username LIKE ?";

  private static final String FIND_ONE_BY_USERNAME_QUERY = SELECT_FROM_USERS_QUERY + WHERE_USERNAME_CLAUSE;
  // @formatter:on

  private final DataSource dataSource;
  private final UserFactory userFactory;

  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
    this.userFactory = UserFactory.getInstance();
  }

  @PostConstruct
  public void postConstruct() {
    findOneByUsername("reidamaxia");
  }

  public Optional<UserDetails> findOneByUsername(String username) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_BY_USERNAME_QUERY);
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (!resultSet.next()) {
        return Optional.empty();
      }

      return Optional.of(new UserDetailsImpl(userFactory.fromResultSet(resultSet)));
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
