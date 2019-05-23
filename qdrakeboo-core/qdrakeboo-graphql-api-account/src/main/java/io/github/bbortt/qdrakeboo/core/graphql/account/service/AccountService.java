package io.github.bbortt.qdrakeboo.core.graphql.account.service;

import io.github.bbortt.qdrakeboo.core.graphql.account.domain.Account;
import java.util.List;

public interface AccountService {

  List<Account> getAccounts();

  Account getCurrentAccount();

  Account saveNewAccount(Account account);
}
