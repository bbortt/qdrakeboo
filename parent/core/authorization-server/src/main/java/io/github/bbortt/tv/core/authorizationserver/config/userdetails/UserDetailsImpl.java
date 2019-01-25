package io.github.bbortt.tv.core.authorizationserver.config.userdetails;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Account account;

  public UserDetailsImpl(Account account) {
    this.account = account;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return account.getRoles();
  }

  @Override
  public String getPassword() {
    return account.getPassword();
  }

  @Override
  public String getUsername() {
    return account.getAccountname();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !account.isBlocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return account.isEnabled();
  }
}
