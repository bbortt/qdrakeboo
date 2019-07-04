package io.github.qdrakeboo.federation.movie.api;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava.FederationDirectives;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GraphQLSchemaConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(GraphQLSchemaConfiguration.class);

  @Bean
  GraphQLSchema schema(@Value("classpath:schema.graphql") Resource sdl) throws IOException {
    // Load our schema definition from resources
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(
        new String(Files.readAllBytes(sdl.getFile().toPath())));

    // Declare Apollo Federation directives (we use @key)
    typeRegistry.addAll(FederationDirectives.allDefinitions);

    // Build the base schema, which includes directives like @base and @extends
    final GraphQLSchema baseSchema = new SchemaGenerator()
        .makeExecutableSchema(typeRegistry, RuntimeWiring.newRuntimeWiring()
            .build());

    if (logger.isDebugEnabled()) {
      logger.debug("Base schema: {}", baseSchema);
    }

    // Transform the schema to add federation support.
    // It exposes the IDL of the base schema in `query{_service{idl}}`
    // and adds entity support.
    final GraphQLSchema federatedSchema =
        Federation.transform(baseSchema)
            .fetchEntities(env -> null)
            .resolveEntityType(env -> null)
            .build();

    if (logger.isDebugEnabled()) {
      logger.debug("Federated schema: {}", federatedSchema);
    }

    return federatedSchema;
  }
}
