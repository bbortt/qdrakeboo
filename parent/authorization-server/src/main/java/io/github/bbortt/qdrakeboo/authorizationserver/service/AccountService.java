package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;

public interface AccountService {

  List<Account> getAccounts();

  Account getCurrentAccount();

  Account saveNewAccount(Account account);
}
