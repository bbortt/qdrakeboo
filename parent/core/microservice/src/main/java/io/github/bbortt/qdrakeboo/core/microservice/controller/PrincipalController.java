package io.github.bbortt.qdrakeboo.core.microservice.controller;

import java.io.IOException;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

  @GetMapping(path = {"/principal"})
  @PreAuthorize("#oauth2.hasScope('read')")
  public Principal principal(Principal principal) throws IOException {
    return principal;
  }
}
