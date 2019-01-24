package io.github.bbortt.tv.core.authorizationserver.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import io.github.bbortt.tv.core.authorizationserver.AuthorizationServer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AuthorizationServer.class})
public abstract class AbstractAuthorizationServerContextAwareTest {

}
