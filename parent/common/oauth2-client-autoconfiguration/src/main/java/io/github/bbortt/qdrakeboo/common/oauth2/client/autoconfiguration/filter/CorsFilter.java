package io.github.bbortt.qdrakeboo.common.oauth2.client.autoconfiguration.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpServletResponse.setHeader("Access-Control-Allow-Methods",
        "OPTIONS, GET, POST, PUT, DELETE");
    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
    httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization");

    if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(request, response);
    }
  }

  public void init(FilterConfig filterConfig) throws ServletException {}

  public void destroy() {}
}
