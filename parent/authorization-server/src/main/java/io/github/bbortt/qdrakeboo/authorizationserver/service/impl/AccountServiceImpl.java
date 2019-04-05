package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountCRUDRepository accountCRUDRepository;

  public AccountServiceImpl(AccountCRUDRepository accountRepository) {
    this.accountCRUDRepository = accountRepository;
  }

  @PreAuthorize("isAuthenticated()")
  public Account getCurrentAccount() {
    String accountName = SecurityContextHolder.getContext().getAuthentication().getName();

    return accountCRUDRepository.findOneByAccountname(accountName).orElseThrow(
        () -> new IllegalArgumentException("Cannot find account for '" + accountName + "'!"));
  }

  @Override
  public List<Account> getAccounts() {
    List<Account> accounts = new ArrayList<Account>();

    accountCRUDRepository.findAll().forEach(accounts::add);

    return accounts;
  }
}
