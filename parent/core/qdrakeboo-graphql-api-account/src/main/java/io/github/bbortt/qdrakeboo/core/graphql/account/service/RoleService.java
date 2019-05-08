package io.github.bbortt.qdrakeboo.core.graphql.account.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.model.account.Role;

public interface RoleService {

  List<Role> getRoles();

  Role findByName(String name);
}
