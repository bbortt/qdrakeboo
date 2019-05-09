package io.github.bbortt.qdrakeboo.authorizationserver.config.userdetails;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountCRUDRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final AccountCRUDRepository accountCRUDRepository;

  public UserDetailsServiceImpl(AccountCRUDRepository userRepository) {
    this.accountCRUDRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<Account> user = accountCRUDRepository.findOneByAccountnameIgnoreCase(username);

    if (!user.isPresent()) {
      throw new UsernameNotFoundException("Username '" + username + "' not found!");
    }

    return new UserDetailsImpl(user.get());
  }
}
