package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.Scope;

public class ScopeFactory implements EntityFactory<Scope> {

  public static ScopeFactory getInstance() {
    return ScopeFactoryHolder.INSTANCE;
  }

  private ScopeFactory() {

  }

  @Override
  public Scope fromResultSet(ResultSet resultSet) throws SQLException {
    Scope clientScope = new Scope();

    clientScope.setCreated(resultSet.getDate(Scope.SCOPE_CREATED_COLUMN_NAME));
    clientScope.setLastUpdated(resultSet.getDate(Scope.SCOPE_LAST_UPDATED_COLUMN_NAME));

    clientScope.setName(resultSet.getString(Scope.NAME_COLUMN_NAME));

    return clientScope;
  }

  private static class ScopeFactoryHolder {

    static final ScopeFactory INSTANCE = getInstance();

    static ScopeFactory getInstance() {
      return new ScopeFactory();
    }
  }
}
