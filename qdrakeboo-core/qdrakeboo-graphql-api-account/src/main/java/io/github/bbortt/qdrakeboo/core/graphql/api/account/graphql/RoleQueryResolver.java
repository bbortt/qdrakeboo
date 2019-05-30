package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Role;
import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Component;

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
