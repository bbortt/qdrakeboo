package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.GrantType;

public class GrantTypeFactory implements EntityFactory<GrantType> {

  public static GrantTypeFactory getInstance() {
    return GrantTypeFactoryHolder.INSTANCE;
  }

  private GrantTypeFactory() {

  }

  @Override
  public GrantType fromResultSet(ResultSet resultSet) throws SQLException {
    GrantType clientGrantType = new GrantType();

    clientGrantType.setCreated(resultSet.getDate(GrantType.GRANT_TYPE_CREATED_COLUMN_NAME));
    clientGrantType
        .setLastUpdated(resultSet.getDate(GrantType.GRANT_TYPE_LAST_UPDATED_COLUMN_NAME));

    clientGrantType.setName(resultSet.getString(GrantType.NAME_COLUMN_NAME));

    return clientGrantType;
  }

  private static class GrantTypeFactoryHolder {

    static final GrantTypeFactory INSTANCE = getInstance();

    static GrantTypeFactory getInstance() {
      return new GrantTypeFactory();
    }
  }
}
