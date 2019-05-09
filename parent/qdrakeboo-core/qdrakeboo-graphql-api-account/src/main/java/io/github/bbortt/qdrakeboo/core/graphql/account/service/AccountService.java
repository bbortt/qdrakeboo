package io.github.bbortt.qdrakeboo.core.graphql.account.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.model.account.Account;

public interface AccountService {

  List<Account> getAccounts();

  Account getCurrentAccount();

  Account saveNewAccount(Account account);
}
