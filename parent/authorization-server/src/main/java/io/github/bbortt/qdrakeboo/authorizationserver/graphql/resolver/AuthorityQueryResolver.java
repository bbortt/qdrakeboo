package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

@Component
public class AuthorityQueryResolver implements GraphQLQueryResolver {

  private final AuthorityService authorityService;

  public AuthorityQueryResolver(AuthorityService authorityService) {
    this.authorityService = authorityService;
  }

  public List<Authority> getAllAuthorities() {
    return authorityService.getAuthorities();
  }
}
