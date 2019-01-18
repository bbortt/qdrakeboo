package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import io.github.bbortt.tv.core.authorizationserver.domain.User;
import io.github.bbortt.tv.core.authorizationserver.domain.UserRole;

public class UserFactory implements EntityFactory<User> {

  private final UserRoleFactory userRoleFactory;

  public static UserFactory getInstance() {
    return UserFactoryHolder.INSTANCE;
  }

  private UserFactory() {
    userRoleFactory = UserRoleFactory.getInstance();
  }

  @Override
  public User fromResultSet(ResultSet resultSet) throws SQLException {
    User user = new User();

    user.setCreated(resultSet.getDate(User.USER_CREATED_COLUMN_NAME));
    user.setLastUpdated(resultSet.getDate(User.USER_LAST_UPDATED_COLUMN_NAME));

    user.setUsername(resultSet.getString(User.USERNAME_COLUMN_NAME));
    user.setEmail(resultSet.getString(User.EMAIL_COLUMN_NAME));
    user.setPassword(resultSet.getString(User.PASSWORD_COLUMN_NAME));
    user.setEnabled(resultSet.getBoolean(User.IS_ENABLED_COLUMN_NAME));
    user.setBlocked(resultSet.getBoolean(User.IS_BLOCKED_COLUMN_NAME));

    Set<UserRole> userRoles = new HashSet<>();
    userRoles.add(userRoleFactory.fromResultSet(resultSet));
    while (resultSet.next()) {
      userRoles.add(userRoleFactory.fromResultSet(resultSet));
    }
    user.setRoles(userRoles);

    return user;
  }

  private static class UserFactoryHolder {

    static final UserFactory INSTANCE = getInstance();

    static UserFactory getInstance() {
      return new UserFactory();
    }
  }
}
