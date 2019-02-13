package io.github.bbortt.tv.authorizationserver.config.clientdetails;

import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.authorizationserver.domain.Client;
import io.github.bbortt.tv.authorizationserver.domain.repository.ClientRepository;

@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {

  private final ClientRepository clientRepository;

  public ClientDetailsServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    Optional<Client> user;
    if (!(user = clientRepository.findOneByClientId(clientId)).isPresent()) {
      throw new ClientRegistrationException("No client with id '" + clientId + "' registered!");
    }

    return new ClientDetailsImpl(user.get());
  }
}
