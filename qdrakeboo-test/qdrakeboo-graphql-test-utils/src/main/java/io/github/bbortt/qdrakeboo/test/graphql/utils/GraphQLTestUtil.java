package io.github.bbortt.qdrakeboo.test.graphql.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

public class GraphQLTestUtil {

  private final String graphqlEndpoint;
  private final TestRestTemplate testRestTemplate;

  private final ObjectMapper objectMapper;

  private String loginEndpoint = "/login";
  private String sessionCookiePrefix = "SESSION=";

  public GraphQLTestUtil(String graphqlEndpoint, TestRestTemplate testRestTemplate) {
    this.graphqlEndpoint = graphqlEndpoint;
    this.testRestTemplate = testRestTemplate;

    this.objectMapper = new ObjectMapper();
  }

  public GraphQLTestUtil withLoginEndpoint(String loginEndpoint) {
    this.loginEndpoint = loginEndpoint;
    return this;
  }

  public GraphQLTestUtil withSessionCookiePrefix(String sessionCookiePrefix) {
    this.sessionCookiePrefix = sessionCookiePrefix;
    return this;
  }

  public GraphQLPostRequest post(String resourceLocation) throws IOException {
    return post(resourceLocation, objectMapper.createObjectNode());
  }

  public GraphQLPostRequest post(String resourceLocation, ObjectNode variables) throws IOException {
    String payload = loadResource(resourceLocation);

    return new GraphQLPostRequest(payload, variables);
  }

  private String loadResource(String resourceLocation) throws IOException {
    ClassPathResource classPathResource = new ClassPathResource(resourceLocation);
    try (InputStream inputStream = classPathResource.getInputStream()) {
      return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }
  }

  public class GraphQLPostRequest {

    private final String payload;
    private final ObjectNode variables;

    private String session;

    protected GraphQLPostRequest(String payload, ObjectNode variables) {
      this.payload = payload;
      this.variables = variables;
    }

    public GraphQLPostRequest withAuthentication(String username, String password) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
      map.add("username", username);
      map.add("password", password);

      ResponseEntity<String> loginResponse = testRestTemplate.postForEntity(loginEndpoint,
          new HttpEntity<MultiValueMap<String, String>>(map, headers), String.class);

      this.session = extractSessionCookieFromLoginResponse(loginResponse);

      return this;
    }

    private String extractSessionCookieFromLoginResponse(ResponseEntity<String> loginResponse) {
      return Arrays.stream(loginResponse.getHeaders().get(HttpHeaders.SET_COOKIE).get(0).split(";"))
          .filter(cookie -> cookie.startsWith(sessionCookiePrefix)).findFirst()
          .orElseThrow(() -> new IllegalArgumentException());
    }

    public ResponseEntity<String> perform() {
      return testRestTemplate.postForEntity(graphqlEndpoint, createHttpRequest(), String.class);
    }

    private HttpEntity<Object> createHttpRequest() {
      ObjectNode objectNode = objectMapper.createObjectNode();
      objectNode.put("query", payload);
      objectNode.set("variables", variables);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);

      if (session != null) {
        httpHeaders.set(HttpHeaders.COOKIE, session);
      }

      try {
        return new HttpEntity<Object>(objectMapper.writeValueAsString(objectNode), httpHeaders);
      } catch (JsonProcessingException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
}
