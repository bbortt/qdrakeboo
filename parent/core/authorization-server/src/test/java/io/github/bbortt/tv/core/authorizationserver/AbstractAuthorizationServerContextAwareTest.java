package io.github.bbortt.tv.core.authorizationserver;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthorizationServer.class})
public abstract class AbstractAuthorizationServerContextAwareTest {

}
