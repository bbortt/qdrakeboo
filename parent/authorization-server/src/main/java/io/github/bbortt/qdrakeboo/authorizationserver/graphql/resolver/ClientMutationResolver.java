package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;

@Component
public class ClientMutationResolver implements GraphQLMutationResolver {

  private final ClientService clientService;

  public ClientMutationResolver(ClientService clientService) {
    this.clientService = clientService;
  }

  public Client newClient(Client client) {
    return clientService.saveNewClient(client);
  }
}
