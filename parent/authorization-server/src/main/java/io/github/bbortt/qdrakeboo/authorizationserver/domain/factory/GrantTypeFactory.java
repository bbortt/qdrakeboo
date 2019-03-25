package io.github.bbortt.qdrakeboo.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

public class GrantTypeFactory implements EntityFactory<GrantType> {

  public static GrantTypeFactory getInstance() {
    return GrantTypeFactoryHolder.INSTANCE;
  }

  private GrantTypeFactory() {

  }

  @Override
  public GrantType fromResultSet(ResultSet resultSet) throws SQLException {
    GrantType clientGrantType = new GrantType();

    clientGrantType.setId(resultSet.getLong(GrantType.ID_RESULT_NAME));

    clientGrantType.setCreated(resultSet.getDate(GrantType.GRANT_TYPE_CREATED_RESULT_NAME));
    clientGrantType
        .setLastUpdated(resultSet.getDate(GrantType.GRANT_TYPE_LAST_UPDATED_RESULT_NAME));

    clientGrantType.setName(resultSet.getString(GrantType.NAME_RESULT_NAME));

    return clientGrantType;
  }

  private static class GrantTypeFactoryHolder {

    static final GrantTypeFactory INSTANCE = getInstance();

    static GrantTypeFactory getInstance() {
      return new GrantTypeFactory();
    }
  }
}
