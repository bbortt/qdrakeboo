package io.github.bbortt.tv.core.userapi.controller;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootEndpointPrincipalController {

  @GetMapping(path = {"/"})
  @PreAuthorize("#oauth2.hasScope('read')")
  public Principal principal(Principal principal) {
    return principal;
  }
}
