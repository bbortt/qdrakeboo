package io.github.bbortt.tv.core.authorizationserver.config.userdetails;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final AccountRepository userRepository;

  public UserDetailsServiceImpl(AccountRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Account> user;
    if (!(user = userRepository.findOneByUsername(username)).isPresent()) {
      throw new UsernameNotFoundException("Username '" + username + "' not found!");
    }

    return new UserDetailsImpl(user.get());
  }
}
