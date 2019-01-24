package io.github.bbortt.tv.core.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.AccountRepository;
import io.github.bbortt.tv.core.authorizationserver.service.AccountService;

public class AccountServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  SecurityContext securityContextMock;

  @Mock
  Authentication authenticationMock;

  @Mock
  AccountRepository accountRepositoryMock;

  AccountServiceImpl fixture;

  @Before
  public void beforeTestSetup() {
    doReturn(authenticationMock).when(securityContextMock).getAuthentication();
    SecurityContextHolder.setContext(securityContextMock);

    fixture = new AccountServiceImpl(accountRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AccountServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(AccountService.class).isAssignableFrom(AccountServiceImpl.class);
  }

  @Test
  public void constructorAcceptsAccountRepository() {
    assertThat(new AccountServiceImpl(accountRepositoryMock))
        .hasFieldOrPropertyWithValue("accountRepository", accountRepositoryMock);
  }

  @Test
  public void getCurrentAccountRequiresAuthentication()
      throws NoSuchMethodException, SecurityException {
    Method getCurrentAccount = AccountServiceImpl.class.getDeclaredMethod("getCurrentAccount");
    assertThat(getCurrentAccount.getDeclaredAnnotation(PreAuthorize.class).value())
        .isEqualTo("isAuthenticated()");
  }

  @Test
  public void getCurrentAccountReturnsEntity() {
    String currentAccountname = "this-is-an-account-name";
    Account expectedAccount = new Account();

    doReturn(currentAccountname).when(authenticationMock).getName();
    doReturn(Optional.of(expectedAccount)).when(accountRepositoryMock)
        .findOneByAccount(Mockito.eq(currentAccountname));

    assertThat(fixture.getCurrentAccount()).isEqualTo(expectedAccount);
    verify(accountRepositoryMock).findOneByAccount(Mockito.eq(currentAccountname));
  }

  @Test
  public void getCurrentAccountThrowsIllegalArgumentExceptionOnUnknownAccount() {
    String currentAccountname = "unexisting-accountname";
    doReturn(currentAccountname).when(authenticationMock).getName();

    doReturn(Optional.empty()).when(accountRepositoryMock).findOneByAccount(Mockito.anyString());

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot find account for '" + currentAccountname + "'!");
    fixture.getCurrentAccount();

    verify(accountRepositoryMock).findOneByAccount(Mockito.eq(currentAccountname));
  }
}
