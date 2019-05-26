package io.github.bbortt.qdrakeboo.core.graphql.starter.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.github.bbortt.qdrakeboo.core.graphql.starter.test.util.GraphQLTestUtil;

@Configuration
@Import({GraphQLTestUtil.class})
public class GraphQLStarterTestAutoconfiguration {

}
