package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import io.github.bbortt.qdrakeboo.authorizationserver.AbstractAuthorizationServerContextAwareTest;

public class RootEndpointControllerIntTest extends AbstractAuthorizationServerContextAwareTest {

  private static final String ROOT_ENDPOINT = "/";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithAnonymousUser
  public void rootEndpointReturnsUrlsForAnonymousUser() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get(ROOT_ENDPOINT)).andDo(print())
        .andExpect(status().is(HttpStatus.OK.value())).andReturn();

    assertThat(mvcResult.getResponse().getContentAsString())
        .isEqualTo("{\"current_user_url\":\"http://localhost/user\"}");
  }

  @Test
  @WithMockUser(username = "gandalf-user", authorities = "GANDALF")
  @Sql({"classpath:sql/RootEndpointControllerIntTest_rootEndpointReturnsUrlsForGandalf.sql"})
  public void rootEndpointReturnsUrlsForGandalf() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get(ROOT_ENDPOINT)).andDo(print())
        .andExpect(status().is(HttpStatus.OK.value())).andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(
        "{\"administrative_interface\":\"http://localhost/admin\",\"current_user_url\":\"http://localhost/user\"}");
  }
}
