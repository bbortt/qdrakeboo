package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import java.util.UUID;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;

public interface AuthorityService {

  List<Authority> getAuthorities();

  Authority findByName(String name);

  Authority saveNewAuthority(Authority authority);

  Authority updateAuthority(Authority authority);

  Authority deleteByUUID(UUID uuid);
}
