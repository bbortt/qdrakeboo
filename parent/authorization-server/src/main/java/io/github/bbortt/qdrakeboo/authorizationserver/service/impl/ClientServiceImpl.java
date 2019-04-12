package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Client;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.ClientCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ClientService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;
import io.github.bbortt.qdrakeboo.authorizationserver.service.ScopeService;

@Service
public class ClientServiceImpl implements ClientService {

  private static final BCryptPasswordEncoder CLIENT_SECRET_ENCODER = new BCryptPasswordEncoder();

  private final ClientCRUDRepository clientCRUDRepository;
  private final AuthorityService authorityService;
  private final GrantTypeService grantTypeService;
  private final ScopeService scopeService;

  public ClientServiceImpl(ClientCRUDRepository clientRepository, AuthorityService authorityService,
      GrantTypeService grantTypeService, ScopeService scopeService) {
    this.clientCRUDRepository = clientRepository;
    this.authorityService = authorityService;
    this.grantTypeService = grantTypeService;
    this.scopeService = scopeService;
  }

  @Override
  public List<Client> getClients() {
    List<Client> clients = new ArrayList<>();

    clientCRUDRepository.findAll().forEach(clients::add);

    return clients;
  }
}
