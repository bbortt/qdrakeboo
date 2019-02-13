package io.github.bbortt.tv.authorizationserver.domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import io.github.bbortt.tv.authorizationserver.domain.AbstractAuditingEntity;
import io.github.bbortt.tv.authorizationserver.domain.Authority;
import io.github.bbortt.tv.authorizationserver.domain.Client;
import io.github.bbortt.tv.authorizationserver.domain.GrantType;
import io.github.bbortt.tv.authorizationserver.domain.Scope;
import io.github.bbortt.tv.authorizationserver.domain.factory.ClientFactory;
import io.github.bbortt.tv.authorizationserver.domain.repository.ClientRepository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryImpl.class);

  // @formatter:off
  private static final String SELECT_FROM_CLIENTS_QUERY =
      "SELECT c." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + Client.CLIENT_CREATED_RESULT_NAME + "," +
      "     c." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + Client.CLIENT_LAST_UPDATED_RESULT_NAME + "," +
      "     a." + Authority.ID_COLUMN_NAME + " AS " + Authority.ID_RESULT_NAME + "," +
      "     a." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + Authority.AUTHORITY_CREATED_RESULT_NAME + "," +
      "     a." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + Authority.AUTHORITY_LAST_UPDATED_RESULT_NAME + "," +
      "     a." + Authority.NAME_COLUMN_NAME + " AS " + Authority.NAME_RESULT_NAME + "," +
      "     gt." + GrantType.ID_COLUMN_NAME + " AS " + GrantType.ID_RESULT_NAME + "," +
      "     gt." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + GrantType.GRANT_TYPE_CREATED_RESULT_NAME + "," +
      "     gt." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + GrantType.GRANT_TYPE_LAST_UPDATED_RESULT_NAME + "," +
      "     gt." + GrantType.NAME_COLUMN_NAME + " AS " + GrantType.NAME_RESULT_NAME + "," +
      "     s." + Scope.ID_COLUMN_NAME + " AS " + Scope.ID_RESULT_NAME + "," +
      "     s." + AbstractAuditingEntity.CREATED_COLUMN_NAME + " AS " + Scope.SCOPE_CREATED_RESULT_NAME + "," +
      "     s." + AbstractAuditingEntity.LAST_UPDATED_COLUMN_NAME + " AS " + Scope.SCOPE_LAST_UPDATED_RESULT_NAME + "," +
      "     s." + Scope.NAME_COLUMN_NAME + " AS " + Scope.NAME_RESULT_NAME + "," +
      " * FROM " + Client.TABLE_NAME + " AS c" +
      "   JOIN client_has_authorities as cha" +
      "     ON c.id = cha.client_id" +
      "   JOIN " + Authority.TABLE_NAME + " as a" +
      "     ON cha.authority_id = a.id" +
      "   JOIN client_has_grant_types as chgt" +
      "     ON c.id = chgt.client_id" +
      "   JOIN " + GrantType.TABLE_NAME + " as gt" +
      "     ON chgt.grant_type_id = gt.id" +
      "   JOIN client_has_scopes as chs" +
      "     ON c.id = chs.client_id" +
      "   JOIN " + Scope.TABLE_NAME + " as s" +
      "     ON chs.scope_id = s.id";

  private static final String WHERE_CLIENT_ID_CLAUSE = " WHERE c." + Client.CLIENT_ID_RESULT_NAME + " LIKE ?";

  private static final String FIND_ONE_BY_CLIENT_ID_QUERY = SELECT_FROM_CLIENTS_QUERY + WHERE_CLIENT_ID_CLAUSE;
  // @formatter:on

  private final DataSource dataSource;
  private final ClientFactory clientFactory;

  public ClientRepositoryImpl(DataSource dataSource) {
    this.dataSource = dataSource;
    this.clientFactory = ClientFactory.getInstance();
  }

  @Cacheable(cacheNames = {Client.CACHE_NAME}, key = "#result.clientId")
  public Optional<Client> findOneByClientId(String clientId) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement preparedStatement =
          connection.prepareStatement(FIND_ONE_BY_CLIENT_ID_QUERY);
      preparedStatement.setString(1, clientId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (!resultSet.next()) {
        return Optional.empty();
      }

      return Optional.of(clientFactory.fromResultSet(resultSet));
    } catch (SQLException e) {
      LOGGER.error("Error while querying datasource: {}", e.getLocalizedMessage());
      throw new IllegalArgumentException("An unknown error has occured!");
    }
  }
}
