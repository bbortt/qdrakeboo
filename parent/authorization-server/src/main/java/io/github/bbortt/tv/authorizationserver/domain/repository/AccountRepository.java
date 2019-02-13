package io.github.bbortt.tv.authorizationserver.domain.repository;

import java.util.Optional;
import io.github.bbortt.tv.authorizationserver.domain.Account;

public interface AccountRepository {

  public Optional<Account> findOneByAccountname(String username);
}
