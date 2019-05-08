package io.github.bbortt.qdrakeboo.core.graphql.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import io.github.bbortt.qdrakeboo.core.graphql.account.AbstractAccountApiContextAwareTest;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.AccountHasRolesCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.model.account.Account;
import io.github.bbortt.qdrakeboo.model.account.Role;
import io.github.bbortt.qdrakeboo.model.account.association.accounthasrole.AccountHasRoleId;

@Sql({"classpath:sql/AccountMutationIntTest.sql"})
public class AccountMutationIntTest extends AbstractAccountApiContextAwareTest {

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
        graphQLTestUtil.post("graphql-tests/newAccount_withoutRoles.graphql").perform();

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
        graphQLTestUtil.post("graphql-tests/newAccount_withRoles.graphql").perform();

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
