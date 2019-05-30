package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.AccountService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryResolver implements GraphQLQueryResolver {

  private final AccountService accountService;

  public AccountQueryResolver(AccountService accountService) {
    this.accountService = accountService;
  }

  public List<Account> getAllAccounts() {
    return accountService.getAccounts();
  }
}
