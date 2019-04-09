package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

@Component
public class AuthorityMutationResolver implements GraphQLMutationResolver {

  private final AuthorityService authorityService;

  public AuthorityMutationResolver(AuthorityService authorityService) {
    this.authorityService = authorityService;
  }

  public Authority newAuthority(Authority authority) {
    return authorityService.saveNewAuthority(authority);
  }

  public Authority updateAuthority(Authority authority) {
    return authorityService.updateAuthority(authority);
  }

  public UUID deleteAuthority(UUID uuid) {
    return authorityService.deleteByUUID(uuid).getUuid();
  }
}
