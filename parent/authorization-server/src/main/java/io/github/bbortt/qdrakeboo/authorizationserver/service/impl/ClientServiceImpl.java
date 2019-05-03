package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

@Service
public class ClientServiceImpl implements ClientService {

  private static final BCryptPasswordEncoder CLIENT_SECRET_ENCODER = new BCryptPasswordEncoder();

  private final Predicate<Authority> doesAuthorityExist;

  private final ClientCRUDRepository clientCRUDRepository;
  private final AuthorityService authorityService;
  private final GrantTypeService grantTypeService;
  private final ScopeService scopeService;

  public ClientServiceImpl(ClientCRUDRepository clientCRUDRepository,
      AuthorityService authorityService, GrantTypeService grantTypeService,
      ScopeService scopeService) {
    this.clientCRUDRepository = clientCRUDRepository;
    this.authorityService = authorityService;
    this.grantTypeService = grantTypeService;
    this.scopeService = scopeService;

    this.doesAuthorityExist = (Authority authority) -> {
      try {
        authorityService.findByName(authority.getName());
        return true;
      } catch (IllegalArgumentException e) {
        return false;
      }
    };
  }

  @Override
  public List<Client> getClients() {
    List<Client> clients = new ArrayList<>();

    clientCRUDRepository.findAll().forEach(clients::add);

    return clients;
  }

  @Override
  public Client saveNewClient(Client client) {
    if (client.getUuid() != null) {
      throw new IllegalArgumentException("Cannot save an existing Client!");
    }

    client.setSecret(CLIENT_SECRET_ENCODER.encode(client.getSecret()));

    client.getAuthorities().stream().filter(doesAuthorityExist.negate())
        .forEach(authorityService::saveNewAuthority);

    client.setAuthorities(client.getAuthorities().stream()
        .map(authority -> authorityService.findByName(authority.getName()))
        .collect(Collectors.toList()));
    client.setGrantTypes(client.getGrantTypes().stream()
        .map(grantType -> grantTypeService.findByName(grantType.getName()))
        .collect(Collectors.toList()));
    client.setScopes(client.getScopes().stream()
        .map(scope -> scopeService.findByName(scope.getName())).collect(Collectors.toList()));

    return clientCRUDRepository.save(client);
  }
}
