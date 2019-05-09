package io.github.bbortt.qdrakeboo.core.graphql.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.core.graphql.account.AbstractAccountApiContextAwareTest;
import io.github.bbortt.qdrakeboo.core.graphql.starter.test.util.GraphQLTestUtil;

@Sql({"classpath:sql/AccountQueryIntTest.sql"})
public class AccountQueryIntTest extends AbstractAccountApiContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllAccountsReadsAccounts() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/getAllAccounts.graphql").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allAccounts\":[{\"accountname\":\"account-query-user\",\"email\":\"account-query-user@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"SERVER_SUPPORT\"}]}]}}");
  }
}
