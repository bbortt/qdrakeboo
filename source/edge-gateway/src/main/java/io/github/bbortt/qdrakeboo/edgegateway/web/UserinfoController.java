package io.github.bbortt.qdrakeboo.edgegateway.web;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserinfoController {

  @GetMapping("/userinfo")
  public Principal getUserInfo(Principal principal) {
    return principal;
  }
}
