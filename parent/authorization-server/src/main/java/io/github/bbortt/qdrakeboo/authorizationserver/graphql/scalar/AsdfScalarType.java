package io.github.bbortt.qdrakeboo.authorizationserver.graphql.scalar;

import java.util.Date;
import org.springframework.stereotype.Component;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Component
public class AsdfScalarType extends GraphQLScalarType {

  public AsdfScalarType() {
    super("Asdf", "Asdf value", new Coercing<Date, String>() {

      @Override
      public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public Date parseValue(Object input) throws CoercingParseValueException {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public Date parseLiteral(Object input) throws CoercingParseLiteralException {
        // TODO Auto-generated method stub
        return null;
      }
    });
  }
}
