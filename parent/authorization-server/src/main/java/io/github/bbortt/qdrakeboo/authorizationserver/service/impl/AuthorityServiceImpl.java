package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AuthorityCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  private final AuthorityCRUDRepository authorityCRUDRepository;

  public AuthorityServiceImpl(AuthorityCRUDRepository authorityCRUDRepository) {
    this.authorityCRUDRepository = authorityCRUDRepository;
  }

  @Override
  public List<Authority> getAuthorities() {
    List<Authority> authorities = new ArrayList<>();

    authorityCRUDRepository.findAll().forEach(authorities::add);

    return authorities;
  }

  @Cacheable(cacheNames = {Authority.CACHE_NAME}, key = "#result.uuid")
  private Authority findByUUID(UUID uuid) {
    if (uuid == null) {
      throw new IllegalArgumentException("Cannot find an Authority without an id!");
    }

    return authorityCRUDRepository.findById(uuid).orElseThrow(
        () -> new IllegalArgumentException("Cannot find Authority by id '" + uuid + "'"));
  }

  @Override
  @Cacheable(cacheNames = {Authority.CACHE_NAME}, key = "#result.uuid")
  public Authority findByName(String name) {
    Optional<Authority> optionalAuthority = authorityCRUDRepository.findByName(name);

    if (!optionalAuthority.isPresent()) {
      throw new IllegalArgumentException("Authority '" + name + "' does not exist!");
    }

    return optionalAuthority.get();
  }

  @Override
  @Transactional
  @CachePut(cacheNames = {Authority.CACHE_NAME}, key = "#result.uuid")
  public Authority saveNewAuthority(Authority authority) {
    if (authority.getUuid() != null) {
      throw new IllegalArgumentException("Cannot save an existing Authority!");
    }

    return authorityCRUDRepository.save(authority);
  }

  @Override
  @Transactional
  @CachePut(cacheNames = {Authority.CACHE_NAME}, key = "#result.uuid")
  public Authority updateAuthority(Authority authority) {
    Authority existingAuthority = findByUUID(authority.getUuid());

    if (authority.getName() != null) {
      existingAuthority.setName(authority.getName());
    }

    return authorityCRUDRepository.save(existingAuthority);
  }

  @Override
  @CacheEvict(cacheNames = {Authority.CACHE_NAME}, key = "#result.uuid")
  public Authority deleteByUUID(UUID uuid) {
    Authority existingAuthority = findByUUID(uuid);

    authorityCRUDRepository.delete(existingAuthority);

    return existingAuthority;
  }
}
