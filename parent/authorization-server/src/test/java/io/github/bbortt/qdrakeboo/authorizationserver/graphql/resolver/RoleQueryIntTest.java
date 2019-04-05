package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

public class RoleQueryIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllRolesReadsRoles() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/getAllRoles.graphql");
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allRoles\":[{\"name\":\"GANDALF\"},{\"name\":\"SERVER_ADMIN\"},{\"name\":\"SERVER_SUPPORT\"},{\"name\":\"CONTENT_ADMIN\"},{\"name\":\"CONTENT_SUPPORT\"},{\"name\":\"PREMIUM_USER\"},{\"name\":\"USER\"},{\"name\":\"GUEST\"}]}}");
  }
}
