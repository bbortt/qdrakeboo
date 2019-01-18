package io.github.bbortt.tv.core.authorizationserver.domain.repository;

import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import io.github.bbortt.tv.core.authorizationserver.config.userdetails.UserDetailsImpl;

@Repository
public class UserRepository {

  private final DataSource dataSource;

  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Optional<UserDetails> findOneByName(String username) {
    // TODO Auto-generated method stub
    return null;
  }
}
