package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;
import io.github.bbortt.tv.core.authorizationserver.domain.Role;

public class AccountFactory implements EntityFactory<Account> {

  private final RoleFactory roleFactory;

  public static AccountFactory getInstance() {
    return AccountFactoryHolder.INSTANCE;
  }

  private AccountFactory() {
    roleFactory = RoleFactory.getInstance();
  }

  @Override
  public Account fromResultSet(ResultSet resultSet) throws SQLException {
    Account user = new Account();

    user.setId(resultSet.getLong(Account.ID_RESULT_NAME));

    user.setCreated(resultSet.getDate(Account.ACCOUNT_CREATED_RESULT_NAME));
    user.setLastUpdated(resultSet.getDate(Account.ACCOUNT_LAST_UPDATED_RESULT_NAME));

    user.setAccountname(resultSet.getString(Account.ACCOUNTNAME_RESULT_NAME));
    user.setEmail(resultSet.getString(Account.EMAIL_RESULT_NAME));
    user.setPassword(resultSet.getString(Account.PASSWORD_RESULT_NAME));
    user.setEnabled(resultSet.getBoolean(Account.IS_ENABLED_RESULT_NAME));
    user.setBlocked(resultSet.getBoolean(Account.IS_BLOCKED_RESULT_NAME));

    Set<Role> userRoles = new HashSet<Role>();
    userRoles.add(roleFactory.fromResultSet(resultSet));

    while (resultSet.next()) {
      userRoles.add(roleFactory.fromResultSet(resultSet));
    }

    user.setRoles(userRoles);

    return user;
  }

  private static class AccountFactoryHolder {

    static final AccountFactory INSTANCE = getInstance();

    static AccountFactory getInstance() {
      return new AccountFactory();
    }
  }
}
