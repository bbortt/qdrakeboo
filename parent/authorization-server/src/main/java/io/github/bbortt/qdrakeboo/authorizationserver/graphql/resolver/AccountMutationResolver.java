package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

@Component
public class AccountMutationResolver implements GraphQLMutationResolver {

  private Role defaultRole;
  
  private final AccountService accountService;
  private final RoleService roleService;

  public AccountMutationResolver(AccountService accountService, RoleService roleService) {
    this.accountService = accountService;
    this.roleService = roleService;

  this.defaultRole = getDefaultRole();
  }
  
  private Role getDefaultRole() {
    return roleService.findOneByName("USER");
  }

  public Account newAccount(Account account) {
List<Role> roles = new ArrayList<>();
    
    if (!account.getRoles().isEmpty()) {
roles.add(defaultRole);      
    }else {
      for (Role role : account.getRoles()) {
        roles.add(roleService.findOneByName(role.getName()))
      }
    }
    
    account.setRoles(roles);
  }
}
