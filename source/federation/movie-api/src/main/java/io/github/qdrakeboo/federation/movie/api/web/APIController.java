package io.github.qdrakeboo.federation.movie.api.web;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  @GetMapping(value = "/api/public", produces = "application/json")
  public String publicEndpoint(Principal principal) {
    return "Hello from a public endpoint! Your principal: " + principal;
  }

  @GetMapping(value = "/api/private", produces = "application/json")
  public String privateEndpoint(Principal principal) {
    return "Hello from a private endpoint! Your principal: " + principal;
  }

  @GetMapping(value = "/api/private-scoped", produces = "application/json")
  public String privateScopedEndpoint(Principal principal) {
    return "Hello from a scoped endpoint! Your principal: " + principal;
  }
}
