package io.github.bbortt.qdrakeboo.core.graphql.api.account.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Account;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Role;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.repository.AccountCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.AccountService;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.RoleService;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
  AccountCRUDRepository accountCRUDRepositoryMock;

  @Mock
  RoleService roleServiceMock;

  AccountServiceImpl fixture;

  Role defaultRole;

  @Before
  public void beforeTestSetup() {
    doReturn(authenticationMock).when(securityContextMock).getAuthentication();
    SecurityContextHolder.setContext(securityContextMock);

    defaultRole = new Role();
    defaultRole.setName("USER");
    doReturn(defaultRole).when(roleServiceMock).findByName("USER");

    doAnswer(new Answer<Account>() {
      @Override
      public Account answer(InvocationOnMock invocation) throws Throwable {
        return invocation.getArgument(0);
      }
    }).when(accountCRUDRepositoryMock).save(Mockito.any(Account.class));

    fixture = new AccountServiceImpl(accountCRUDRepositoryMock, roleServiceMock);
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
  public void constructorAcceptsArguments() {
    assertThat(new AccountServiceImpl(accountCRUDRepositoryMock, roleServiceMock))
        .hasFieldOrPropertyWithValue("accountCRUDRepository", accountCRUDRepositoryMock)
        .hasFieldOrPropertyWithValue("roleService", roleServiceMock);
  }

  @Test
  public void getAccountsTransformsRepositoryResult() {
    Account account = new Account();
    Iterable<Account> accounts = Collections.singletonList(account);

    doReturn(accounts).when(accountCRUDRepositoryMock).findAll();

    assertThat(fixture.getAccounts()).containsExactly(account);

    verify(accountCRUDRepositoryMock).findAll();
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
    String currentAccountname = "this-is-an-io.github.bbortt.qdrakeboo.core.graphql.account-name";
    Account expectedAccount = new Account();

    doReturn(currentAccountname).when(authenticationMock).getName();
    doReturn(Optional.of(expectedAccount)).when(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.eq(currentAccountname));

    assertThat(fixture.getCurrentAccount()).isEqualTo(expectedAccount);
    verify(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.eq(currentAccountname));
  }

  @Test
  public void getCurrentAccountThrowsIllegalArgumentExceptionOnUnknownAccount() {
    String currentAccountname = "unexisting-accountname";
    doReturn(currentAccountname).when(authenticationMock).getName();

    doReturn(Optional.empty()).when(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.anyString());

    expectedException.expect(IllegalArgumentException.class);
    expectedException
        .expectMessage(
            "Cannot find io.github.bbortt.qdrakeboo.core.graphql.account for accountname '"
                + currentAccountname + "'!");
    fixture.getCurrentAccount();

    verify(accountCRUDRepositoryMock)
        .findOneByAccountnameIgnoreCase(Mockito.eq(currentAccountname));
  }

  @Test
  public void saveNewAccountChecksForExistingUUID() {
    Account account = new Account();
    account.setUuid(UUID.fromString("2ab702b6-0134-4a92-bf4d-0d57ef9a4aca"));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot save an existing Account!");
    fixture.saveNewAccount(account);
  }

  @Test
  public void saveNewAccountChecksPasswordMatch() {
    String password = "password";
    String confirmation = "confirmation";

    Account account = new Account();
    account.setPassword(password);
    account.setConfirmation(confirmation);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Password and confirmation do not match!");
    fixture.saveNewAccount(account);
  }

  @Test
  public void saveNewAccountHashesPassword() {
    String password = "password";

    Account account = new Account();
    account.setPassword(password);
    account.setConfirmation(password);

    assertThat(fixture.saveNewAccount(account).getPassword()).isNotEqualTo(password);
  }

  @Test
  public void saveNewAccountAddsDefaultRole() {
    String password = "password";

    Account account = new Account();
    account.setPassword(password);
    account.setConfirmation(password);

    assertThat(fixture.saveNewAccount(account).getRoles()).containsExactly(defaultRole);
  }

  @Test
  public void saveNewAccountCompletesEntity() {
    String password = "password";
    String roleName = "roleName";

    Account account = new Account();
    account.setPassword(password);
    account.setConfirmation(password);

    Role parameterRole = new Role();
    parameterRole.setName(roleName);
    account.setRoles(Collections.singletonList(parameterRole));
    doReturn(parameterRole).when(roleServiceMock).findByName(roleName);

    Account newAccount = fixture.saveNewAccount(account);

    verify(accountCRUDRepositoryMock).save(Mockito.eq(account));

    assertThat(newAccount.isEnabled()).isTrue();
    assertThat(newAccount.getRoles()).containsExactly(parameterRole);
  }
}
