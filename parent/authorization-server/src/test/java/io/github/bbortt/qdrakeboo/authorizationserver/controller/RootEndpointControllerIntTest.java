package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;

public class RootEndpointControllerIntTest extends AbstractAuthorizationServerContextAwareTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void rootEndpointReturnsUrlsForAnonymousUser() {

  }

  @Test
  public void rootEndpointReturnsUrlsForGandalf() {

  }
}
