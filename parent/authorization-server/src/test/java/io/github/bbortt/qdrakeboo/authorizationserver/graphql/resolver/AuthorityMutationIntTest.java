package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AuthorityCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/AuthorityMutationIntTest.sql"})
public class AuthorityMutationIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  AuthorityCRUDRepository authorityCRUDRepository;

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void newAuthoritySavesAuthority() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/newAuthority.graphql")
        .withAuthentication("authority-mutation-user", "authority-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .isEqualTo("{\"data\":{\"newAuthority\":{\"name\":\"a-new-authority\"}}}");

    authorityCRUDRepository.findByName("a-new-authority")
        .orElseThrow(() -> new IllegalArgumentException());
  }

  @Test
  public void updateAuthorityUpdatesExistingAuthority() throws Exception {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/updateAuthority.graphql")
        .withAuthentication("authority-mutation-user", "authority-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"updateAuthority\":{\"uuid\":\"ebe7611f-652e-4fa5-91fa-474be380488a\",\"name\":\"updated-name\"}}}");

    authorityCRUDRepository.findByName("updated-name")
        .orElseThrow(() -> new IllegalArgumentException());
  }

  @Test
  public void deleteAuthorityRemovesAuthority() throws IOException {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/deleteAuthority.graphql")
        .withAuthentication("authority-mutation-user", "authority-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .isEqualTo("{\"data\":{\"deleteAuthority\":\"ebe7611f-652e-4fa5-91fa-474be380488a\"}}");

    assertThat(authorityCRUDRepository.findByName("to-delete").isPresent()).isFalse();
  }
}
