package io.github.bbortt.tv.core.authorizationserver.domain.repository.impl;

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
import io.github.bbortt.tv.core.authorizationserver.config.userdetails.UserDetailsImpl;
import io.github.bbortt.tv.core.authorizationserver.domain.AbstractAuditingEntity;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;
import io.github.bbortt.tv.core.authorizationserver.domain.Role;
import io.github.bbortt.tv.core.authorizationserver.domain.factory.AccountFactory;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.AccountRepository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class);

  // @formatter:off
  private static final String SELECT_FROM_USERS_QUERY =
      "SELECT a." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + Account.ACCOUNT_CREATED_COLUMN_NAME + "," +
      "     a." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + Account.ACCOUNT_LAST_UPDATED_COLUMN_NAME + "," +
      "     r." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + Role.ROLE_CREATED_COLUMN_NAME + "," +
      "     r." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + Role.ROLE_LAST_UPDATED_COLUMN_NAME + "," +
      " * FROM " + Account.TABLE_NAME + " AS a" +
      "   JOIN account_has_roles AS ahr" +
      "     ON a.id = ahr.role_id" +
      "   JOIN " + Role.TABLE_NAME + " AS r" +
      "     ON ahr.role_id = r.id";

  private static final String WHERE_ACCOUNTNAME_CLAUSE = " WHERE a." + Account.ACCOUNTNAME_COLUMN_NAME + " LIKE ?";

  private static final String FIND_ONE_BY_ACCOUNTNAME_QUERY = SELECT_FROM_USERS_QUERY + WHERE_ACCOUNTNAME_CLAUSE;
  // @formatter:on

  private final DataSource dataSource;
  private final AccountFactory accountFactory;

  public AccountRepositoryImpl(DataSource dataSource) {
    this.dataSource = dataSource;
    this.accountFactory = AccountFactory.getInstance();
  }

  public Optional<UserDetails> findOneByUsername(String username) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement =
          connection.prepareStatement(FIND_ONE_BY_ACCOUNTNAME_QUERY);
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (!resultSet.next()) {
        return Optional.empty();
      }

      return Optional.of(new UserDetailsImpl(accountFactory.fromResultSet(resultSet)));
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
