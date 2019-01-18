package io.github.bbortt.tv.core.authorizationserver.config.userdetails;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserDetails> user;
    if (!(user = userRepository.findOneByUsername(username)).isPresent()) {
      throw new UsernameNotFoundException("Username '" + username + "' not found!");
    }

    return user.get();
  }
}
