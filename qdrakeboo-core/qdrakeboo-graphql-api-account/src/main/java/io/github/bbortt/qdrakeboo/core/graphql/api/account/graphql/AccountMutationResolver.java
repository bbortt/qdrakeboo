package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountMutationResolver implements GraphQLMutationResolver {

  private final AccountService accountService;

  public AccountMutationResolver(AccountService accountService) {
    this.accountService = accountService;
  }

  public Account newAccount(Account account) {
    return accountService.saveNewAccount(account);
  }
}
