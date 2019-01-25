package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Set<GrantType> grantTypes = new TreeSet<>(GrantType.COMPARATOR);
    grantTypes.add(grantTypeFactory.fromResultSet(resultSet));

    Set<Authority> authorities = new TreeSet<>(Authority.COMPARATOR);
    authorities.add(authorityFactory.fromResultSet(resultSet));

    Set<Scope> scopes = new TreeSet<>(Scope.COMPARATOR);
    scopes.add(scopeFactory.fromResultSet(resultSet));

    while (resultSet.next()) {
      System.out.println(resultSet.toString());

      grantTypes.add(grantTypeFactory.fromResultSet(resultSet));
      authorities.add(authorityFactory.fromResultSet(resultSet));
      scopes.add(scopeFactory.fromResultSet(resultSet));
    }

    client.setGrantTypes(grantTypes);
    client.setAuthorities(authorities);
    client.setScopes(scopes);

    System.out.println(grantTypes);
    System.out.println(authorities);
    System.out.println(scopes);

    return client;
  }

  private static class ClientFactoryHolder {

    static final ClientFactory INSTANCE = getInstance();

    static ClientFactory getInstance() {
      return new ClientFactory();
    }
  }
}
