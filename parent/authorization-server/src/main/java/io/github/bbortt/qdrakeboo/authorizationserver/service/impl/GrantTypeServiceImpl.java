package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.GrantTypeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;

@Service
public class GrantTypeServiceImpl implements GrantTypeService {

  private final GrantTypeCRUDRepository grantTypeCRUDRepository;

  public GrantTypeServiceImpl(GrantTypeCRUDRepository roleCRUDRepository) {
    this.grantTypeCRUDRepository = roleCRUDRepository;
  }

  @Override
  public List<GrantType> getGrantTypes() {
    List<GrantType> roles = new ArrayList<>();

    grantTypeCRUDRepository.findAll().forEach(roles::add);

    return roles;
  }

  @Override
  @Cacheable(cacheNames = {GrantType.CACHE_NAME}, key = "#result.uuid")
  public GrantType findByName(String name) {
    Optional<GrantType> optionalGrantType = grantTypeCRUDRepository.findByName(name);

    if (!optionalGrantType.isPresent()) {
      throw new IllegalArgumentException("GrantType '" + name + "' does not exist!");
    }

    return optionalGrantType.get();
  }
}
