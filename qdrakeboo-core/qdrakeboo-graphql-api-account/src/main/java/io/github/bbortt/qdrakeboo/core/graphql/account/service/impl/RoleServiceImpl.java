package io.github.bbortt.qdrakeboo.core.graphql.account.service.impl;

import io.github.bbortt.qdrakeboo.core.graphql.account.domain.Role;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.account.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleCRUDRepository roleCRUDRepository;

  public RoleServiceImpl(RoleCRUDRepository roleCRUDRepository) {
    this.roleCRUDRepository = roleCRUDRepository;
  }

  @Override
  public List<Role> getRoles() {
    List<Role> roles = new ArrayList<>();

    roleCRUDRepository.findAll().forEach(roles::add);

    return roles;
  }

  @Override
  @Cacheable(cacheNames = {Role.CACHE_NAME}, key = "#result.uuid")
  public Role findByName(String name) {
    return roleCRUDRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("Role '" + name + "' does not exist!"));
  }
}
