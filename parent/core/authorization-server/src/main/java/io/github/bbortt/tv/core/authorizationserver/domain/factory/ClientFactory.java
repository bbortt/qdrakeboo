package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientAuthority;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientGrantType;
import io.github.bbortt.tv.core.authorizationserver.domain.ClientScope;

public class ClientFactory implements EntityFactory<Client> {

  private final ClientAuthorityFactory clientAuthorityFactory;
  private final ClientGrantTypeFactory clientGrantTypeFactory;
  private final ClientScopeFactory clientScopeFactory;

  public static ClientFactory getInstance() {
    return ClientFactoryHolder.INSTANCE;
  }

  private ClientFactory() {
    this.clientAuthorityFactory = ClientAuthorityFactory.getInstance();
    this.clientGrantTypeFactory = ClientGrantTypeFactory.getInstance();
    this.clientScopeFactory = ClientScopeFactory.getInstance();
  }

  @Override
  public Client fromResultSet(ResultSet resultSet) throws SQLException {
    Client client = new Client();

    client.setCreated(resultSet.getDate(Client.CLIENT_CREATED_COLUMN_NAME));
    client.setLastUpdated(resultSet.getDate(Client.CLIENT_LAST_UPDATED_COLUMN_NAME));

    client.setId(resultSet.getLong(Client.ID_COLUMN_NAME));
    client.setClientId(resultSet.getString(Client.CLIENT_ID_COLUMN_NAME));
    client.setSecret(resultSet.getString(Client.SECRET_COLUMN_NAME));
    client.setSecretRequired(resultSet.getBoolean(Client.IS_SECRET_REQUIRED_COLUMN_NAME));
    client.setAutoApprove(resultSet.getBoolean(Client.IS_AUTO_APPROVE_COLUMN_NAME));
    client.setAccessTokenValiditySeconds(
        resultSet.getInt(Client.ACCESS_TOKEN_VALIDITY_SECONDS_COLUMN_NAME));
    client.setRefreshTokenValiditySeconds(
        resultSet.getInt(Client.REFRESH_TOKEN_VALIDITY_SECONDS_COLUMN_NAME));
    client.setRedirectUrl(resultSet.getString(Client.REDIRECT_URL_COLUMN_NAME));

    Set<ClientGrantType> grantTypes = new HashSet<>();
    grantTypes.add(clientGrantTypeFactory.fromResultSet(resultSet));

    Set<ClientAuthority> authorities = new HashSet<>();
    authorities.add(clientAuthorityFactory.fromResultSet(resultSet));

    Set<ClientScope> scopes = new HashSet<>();
    scopes.add(clientScopeFactory.fromResultSet(resultSet));

    while (resultSet.next()) {
      grantTypes.add(clientGrantTypeFactory.fromResultSet(resultSet));
      authorities.add(clientAuthorityFactory.fromResultSet(resultSet));
      scopes.add(clientScopeFactory.fromResultSet(resultSet));
    }

    client.setGrantTypes(grantTypes);
    client.setAuthorities(authorities);
    client.setScopes(scopes);

    return client;
  }

  private static class ClientFactoryHolder {

    static final ClientFactory INSTANCE = getInstance();

    static ClientFactory getInstance() {
      return new ClientFactory();
    }
  }
}
