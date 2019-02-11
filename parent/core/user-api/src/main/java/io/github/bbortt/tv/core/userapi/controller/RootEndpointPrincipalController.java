package io.github.bbortt.tv.core.userapi.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootEndpointPrincipalController {

  @GetMapping
  public Principal principal(Principal principal) {
    return principal;
  }
}
