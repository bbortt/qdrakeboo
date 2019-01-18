package io.github.bbortt.tv.core.authorizationserver.config.clientdetails;

import java.util.Optional;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.ClientRepository;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

  private final ClientRepository clientRepository;

  public ClientDetailsServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    Optional<ClientDetails> user;
    if (!(user = clientRepository.findOneByClientId(clientId)).isPresent()) {
      throw new ClientRegistrationException("No client with id '" + clientId + "' registered!");
    }

    return user.get();
  }
}
