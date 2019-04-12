package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/GrantTypeQueryIntTest.sql"})
public class GrantTypeQueryIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllGrantTypesReadsGrantTypes() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/getAllGrantTypes.graphql")
        .withAuthentication("grant-type-query-user", "grant-type-query-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allGrantTypes\":[{\"name\":\"authorization_code\"},{\"name\":\"refresh_token\"},{\"name\":\"implicit\"},{\"name\":\"password\"}]}}");
  }
}
