package io.github.bbortt.qdrakeboo.upstreamserver.web;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HeaderController.class);

  @GetMapping("/headers")
  public HttpEntity<HttpStatus> readHeaders(HttpServletRequest httpServletRequest) {
    Iterator<String> headers = httpServletRequest.getHeaderNames().asIterator();

    while (headers.hasNext()) {
      String name = headers.next();
      String header = httpServletRequest.getHeader(name);
      LOGGER.info("header: { name: {}, value: {}}", name, header);
    }

    return new HttpEntity<>(HttpStatus.OK);
  }
}
