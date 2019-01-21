package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.Authority;

public class AuthorityFactory implements EntityFactory<Authority> {

  public static AuthorityFactory getInstance() {
    return AuthorityFactoryHolder.INSTANCE;
  }

  private AuthorityFactory() {

  }

  @Override
  public Authority fromResultSet(ResultSet resultSet) throws SQLException {
    Authority clientAuthority = new Authority();

    clientAuthority.setCreated(resultSet.getDate(Authority.AUTHORITY_CREATED_COLUMN_NAME));
    clientAuthority.setLastUpdated(resultSet.getDate(Authority.AUTHORITY_LAST_UPDATED_COLUMN_NAME));

    clientAuthority.setName(resultSet.getString(Authority.NAME_COLUMN_NAME));

    return clientAuthority;
  }

  private static class AuthorityFactoryHolder {

    static final AuthorityFactory INSTANCE = getInstance();

    static AuthorityFactory getInstance() {
      return new AuthorityFactory();
    }
  }
}