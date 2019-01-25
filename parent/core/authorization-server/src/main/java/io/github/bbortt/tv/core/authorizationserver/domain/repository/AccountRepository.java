package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;

public interface AccountRepository {

  public Optional<Account> findOneByAccountname(String username);
}
