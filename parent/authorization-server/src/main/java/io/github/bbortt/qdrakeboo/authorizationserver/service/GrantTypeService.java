package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

public interface GrantTypeService {

  List<GrantType> getGrantTypes();

  GrantType findByName(String name);
}
