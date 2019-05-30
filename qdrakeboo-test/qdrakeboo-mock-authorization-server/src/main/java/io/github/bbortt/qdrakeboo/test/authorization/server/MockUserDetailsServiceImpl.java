package io.github.bbortt.qdrakeboo.test.authorization.server;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MockUserDetailsServiceImpl implements UserDetailsService {

  private final List<User> users;

  public MockUserDetailsServiceImpl() {
    users = createUsersList();
  }

  private List<User> createUsersList() {
    User guest = new User("guest", "guest", true, true,
        true, true, AuthorityUtils.createAuthorityList("GUEST"));
    User user = new User("user", "user", true, true,
        true, true, AuthorityUtils.createAuthorityList("USER"));
    User premiumUser = new User("premium_user", "premium_user", true, true,
        true, true, AuthorityUtils.createAuthorityList("PREMIUM_USER"));
    User contentSupport = new User("content_support", "content_support", true, true,
        true, true, AuthorityUtils.createAuthorityList("CONTENT_SUPPORT"));
    User contentAdmin = new User("content_admin", "content_admin", true, true,
        true, true, AuthorityUtils.createAuthorityList("CONTENT_ADMIN"));
    User serverSupport = new User("server_support", "server_support", true, true,
        true, true, AuthorityUtils.createAuthorityList("SERVER_SUPPORT"));
    User serverAdmin = new User("server_admin", "server_admin", true, true,
        true, true, AuthorityUtils.createAuthorityList("SERVER_ADMIN"));
    User gandalf = new User("gandalf", "gandalf", true, true,
        true, true, AuthorityUtils.createAuthorityList("GANDALF"));

    return Arrays
        .asList(guest, user, premiumUser, contentSupport, contentAdmin, serverSupport, serverAdmin,
            gandalf);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return users.stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown username '" + username + "'!"));
  }
}
