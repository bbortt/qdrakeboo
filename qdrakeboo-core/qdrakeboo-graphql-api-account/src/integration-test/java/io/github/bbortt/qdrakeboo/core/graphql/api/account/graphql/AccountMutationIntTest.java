package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.AbstractApplicationContextAwareTest;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Role;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.association.accounthasrole.AccountHasRoleId;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.repository.AccountHasRolesCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.test.graphql.utils.GraphQLTestUtil;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@Sql({"classpath:sql/AccountMutationIntTest.sql"})
public class AccountMutationIntTest extends AbstractApplicationContextAwareTest {

  @Autowired
  AccountCRUDRepository accountCRUDRepository;

  @Autowired
  RoleCRUDRepository roleCRUDRepository;

  @Autowired
  AccountHasRolesCRUDRepository accountHasRolesCRUDRepository;

  @Autowired
  GraphQLTestUtil graphQLTestUtil;

  @Before
  public void beforeTestSetup() {
    graphQLTestUtil.withClient("testclient", "testsecret");
  }

  @Test
  public void newAccountSavesAccountWithoutRoles() throws Exception {
    ResponseEntity<String> response =
        graphQLTestUtil.post("graphql-tests/newAccount_withoutRoles.graphql")
            .withAuthentication("server_support", "server_support")
            .perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"io.github.bbortt.qdrakeboo.core.graphql.account-without-roles\",\"email\":\"io.github.bbortt.qdrakeboo.core.graphql.account-without-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"USER\"}]}}}");

    Account accountWithoutRoles =
        accountCRUDRepository.findOneByAccountnameIgnoreCase(
            "io.github.bbortt.qdrakeboo.core.graphql.account-without-roles")
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
            .withAuthentication("server_support", "server_support")
            .perform();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(
        "{\"data\":{\"newAccount\":{\"accountname\":\"io.github.bbortt.qdrakeboo.core.graphql.account-with-roles\",\"email\":\"io.github.bbortt.qdrakeboo.core.graphql.account-with-roles@bbortt.github.io\",\"enabled\":true,\"blocked\":false,\"roles\":[{\"name\":\"PREMIUM_USER\"}]}}}");

    Account accountWithRoles =
        accountCRUDRepository.findOneByAccountnameIgnoreCase(
            "io.github.bbortt.qdrakeboo.core.graphql.account-with-roles")
            .orElseThrow(() -> new IllegalArgumentException());

    Role premiumUser = roleCRUDRepository.findByName("PREMIUM_USER")
        .orElseThrow(() -> new IllegalArgumentException());

    assertThat(accountHasRolesCRUDRepository
        .findById(new AccountHasRoleId(accountWithRoles, premiumUser)).isPresent()).isTrue();
  }
}
