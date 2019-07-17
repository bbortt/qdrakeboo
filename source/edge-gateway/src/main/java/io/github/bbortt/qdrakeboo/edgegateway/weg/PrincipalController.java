package io.github.bbortt.qdrakeboo.edgegateway.weg;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

  @GetMapping("/principal")
  public Principal principal(Principal principal) {
    return principal;
  }
}
