package io.github.bbortt.tv.authorizationserver.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.authorizationserver.domain.Account;
import io.github.bbortt.tv.authorizationserver.domain.repository.AccountRepository;
import io.github.bbortt.tv.authorizationserver.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @PreAuthorize("isAuthenticated()")
  public Account getCurrentAccount() {
    String accountName = SecurityContextHolder.getContext().getAuthentication().getName();

    return accountRepository.findOneByAccountname(accountName).orElseThrow(
        () -> new IllegalArgumentException("Cannot find account for '" + accountName + "'!"));
  }
}
