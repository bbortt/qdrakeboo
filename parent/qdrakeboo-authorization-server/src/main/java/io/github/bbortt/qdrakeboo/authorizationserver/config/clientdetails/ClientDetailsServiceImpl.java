package io.github.bbortt.qdrakeboo.authorizationserver.config.clientdetails;

import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientCRUDRepository;

@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {

  private final ClientCRUDRepository clientCRUDRepository;

  public ClientDetailsServiceImpl(ClientCRUDRepository clientRepository) {
    this.clientCRUDRepository = clientRepository;
  }

  @Override
  public ClientDetails loadClientByClientId(String clientId) {
    Optional<Client> client = clientCRUDRepository.findOneByClientId(clientId);

    if (!client.isPresent()) {
      throw new ClientRegistrationException("No client with id '" + clientId + "' registered!");
    }

    return new ClientDetailsImpl(client.get());
  }
}
