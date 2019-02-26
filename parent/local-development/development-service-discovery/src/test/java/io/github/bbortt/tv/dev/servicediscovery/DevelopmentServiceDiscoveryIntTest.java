package io.github.bbortt.tv.dev.servicediscovery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DevelopmentServiceDiscovery.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class DevelopmentServiceDiscoveryIntTest {

  @Test
  public void contextLoads() {

  }
}
