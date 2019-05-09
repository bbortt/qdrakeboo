package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

public class RootEndpointControllerUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AccountService accountServiceMock;

  @Mock
  RoleHierarchy roleHierarchyMock;

  RootEndpointController fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new RootEndpointController(accountServiceMock, roleHierarchyMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(RootEndpointController.class).hasAnnotation(RestController.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new RootEndpointController(accountServiceMock, roleHierarchyMock))
        .hasFieldOrPropertyWithValue("accountService", accountServiceMock)
        .hasFieldOrPropertyWithValue("roleHierarchy", roleHierarchyMock);
  }

  @Test
  public void rootEndpointsInformationIsAnnotated()
      throws NoSuchMethodException, SecurityException {
    Method rootEndpointsInformation = RootEndpointController.class
        .getDeclaredMethod("rootEndpointsInformation", HttpServletRequest.class);

    assertThat(rootEndpointsInformation.getDeclaredAnnotation(GetMapping.class)).isNotNull();
  }

  @Test
  public void rootEndpointsInformationReturnsEndpoints() {
    String currentUrl = "this-is-the-current-url/";

    Map<Object, Object> exptectedMap = new HashMap<>();
    exptectedMap.put("current_user_url", currentUrl + "user");

    Account account = new Account();
    Role role = new Role();
    role.setName("USER");
    account.setRoles(Collections.singletonList(role));
    doReturn(account).when(accountServiceMock).getCurrentAccount();

    HttpServletRequest httpServletRequestMock = Mockito.mock(HttpServletRequest.class);
    doReturn(new StringBuffer(currentUrl)).when(httpServletRequestMock).getRequestURL();

    assertThat(fixture.rootEndpointsInformation(httpServletRequestMock)).isEqualTo(exptectedMap);
  }
}
