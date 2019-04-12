package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;

@Component
public class GrantTypeQueryResolver implements GraphQLQueryResolver {

  private final GrantTypeService grantTypeService;

  public GrantTypeQueryResolver(GrantTypeService grantTypeService) {
    this.grantTypeService = grantTypeService;
  }

  public List<GrantType> getAllGrantTypes() {
    return grantTypeService.getGrantTypes();
  }
}
