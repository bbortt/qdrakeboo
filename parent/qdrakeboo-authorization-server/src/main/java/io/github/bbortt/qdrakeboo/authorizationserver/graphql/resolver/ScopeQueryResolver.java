package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

@Component
public class ScopeQueryResolver implements GraphQLQueryResolver {

  private final ScopeService scopeService;

  public ScopeQueryResolver(ScopeService scopeService) {
    this.scopeService = scopeService;
  }

  public List<Scope> getAllScopes() {
    return scopeService.getScopes();
  }
}
