package io.github.bbortt.tv.authorizationserver.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.bbortt.tv.authorizationserver.controller.RootEndpointController;

public class RootEndpointControllerUnitTest {

  RootEndpointController fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new RootEndpointController();
  }

  @Test
  public void isAnnotated() {
    assertThat(RootEndpointController.class).hasAnnotation(RestController.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new RootEndpointController()).isNotNull();
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

    HttpServletRequest httpServletRequestMock = Mockito.mock(HttpServletRequest.class);
    doReturn(new StringBuffer(currentUrl)).when(httpServletRequestMock).getRequestURL();

    assertThat(fixture.rootEndpointsInformation(httpServletRequestMock)).isEqualTo(exptectedMap);
  }
}
