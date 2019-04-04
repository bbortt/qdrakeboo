package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleCRUDRepository roleCRUDRepository;

  public RoleServiceImpl(RoleCRUDRepository roleCRUDRepository) {
    this.roleCRUDRepository = roleCRUDRepository;
  }

  @Override
  @Cacheable({Role.CACHE_NAME})
  public List<Role> getRoles() {
    List<Role> roles = new ArrayList<>();

    for (Role role : roleCRUDRepository.findAll()) {
      roles.add(role);
    }

    return roles;
  }
}
