package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ScopeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

@Service
public class ScopeServiceImpl implements ScopeService {

  private final ScopeCRUDRepository scopeCRUDRepository;

  public ScopeServiceImpl(ScopeCRUDRepository roleCRUDRepository) {
    this.scopeCRUDRepository = roleCRUDRepository;
  }

  @Override
  public List<Scope> getScopes() {
    List<Scope> roles = new ArrayList<>();

    scopeCRUDRepository.findAll().forEach(roles::add);

    return roles;
  }

  @Override
  @Cacheable(cacheNames = {Scope.CACHE_NAME}, key = "#result.uuid")
  public Scope findByName(String name) {
    Optional<Scope> optionalScope = scopeCRUDRepository.findByName(name);

    if (!optionalScope.isPresent()) {
      throw new IllegalArgumentException("Scope '" + name + "' does not exist!");
    }

    return optionalScope.get();
  }
}
