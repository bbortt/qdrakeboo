package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;

public interface AccountService {

  Account getCurrentAccount();

  List<Account> getAccounts();
}
