package io.github.bbortt.qdrakeboo.core.graphql.account.graphql;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.core.graphql.account.service.AccountService;
import io.github.bbortt.qdrakeboo.model.account.Account;

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
