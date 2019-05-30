package io.github.bbortt.qdrakeboo.core.graphql.api.account;

import io.github.bbortt.qdrakeboo.test.graphql.utils.GraphQLTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLTestUtilConfiguration {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Value("${graphql.servlet.mapping:/graphql}")
  private String loginEndpoint;

  @Bean
  protected GraphQLTestUtil graphQLTestUtil() {
    return new GraphQLTestUtil(loginEndpoint, testRestTemplate);
  }
}
