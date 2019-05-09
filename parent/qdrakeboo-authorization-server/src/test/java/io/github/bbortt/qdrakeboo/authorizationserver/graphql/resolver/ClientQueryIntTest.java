package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/ClientQueryIntTest.sql"})
public class ClientQueryIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllClientsReadsClients() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/getAllClients.graphql")
        .withAuthentication("client-query-user", "client-query-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allClients\":[{\"clientId\":\"8f1de566-beed-4b9a-9e1c-32b4b381e737\",\"resourceIds\":\"test-resource\",\"secretRequired\":true,\"autoApprove\":false,\"accessTokenValiditySeconds\":3600,\"refreshTokenValiditySeconds\":7200,\"redirectUris\":\"https://no.where\",\"authorities\":[{\"name\":\"client-test\"}],\"grantTypes\":[{\"name\":\"implicit\"}],\"scopes\":[{\"name\":\"read\"}]}]}}");
  }
}
