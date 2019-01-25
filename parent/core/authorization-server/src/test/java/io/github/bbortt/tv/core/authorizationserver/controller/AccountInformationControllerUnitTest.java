package io.github.bbortt.tv.core.authorizationserver.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.bbortt.tv.core.authorizationserver.domain.Account;
import io.github.bbortt.tv.core.authorizationserver.service.AccountService;

public class AccountInformationControllerUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AccountService accountServiceMock;

  AccountInformationController fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AccountInformationController(accountServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AccountInformationController.class).hasAnnotation(RestController.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AccountInformationController(accountServiceMock)).isNotNull()
        .hasFieldOrPropertyWithValue("accountService", accountServiceMock);
  }

  @Test
  public void getAccountInformationIsAnnotated() throws NoSuchMethodException, SecurityException {
    Method getAccountInformation = AccountInformationController.class
        .getDeclaredMethod("getAccountInformation", Principal.class, HttpServletResponse.class);

    assertThat(getAccountInformation.getDeclaredAnnotation(GetMapping.class).value())
        .containsExactly("/user", "/me");
  }

  @Test
  public void getAccountInformationAccountInformationMap() {
    Principal principalMock = Mockito.mock(Principal.class);

    Date created = new Date();
    Date lastUpdated = new Date();
    String accountname = "this-is-an-accountname";
    String email = "this-is-an-email";

    Account account = new Account();
    account.setCreated(created);
    account.setLastUpdated(lastUpdated);
    account.setAccountname(accountname);
    account.setEmail(email);

    doReturn(account).when(accountServiceMock).getCurrentAccount();

    Map<String, Object> expectedMap = new HashMap<>();
    expectedMap.put("created_at", created);
    expectedMap.put("updated_at", lastUpdated);
    expectedMap.put("login", accountname);
    expectedMap.put("email", email);

    HttpServletResponse httpServletResponseSpy = Mockito.spy(HttpServletResponse.class);

    assertThat(fixture.getAccountInformation(principalMock, httpServletResponseSpy))
        .isEqualTo(expectedMap);
    verify(httpServletResponseSpy, never()).setStatus(Mockito.anyInt());
  }

  @Test
  public void getAccountInformationReturnsHTTP401IfNoPrincipalPresent() {
    Map<String, Object> expectedMap = new HashMap<>();
    expectedMap.put("message", "Requires authentication");;

    HttpServletResponse httpServletResponseSpy = Mockito.spy(HttpServletResponse.class);

    assertThat(fixture.getAccountInformation(null, httpServletResponseSpy)).isEqualTo(expectedMap);
    verify(httpServletResponseSpy).setStatus(Mockito.eq(401));
  }
}
