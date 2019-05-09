package io.github.bbortt.qdrakeboo.core.graphql.account.graphql;

import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.core.graphql.account.service.AccountService;
import io.github.bbortt.qdrakeboo.model.account.Account;

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
