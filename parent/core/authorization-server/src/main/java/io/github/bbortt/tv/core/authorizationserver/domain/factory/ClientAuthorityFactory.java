package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientAuthority;

public class ClientAuthorityFactory implements EntityFactory<ClientAuthority> {

  public static ClientAuthorityFactory getInstance() {
    return ClientAuthorityFactoryHolder.INSTANCE;
  }

  private ClientAuthorityFactory() {

  }

  @Override
  public ClientAuthority fromResultSet(ResultSet resultSet) throws SQLException {
    ClientAuthority clientAuthority = new ClientAuthority();

    clientAuthority.setCreated(resultSet.getDate(ClientAuthority.CLIENT_AUTHORITY_CREATED_COLUMN_NAME));
    clientAuthority.setLastUpdated(
        resultSet.getDate(ClientAuthority.CLIENT_AUTHORITY_LAST_UPDATED_COLUMN_NAME));

    clientAuthority.setName(resultSet.getString(ClientAuthority.NAME_COLUMN_NAME));

    return clientAuthority;
  }

  private static class ClientAuthorityFactoryHolder {

    static final ClientAuthorityFactory INSTANCE = getInstance();

    static ClientAuthorityFactory getInstance() {
      return new ClientAuthorityFactory();
    }
  }
}
