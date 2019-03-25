package io.github.bbortt.qdrakeboo.authorizationserver.domain.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityFactory<T> {

  T fromResultSet(ResultSet resultSet) throws SQLException;
}
