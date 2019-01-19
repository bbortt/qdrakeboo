package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {

  public Optional<UserDetails> findOneByUsername(String username);
}
