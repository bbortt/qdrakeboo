package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

@RestController
public class RootEndpointController {

  private static final Logger LOGGER = LoggerFactory.getLogger(RootEndpointController.class);

  private static final String ALL_REQUESTS_ENDPOINTS = "ALL";

  private static Map<String, Map<String, Object>> endpointsInformationMap = new HashMap<>();

  static {
    Map<String, Object> allEndpoints = new HashMap<>();
    allEndpoints.put("current_user_url", "user");
    endpointsInformationMap.put(ALL_REQUESTS_ENDPOINTS, allEndpoints);

    Map<String, Object> serverSupportEndpoints = new HashMap<>();
    serverSupportEndpoints.put("administrative_interface", "admin");
    endpointsInformationMap.put("SERVER_SUPPORT", serverSupportEndpoints);
  }

  private final AccountService accountService;
  private final RoleHierarchy roleHierarchy;

  public RootEndpointController(AccountService accountService, RoleHierarchy roleHierarchy) {
    this.accountService = accountService;
    this.roleHierarchy = roleHierarchy;
  }

  @GetMapping({"/"})
  public Map<Object, Object> rootEndpointsInformation(HttpServletRequest httpServletRequest) {
    String currentName = httpServletRequest.getRequestURL().toString();

    Map<Object, Object> endpoints = new HashMap<>();

    try {
      Account currentAccount = accountService.getCurrentAccount();
      List<String> reachableAuthorities =
          roleHierarchy.getReachableGrantedAuthorities(currentAccount.getRoles()).stream()
              .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

      endpointsInformationMap.entrySet().stream()
          .filter(entry -> reachableAuthorities.contains(entry.getKey())).map(Entry::getValue)
          .map(Map::entrySet).flatMap(Set::stream).forEach(endpointInformation -> {
            endpoints.put(endpointInformation.getKey(), endpointInformation.getValue());
          });
    } catch (IllegalArgumentException e) {
      LOGGER.warn(e.getLocalizedMessage());
    } finally {
      endpointsInformationMap.get(ALL_REQUESTS_ENDPOINTS).forEach(endpoints::put);
    }

    return new HashMap<>(endpoints).entrySet().stream().map(entry -> {
      entry.setValue(currentName + entry.getValue());
      return entry;
    }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
