package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.AbstractApplicationContextAwareTest;
import io.github.bbortt.qdrakeboo.test.graphql.utils.GraphQLTestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RoleQueryIntTest extends AbstractApplicationContextAwareTest {

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void getAllRolesReadsRoles() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/getAllRoles.graphql").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"allRoles\":[{\"name\":\"GANDALF\"},{\"name\":\"SERVER_ADMIN\"},{\"name\":\"SERVER_SUPPORT\"},{\"name\":\"CONTENT_ADMIN\"},{\"name\":\"CONTENT_SUPPORT\"},{\"name\":\"PREMIUM_USER\"},{\"name\":\"USER\"},{\"name\":\"GUEST\"}]}}");
  }
}
