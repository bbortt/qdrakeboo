package io.github.bbortt.qdrakeboo.authorizationserver;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthorizationServer.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractAuthorizationServerContextAwareTest {

}
