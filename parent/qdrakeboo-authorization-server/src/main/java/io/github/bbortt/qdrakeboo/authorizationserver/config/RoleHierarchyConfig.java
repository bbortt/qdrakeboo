package io.github.bbortt.qdrakeboo.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
    roleHierarchyImpl.setHierarchy(
        "GANDALF > SERVER_ADMIN > SERVER_SUPPORT > CONTENT_ADMIN > CONTENT_SUPPORT > PREMIUM_USER > USER > GUEST > ANONYMOUS");
    return roleHierarchyImpl;
  }
}
