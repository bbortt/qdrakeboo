package io.github.bbortt.tv.core.userapi.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootEndpointPrincipalController {

  @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
  public void corsHeaders(HttpServletResponse response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.addHeader("Access-Control-Allow-Headers",
        "origin, content-type, accept, x-requested-with");
    response.addHeader("Access-Control-Max-Age", "3600");
  }

  @GetMapping
  public Principal principal(Principal principal) {
    return principal;
  }
}
