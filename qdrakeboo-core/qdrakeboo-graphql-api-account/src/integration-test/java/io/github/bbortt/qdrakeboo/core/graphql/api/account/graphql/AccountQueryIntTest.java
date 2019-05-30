package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.AbstractApplicationContextAwareTest;
import io.github.bbortt.qdrakeboo.test.graphql.utils.GraphQLTestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@Sql({"classpath:sql/AccountQueryIntTest.sql"})
public class AccountQueryIntTest extends AbstractApplicationContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllAccountsReadsAccounts() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/getAllAccounts.graphql").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allAccounts\":[{\"accountname\":\"io.github.bbortt.qdrakeboo.core.graphql.account-query-user\",\"email\":\"io.github.bbortt.qdrakeboo.core.graphql.account-query-user@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"SERVER_SUPPORT\"}]}]}}");
  }
}
