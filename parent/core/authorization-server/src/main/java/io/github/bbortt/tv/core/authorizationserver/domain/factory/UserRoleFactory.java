package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.bbortt.tv.core.authorizationserver.domain.UserRole;

public class UserRoleFactory implements EntityFactory<UserRole> {

  public static UserRoleFactory getInstance() {
    return UserRoleFactoryHolder.INSTANCE;
  }

  private UserRoleFactory() {

  }

  @Override
  public UserRole fromResultSet(ResultSet resultSet) throws SQLException {
    UserRole userRole = new UserRole();

    userRole.setCreated(resultSet.getDate(UserRole.USER_ROLE_CREATED_COLUMN_NAME));
    userRole.setLastUpdated(resultSet.getDate(UserRole.USER_ROLE_LAST_UPDATED_COLUMN_NAME));

    userRole.setName(resultSet.getString(UserRole.NAME_COLUMN_NAME));

    return userRole;
  }

  private static class UserRoleFactoryHolder {

    static final UserRoleFactory INSTANCE = getInstance();

    static UserRoleFactory getInstance() {
      return new UserRoleFactory();
    }
  }
}
