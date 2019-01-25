package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import io.github.bbortt.tv.core.authorizationserver.domain.Authority;
import io.github.bbortt.tv.core.authorizationserver.domain.Client;
import io.github.bbortt.tv.core.authorizationserver.domain.GrantType;
import io.github.bbortt.tv.core.authorizationserver.domain.Scope;
import io.github.bbortt.tv.core.authorizationserver.util.DublicateCheckingSet;

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

    client.setId(resultSet.getLong(Client.ID_RESULT_NAME));

    client.setCreated(resultSet.getDate(Client.CLIENT_CREATED_RESULT_NAME));
    client.setLastUpdated(resultSet.getDate(Client.CLIENT_LAST_UPDATED_RESULT_NAME));

    client.setClientId(resultSet.getString(Client.CLIENT_ID_RESULT_NAME));
    client.setSecret(resultSet.getString(Client.SECRET_RESULT_NAME));
    client.setSecretRequired(resultSet.getBoolean(Client.SECRET_REQUIRED_RESULT_NAME));
    client.setAutoApprove(resultSet.getBoolean(Client.AUTO_APPROVE_RESULT_NAME));
    client.setAccessTokenValiditySeconds(
        resultSet.getInt(Client.ACCESS_TOKEN_VALIDITY_SECONDS_RESULT_NAME));
    client.setRefreshTokenValiditySeconds(
        resultSet.getInt(Client.REFRESH_TOKEN_VALIDITY_SECONDS_RESULT_NAME));
    client.setRedirectUris(resultSet.getString(Client.REDIRECT_URIS_RESULT_NAME));

    Set<GrantType> grantTypes = new DublicateCheckingSet<>();
    grantTypes.add(grantTypeFactory.fromResultSet(resultSet));

    Set<Authority> authorities = new DublicateCheckingSet<>();
    authorities.add(authorityFactory.fromResultSet(resultSet));

    Set<Scope> scopes = new DublicateCheckingSet<>();
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
