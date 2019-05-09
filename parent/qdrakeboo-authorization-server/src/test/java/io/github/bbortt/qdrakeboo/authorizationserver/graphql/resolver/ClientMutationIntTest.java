package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasauthorities.ClientHasAuthorityId;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasgranttypes.ClientHasGrantTypeId;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.clienthasscopes.ClientHasScopeId;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AuthorityCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientHasAuthoritiesCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientHasGrantTypesCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientHasScopesCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.GrantTypeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ScopeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/ClientMutationIntTest.sql"})
public class ClientMutationIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  ClientCRUDRepository clientCRUDRepository;

  @Autowired
  AuthorityCRUDRepository authorityCRUDRepository;

  @Autowired
  GrantTypeCRUDRepository grantTypeCRUDRepository;

  @Autowired
  ScopeCRUDRepository scopeCRUDRepository;

  @Autowired
  ClientHasAuthoritiesCRUDRepository clientHasAuthoritiesCRUDRepository;

  @Autowired
  ClientHasGrantTypesCRUDRepository clientHasGrantTypesCRUDRepository;

  @Autowired
  ClientHasScopesCRUDRepository clientHasScopesCRUDRepository;

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void newAccountSavesAccountWithRoles() throws IOException {
    ResponseEntity<String> response = graphQLTestUtil.post("graphql-tests/newClient.graphql")
        .withAuthentication("client-mutation-user", "client-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newClient\":{\"clientId\":\"2eb16959-ac97-4e36-9a7a-5d51d230fae2\",\"resourceIds\":\"test-resource\",\"secretRequired\":true,\"autoApprove\":false,\"accessTokenValiditySeconds\":3600,\"refreshTokenValiditySeconds\":7200,\"redirectUris\":\"http://localhost/login\",\"authorities\":[{\"name\":\"client-test\"}],\"grantTypes\":[{\"name\":\"implicit\"}],\"scopes\":[{\"name\":\"read\"}]}}}");

    Client client = clientCRUDRepository.findOneByClientId("2eb16959-ac97-4e36-9a7a-5d51d230fae2")
        .orElseThrow(() -> new IllegalArgumentException());

    Authority authority = authorityCRUDRepository.findByName("client-test")
        .orElseThrow(() -> new IllegalArgumentException());

    assertThat(clientHasAuthoritiesCRUDRepository
        .findById(new ClientHasAuthorityId(client, authority)).isPresent()).isTrue();

    GrantType grantType = grantTypeCRUDRepository.findByName("implicit")
        .orElseThrow(() -> new IllegalArgumentException());

    assertThat(clientHasGrantTypesCRUDRepository
        .findById(new ClientHasGrantTypeId(client, grantType)).isPresent()).isTrue();

    Scope scope =
        scopeCRUDRepository.findByName("read").orElseThrow(() -> new IllegalArgumentException());

    assertThat(
        clientHasScopesCRUDRepository.findById(new ClientHasScopeId(client, scope)).isPresent())
            .isTrue();
  }
}
