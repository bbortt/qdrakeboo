package io.github.bbortt.qdrakeboo.test.authorization.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MockUserDetailsServiceConfiguration {

  @Bean
  public UserDetailsService userDetailsService() {
    UserBuilder userBuilder = User.withDefaultPasswordEncoder();
    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    inMemoryUserDetailsManager
        .createUser(userBuilder.username("guest").password("guest").roles("GUEST").build());
    inMemoryUserDetailsManager
        .createUser(userBuilder.username("user").password("user").roles("USER").build());
    inMemoryUserDetailsManager.createUser(
        userBuilder.username("premium_user").password("premium_user").roles("PREMIUM_USER")
            .build());
    inMemoryUserDetailsManager.createUser(
        userBuilder.username("content_support").password("content_support").roles("CONTENT_SUPPORT")
            .build());
    inMemoryUserDetailsManager.createUser(
        userBuilder.username("content_admin").password("content_admin").roles("CONTENT_ADMIN")
            .build());
    inMemoryUserDetailsManager.createUser(
        userBuilder.username("server_support").password("server_support").roles("SERVER_SUPPORT")
            .build());
    inMemoryUserDetailsManager.createUser(
        userBuilder.username("server_admin").password("server_admin").roles("SERVER_ADMIN")
            .build());
    inMemoryUserDetailsManager
        .createUser(userBuilder.username("gandalf").password("gandalf").roles("GANDALF").build());

    return inMemoryUserDetailsManager;
  }
}
