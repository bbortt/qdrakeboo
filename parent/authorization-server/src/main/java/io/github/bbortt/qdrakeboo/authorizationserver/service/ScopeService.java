package io.github.bbortt.qdrakeboo.authorizationserver.service;

import java.util.List;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;

public interface ScopeService {

  List<Scope> getScopes();

  Scope findByName(String name);
}
