package io.github.bbortt.tv.core.authorizationserver.domain.repository.syntaxcheck;

import org.springframework.stereotype.Component;
import io.github.bbortt.tv.core.authorizationserver.domain.repository.ClientRepository;

@Component
public class ClientRepositorySyntaxCheck implements RepositorySyntaxCheck {

  private final ClientRepository clientRepository;

  public ClientRepositorySyntaxCheck(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public void checkSyntax() {
    clientRepository.findOneByClientId("");
  }
}
