package io.github.bbortt.qdrakeboo.authorizationserver.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootEndpointController {

  private static Map<String, Object> ENDPOINTS_INFORMATION_MAP = new HashMap<>();

  static {
    ENDPOINTS_INFORMATION_MAP.put("current_user_url", "user");
  }

  @GetMapping({"/"})
  public Map<Object, Object> rootEndpointsInformation(HttpServletRequest httpServletRequest) {
    String currentName = httpServletRequest.getRequestURL().toString();

    return new HashMap<>(RootEndpointController.ENDPOINTS_INFORMATION_MAP).entrySet().stream()
        .map(entry -> {
          entry.setValue(currentName + entry.getValue());
          return entry;
        }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
