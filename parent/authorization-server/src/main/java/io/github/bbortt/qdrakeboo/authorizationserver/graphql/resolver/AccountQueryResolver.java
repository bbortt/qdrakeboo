package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

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
