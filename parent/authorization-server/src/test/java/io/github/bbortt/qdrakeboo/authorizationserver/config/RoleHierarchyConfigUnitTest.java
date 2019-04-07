package io.github.bbortt.qdrakeboo.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

public class RoleHierarchyConfigUnitTest {

  @Test
  public void isAnnotated() {
    assertThat(RoleHierarchyConfig.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new RoleHierarchyConfig()).isNotNull();
  }

  @Test
  public void roleHierarchyReturnsRoleHierarchy() {
    assertThat(new RoleHierarchyConfig().roleHierarchy()).isInstanceOf(RoleHierarchyImpl.class);    
  }
}
