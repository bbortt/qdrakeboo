package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.association.accounthasroles.AccountHasRoleId;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountHasRolesCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.graphql.GraphQLTestUtil;

@Sql({"classpath:sql/AccountMutationIntTest.sql"})
public class AccountMutationIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  AccountCRUDRepository accountCRUDRepository;

  @Autowired
  RoleCRUDRepository roleCRUDRepository;

  @Autowired
  AccountHasRolesCRUDRepository accountHasRolesCRUDRepository;

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Test
  public void newAccountSavesAccountWithoutRoles() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/newAccount_withoutRoles.graphql")
            .withAuthentication("account-mutation-user", "account-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"account-without-roles\",\"email\":\"account-without-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"USER\"}]}}}");

    Account accountWithoutRoles =
        accountCRUDRepository.findOneByAccountnameIgnoreCase("account-without-roles")
            .orElseThrow(() -> new IllegalArgumentException());

    Role user =
        roleCRUDRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException());

    assertThat(accountHasRolesCRUDRepository
        .findById(new AccountHasRoleId(accountWithoutRoles, user)).isPresent()).isTrue();
  }

  @Test
  public void newAccountSavesAccountWithRoles() throws IOException {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/newAccount_withRoles.graphql")
            .withAuthentication("account-mutation-user", "account-mutation-password").perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"account-with-roles\",\"email\":\"account-with-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"PREMIUM_USER\"}]}}}");

    Account accountWithRoles =
        accountCRUDRepository.findOneByAccountnameIgnoreCase("account-with-roles")
            .orElseThrow(() -> new IllegalArgumentException());

    Role premiumUser = roleCRUDRepository.findByName("PREMIUM_USER")
        .orElseThrow(() -> new IllegalArgumentException());

    assertThat(accountHasRolesCRUDRepository
        .findById(new AccountHasRoleId(accountWithRoles, premiumUser)).isPresent()).isTrue();
  }
}
