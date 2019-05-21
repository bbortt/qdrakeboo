package io.github.bbortt.qdrakeboo.core.graphql.account.graphql;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.core.graphql.account.service.RoleService;
import io.github.bbortt.qdrakeboo.model.account.Role;

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
