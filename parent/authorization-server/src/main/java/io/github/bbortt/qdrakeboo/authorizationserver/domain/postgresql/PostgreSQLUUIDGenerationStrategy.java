package io.github.bbortt.qdrakeboo.authorizationserver.domain.postgresql;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PostgreSQLUUIDGenerationStrategy implements IdentifierGenerator {

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    return ((Session) session).doReturningWork(connection -> {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select uuid_generate_v1()");
      while (resultSet.next()) {
        return (UUID) resultSet.getObject(1);
      }

      throw new IllegalArgumentException("Cannot generate new UUID!");
    });
  }
}
