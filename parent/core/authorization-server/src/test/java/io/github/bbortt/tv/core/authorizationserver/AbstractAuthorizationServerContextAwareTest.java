package io.github.bbortt.tv.core.authorizationserver;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractAuthorizationServerContextAwareTest {

}
