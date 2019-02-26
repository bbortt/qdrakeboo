package io.github.bbortt.tv.dev.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DevelopmentServiceDiscovery {

  public static void main(String[] args) {
    SpringApplication.run(DevelopmentServiceDiscovery.class, args);
  }
}
