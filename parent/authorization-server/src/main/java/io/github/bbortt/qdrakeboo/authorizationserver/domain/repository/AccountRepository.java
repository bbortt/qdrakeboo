package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;

import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;

public interface AccountRepository {

  public Optional<Account> findOneByAccountname(String username);
}
