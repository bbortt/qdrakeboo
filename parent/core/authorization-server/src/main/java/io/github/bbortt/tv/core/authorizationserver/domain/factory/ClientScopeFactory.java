package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientScope;

public class ClientScopeFactory implements EntityFactory<ClientScope> {

  public static ClientScopeFactory getInstance() {
    return ClientScopeFactoryHolder.INSTANCE;
  }

  private ClientScopeFactory() {

  }

  @Override
  public ClientScope fromResultSet(ResultSet resultSet) throws SQLException {
    ClientScope clientScope = new ClientScope();

    clientScope.setCreated(resultSet.getDate(ClientScope.CLIENT_SCOPE_CREATED_COLUMN_NAME));
    clientScope
        .setLastUpdated(resultSet.getDate(ClientScope.CLIENT_SCOPE_LAST_UPDATED_COLUMN_NAME));

    clientScope.setName(resultSet.getString(ClientScope.NAME_COLUMN_NAME));

    return clientScope;
  }

  private static class ClientScopeFactoryHolder {

    static final ClientScopeFactory INSTANCE = getInstance();

    static ClientScopeFactory getInstance() {
      return new ClientScopeFactory();
    }
  }
}
