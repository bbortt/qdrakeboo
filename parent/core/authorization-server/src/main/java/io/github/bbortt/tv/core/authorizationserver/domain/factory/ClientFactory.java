package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import io.github.bbortt.tv.core.authorizationserver.domain.Authority;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;
import io.github.bbortt.tv.core.authorizationserver.domain.GrantType;
import io.github.bbortt.tv.core.authorizationserver.domain.Scope;

public class ClientFactory implements EntityFactory<Client> {

  private final AuthorityFactory authorityFactory;
  private final GrantTypeFactory grantTypeFactory;
  private final ScopeFactory scopeFactory;

  public static ClientFactory getInstance() {
    return ClientFactoryHolder.INSTANCE;
  }

  private ClientFactory() {
    this.authorityFactory = AuthorityFactory.getInstance();
    this.grantTypeFactory = GrantTypeFactory.getInstance();
    this.scopeFactory = ScopeFactory.getInstance();
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
    client.setRedirectUris(resultSet.getString(Client.REDIRECT_URIS_COLUMN_NAME));

    Set<GrantType> grantTypes = new HashSet<>();
    grantTypes.add(grantTypeFactory.fromResultSet(resultSet));

    Set<Authority> authorities = new HashSet<>();
    authorities.add(authorityFactory.fromResultSet(resultSet));

    Set<Scope> scopes = new HashSet<>();
    scopes.add(scopeFactory.fromResultSet(resultSet));

    while (resultSet.next()) {
      grantTypes.add(grantTypeFactory.fromResultSet(resultSet));
      authorities.add(authorityFactory.fromResultSet(resultSet));
      scopes.add(scopeFactory.fromResultSet(resultSet));
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
