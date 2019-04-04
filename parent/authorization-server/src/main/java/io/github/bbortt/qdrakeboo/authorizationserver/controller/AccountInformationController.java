package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Account;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AccountService;

@RestController
public class AccountInformationController {

  private static final Map<String, Object> ERROR_MESSAGE_MAP = new HashMap<>();

  static {
    ERROR_MESSAGE_MAP.put("message", "Requires authentication");
  }

  private final AccountService accountService;

  public AccountInformationController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping({"/user", "/me"})
  public Map<String, Object> getAccountInformation(Principal principal,
      HttpServletResponse httpServletResponse) {
    if (principal == null) {
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
      return ERROR_MESSAGE_MAP;
    }

    Account account = accountService.getCurrentAccount();

    Map<String, Object> accountInformation = new HashMap<>();
    accountInformation.put("login", account.getAccountname());
    accountInformation.put("email", account.getEmail());
    accountInformation.put("created_at", account.getCreated());
    accountInformation.put("updated_at", account.getLastUpdated());

    return accountInformation;
  }
}
