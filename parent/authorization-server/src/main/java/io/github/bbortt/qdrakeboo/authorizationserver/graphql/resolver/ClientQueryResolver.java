package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import java.util.List;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;

@Component
public class ClientQueryResolver implements GraphQLQueryResolver {

  private final ClientService clientService;

  public ClientQueryResolver(ClientService clientService) {
    this.clientService = clientService;
  }

  public List<Client> getAllClients() {
    return clientService.getClients();
  }
}
