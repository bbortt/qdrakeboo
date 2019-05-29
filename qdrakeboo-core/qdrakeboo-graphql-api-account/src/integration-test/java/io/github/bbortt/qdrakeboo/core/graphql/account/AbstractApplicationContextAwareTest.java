package io.github.bbortt.qdrakeboo.core.graphql.account;

import io.github.bbortt.qdrakeboo.test.graphql.utils.GraphQLTestUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractApplicationContextAwareTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Value("${graphql.servlet.mapping:/graphql}")
  private String loginEndpoint;

  @Bean
  protected GraphQLTestUtil graphQLTestUtil() {
    return new GraphQLTestUtil(loginEndpoint, testRestTemplate);
  }
}
