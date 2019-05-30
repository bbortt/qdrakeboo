package io.github.bbortt.qdrakeboo.core.graphql.api.account;

import io.github.bbortt.qdrakeboo.test.authorization.server.annotation.EnableMockAuthorizationServer;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@EnableMockAuthorizationServer
@Import({GraphQLTestUtilConfiguration.class})
@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractApplicationContextAwareTest {

}
