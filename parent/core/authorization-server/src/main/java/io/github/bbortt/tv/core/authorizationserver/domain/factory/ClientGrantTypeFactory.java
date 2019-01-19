package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientGrantType;

public class ClientGrantTypeFactory implements EntityFactory<ClientGrantType> {

  public static ClientGrantTypeFactory getInstance() {
    return ClientGrantTypeFactoryHolder.INSTANCE;
  }

  private ClientGrantTypeFactory() {

  }

  @Override
  public ClientGrantType fromResultSet(ResultSet resultSet) throws SQLException {
    ClientGrantType clientGrantType = new ClientGrantType();

    clientGrantType
        .setCreated(resultSet.getDate(ClientGrantType.CLIENT_GRANT_TYPE_CREATED_COLUMN_NAME));
    clientGrantType.setLastUpdated(
        resultSet.getDate(ClientGrantType.CLIENT_GRANT_TYPE_LAST_UPDATED_COLUMN_NAME));

    clientGrantType.setName(resultSet.getString(ClientGrantType.NAME_COLUMN_NAME));

    return clientGrantType;
  }

  private static class ClientGrantTypeFactoryHolder {

    static final ClientGrantTypeFactory INSTANCE = getInstance();

    static ClientGrantTypeFactory getInstance() {
      return new ClientGrantTypeFactory();
    }
  }
}
