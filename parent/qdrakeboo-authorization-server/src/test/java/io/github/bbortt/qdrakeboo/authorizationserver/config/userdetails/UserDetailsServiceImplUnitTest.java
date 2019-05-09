package io.github.bbortt.qdrakeboo.authorizationserver.config.userdetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AccountCRUDRepository;

public class UserDetailsServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  AccountCRUDRepository accountCRUDRepositoryMock;

  UserDetailsServiceImpl fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new UserDetailsServiceImpl(accountCRUDRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(UserDetailsServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(UserDetailsService.class).isAssignableFrom(UserDetailsServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new UserDetailsServiceImpl(accountCRUDRepositoryMock)).isNotNull()
        .hasFieldOrPropertyWithValue("accountCRUDRepository", accountCRUDRepositoryMock);
  }

  @Test
  public void loadUserByUsernameReturnsUserDetailsImpl() {
    String username = "this-is-a-username";

    Account account = new Account();
    doReturn(Optional.of(account)).when(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.eq(username));

    assertThat(fixture.loadUserByUsername(username)).isInstanceOf(UserDetailsImpl.class)
        .hasFieldOrPropertyWithValue("account", account);
  }

  @Test
  public void loadUserByUsernameThrowsUsernameNotFoundException() {
    String username = "this-is-a-nonexisting-username";

    doReturn(Optional.empty()).when(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.eq(username));

    expectedException.expect(UsernameNotFoundException.class);
    expectedException.expectMessage("Username '" + username + "' not found!");
    fixture.loadUserByUsername(username);
  }
}
