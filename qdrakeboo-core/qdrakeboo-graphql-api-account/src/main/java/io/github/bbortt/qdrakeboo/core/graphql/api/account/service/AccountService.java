package io.github.bbortt.qdrakeboo.core.graphql.api.account.service;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import java.util.List;

public interface AccountService {

  List<Account> getAccounts();

  Account getCurrentAccount();

  Account saveNewAccount(Account account);
}
