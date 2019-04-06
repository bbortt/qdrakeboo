package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;

public interface RoleService {

  List<Role> getRoles();

  Role findByName(String name);
}
