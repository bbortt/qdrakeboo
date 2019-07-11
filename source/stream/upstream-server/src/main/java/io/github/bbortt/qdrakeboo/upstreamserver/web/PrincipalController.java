package io.github.bbortt.qdrakeboo.upstreamserver.web;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalController.class);

  @GetMapping("/principal")
  public Principal getPrincipal(Principal principal) {
    LOGGER.info("Current principal: {}", principal);

    return principal;
  }
}
