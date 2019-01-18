package io.github.bbortt.tv.core.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityFactory<T> {

  T fromResultSet(ResultSet resultSet) throws SQLException;
}
