package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/AccountQueryIntTest.sql"})
public class AccountQueryIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllAccountsReadsAccounts() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/getAllAccounts.graphql")
        .withAuthentication("account-query-user", "account-query-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allAccounts\":[{\"accountname\":\"account-query-user\",\"email\":\"account-query-user@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"SERVER_SUPPORT\"}]}]}}");
  }
}
