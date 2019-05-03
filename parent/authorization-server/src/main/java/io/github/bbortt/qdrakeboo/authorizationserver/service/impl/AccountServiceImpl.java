package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

@Service
public class AccountServiceImpl implements AccountService {

  private static final String DEFAULT_ROLE_NAME = "USER";
  private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private final AccountCRUDRepository accountCRUDRepository;
  private final RoleService roleService;

  public AccountServiceImpl(AccountCRUDRepository accountRepository, RoleService roleService) {
    this.accountCRUDRepository = accountRepository;
    this.roleService = roleService;
  }

  @Override
  public List<Account> getAccounts() {
    List<Account> accounts = new ArrayList<>();

    accountCRUDRepository.findAll().forEach(accounts::add);

    return accounts;
  }

  @PreAuthorize("isAuthenticated()")
  public Account getCurrentAccount() {
    String accountName = SecurityContextHolder.getContext().getAuthentication().getName();

    return accountCRUDRepository.findOneByAccountnameIgnoreCase(accountName)
        .orElseThrow(() -> new IllegalArgumentException(
            "Cannot find account for accountname '" + accountName + "'!"));
  }

  @Override
  @Transactional
  public Account saveNewAccount(Account account) {
    if (account.getUuid() != null) {
      throw new IllegalArgumentException("Cannot save an existing Account!");
    }

    if (!account.getPassword().equals(account.getConfirmation())) {
      throw new IllegalArgumentException("Password and confirmation do not match!");
    }

    account.setEnabled(true);
    account.setPassword(PASSWORD_ENCODER.encode(account.getPassword()));

    if (account.getRoles().isEmpty()) {
      account.setRoles(Collections.singletonList(roleService.findByName(DEFAULT_ROLE_NAME)));
    } else {
      account.setRoles(account.getRoles().stream()
          .map(role -> roleService.findByName(role.getName())).collect(Collectors.toList()));
    }

    return accountCRUDRepository.save(account);
  }
}
