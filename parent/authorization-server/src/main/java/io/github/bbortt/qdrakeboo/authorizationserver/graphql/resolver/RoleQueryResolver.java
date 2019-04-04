package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

@Component
public class RoleQueryResolver implements GraphQLQueryResolver {

  private final RoleService roleService;

  public RoleQueryResolver(RoleService roleService) {
    this.roleService = roleService;
  }

  public List<Role> getAllRoles() {
    return roleService.getRoles();
  }
}
