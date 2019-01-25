package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.Role;

public class RoleFactory implements EntityFactory<Role> {

  public static RoleFactory getInstance() {
    return RoleFactoryHolder.INSTANCE;
  }

  private RoleFactory() {

  }

  @Override
  public Role fromResultSet(ResultSet resultSet) throws SQLException {
    Role userRole = new Role();

    userRole.setId(resultSet.getLong(Role.ID_RESULT_NAME));
    
    userRole.setCreated(resultSet.getDate(Role.ROLE_CREATED_COLUMN_NAME));
    userRole.setLastUpdated(resultSet.getDate(Role.ROLE_LAST_UPDATED_RESULT_NAME));

    userRole.setName(resultSet.getString(Role.NAME_RESULT_NAME));

    return userRole;
  }

  private static class RoleFactoryHolder {

    static final RoleFactory INSTANCE = getInstance();

    static RoleFactory getInstance() {
      return new RoleFactory();
    }
  }
}
