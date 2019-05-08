package io.github.bbortt.qdrakeboo.core.graphql.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.core.graphql.account.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.core.graphql.account.service.RoleService;
import io.github.bbortt.qdrakeboo.model.account.Role;

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
    Optional<Role> optionalRole = roleCRUDRepository.findByName(name);

    if (!optionalRole.isPresent()) {
      throw new IllegalArgumentException("Role '" + name + "' does not exist!");
    }

    return optionalRole.get();
  }
}
