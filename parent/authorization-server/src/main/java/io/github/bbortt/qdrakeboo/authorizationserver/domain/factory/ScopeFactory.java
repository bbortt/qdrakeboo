package io.github.bbortt.qdrakeboo.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;

public class ScopeFactory implements EntityFactory<Scope> {

  public static ScopeFactory getInstance() {
    return ScopeFactoryHolder.INSTANCE;
  }

  private ScopeFactory() {

  }

  @Override
  public Scope fromResultSet(ResultSet resultSet) throws SQLException {
    Scope clientScope = new Scope();

    clientScope.setId(resultSet.getLong(Scope.ID_RESULT_NAME));
    
    clientScope.setCreated(resultSet.getDate(Scope.SCOPE_CREATED_RESULT_NAME));
    clientScope.setLastUpdated(resultSet.getDate(Scope.SCOPE_LAST_UPDATED_RESULT_NAME));

    clientScope.setName(resultSet.getString(Scope.NAME_RESULT_NAME));

    return clientScope;
  }

  private static class ScopeFactoryHolder {

    static final ScopeFactory INSTANCE = getInstance();

    static ScopeFactory getInstance() {
      return new ScopeFactory();
    }
  }
}
