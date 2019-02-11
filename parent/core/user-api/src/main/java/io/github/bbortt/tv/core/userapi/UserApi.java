package io.github.bbortt.tv.core.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class UserApi {

  public static void main(String[] args) {
    SpringApplication.run(UserApi.class, args);
  }
}
