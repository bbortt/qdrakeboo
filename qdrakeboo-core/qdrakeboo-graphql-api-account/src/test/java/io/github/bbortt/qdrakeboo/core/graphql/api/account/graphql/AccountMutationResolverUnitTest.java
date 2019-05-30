package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.AccountService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Component;

public class AccountMutationResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AccountService accountServiceMock;

  AccountMutationResolver fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AccountMutationResolver(accountServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AccountMutationResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLMutationResolver.class).isAssignableFrom(AccountMutationResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AccountMutationResolver(accountServiceMock))
        .hasFieldOrPropertyWithValue("accountService", accountServiceMock);
  }

  @Test
  public void newAccountSavesEntity() {
    Account account = new Account();

    fixture.newAccount(account);

    verify(accountServiceMock).saveNewAccount(account);
  }
}
