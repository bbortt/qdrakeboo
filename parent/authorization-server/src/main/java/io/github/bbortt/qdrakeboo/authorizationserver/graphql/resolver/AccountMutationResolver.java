package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

@Component
public class AccountMutationResolver implements GraphQLMutationResolver {

  private final AccountService accountService;

  public AccountMutationResolver(AccountService accountService) {
    this.accountService = accountService;
  }

  public Account newAccount(Account account) {
    // TODO: Check for id
    
    return accountService.saveNewAccount(account);
  }
}
