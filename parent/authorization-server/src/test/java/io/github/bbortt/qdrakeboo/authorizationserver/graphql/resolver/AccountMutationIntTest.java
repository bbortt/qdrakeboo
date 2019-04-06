package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

public class AccountMutationIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  @Sql({"classpath:sql/AccountMutationIntTest_beforeTestSetup.sql"})
  public void newAccountSavesAccountWithoutRoles() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/newAccount_withoutRoles.graphql");
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"account-without-roles\",\"email\":\"account-without-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"USER\"}]}}}");
  }

  @Test
  @Sql({"classpath:sql/AccountMutationIntTest_beforeTestSetup.sql"})
  public void newAccountSavesAccountWithRoles() throws IOException {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/newAccount_withRoles.graphql");
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"account-with-roles\",\"email\":\"account-with-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"PREMIUM_USER\"}]}}}");
  }
}
