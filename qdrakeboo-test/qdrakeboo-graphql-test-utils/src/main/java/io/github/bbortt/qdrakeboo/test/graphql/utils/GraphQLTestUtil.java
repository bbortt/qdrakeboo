package io.github.bbortt.qdrakeboo.test.graphql.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.StreamUtils;

public class GraphQLTestUtil {

  private final String graphqlEndpoint;
  private final TestRestTemplate testRestTemplate;
  private final OAuth2RestTemplate oAuth2RestTemplate; // See: https://stackoverflow.com/questions/27864295/how-to-use-oauth2resttemplate

  private final ObjectMapper objectMapper;

  private String clientId;
  private String clientSecret;
  private String clientScope;

  private String getTokenEndpoint = "/oauth/token";

  public GraphQLTestUtil(String graphqlEndpoint, TestRestTemplate testRestTemplate) {
    this.graphqlEndpoint = graphqlEndpoint;
    this.testRestTemplate = testRestTemplate;

    this.objectMapper = new ObjectMapper();
  }

  public GraphQLTestUtil withClient(String clientId, String clientSecret) {
    return withClient(clientId, clientSecret, "test");
  }

  public GraphQLTestUtil withClient(String clientId, String clientSecret, String clientScope) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.clientScope = clientScope;
    return this;
  }

  public GraphQLTestUtil withGetTokenEndpoint(String getTokenEndpoint) {
    this.getTokenEndpoint = getTokenEndpoint;
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

    private String authorizationToken;

    private final String payload;
    private final ObjectNode variables;

    protected GraphQLPostRequest(String payload, ObjectNode variables) {
      this.payload = payload;
      this.variables = variables;
    }

    public GraphQLPostRequest withAuthentication(String username, String password) {
      Map<String, String> params = new HashMap<>();
      params.put("grant_type", "password");
      params.put("username", username);
      params.put("password", password);
      params.put("scope", clientScope);

      ResponseEntity<String> authorizationResponse = testRestTemplate
          .postForEntity(getTokenEndpoint, createAuthorizationRequest(username, password),
              String.class, params);

      this.authorizationToken = authorizationResponse.getBody();
      System.out.println("Authorization-Token: " + authorizationToken);

      return this;
    }

    private HttpEntity<Object> createAuthorizationRequest(String username, String password) {
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      httpHeaders.setBasicAuth(clientId, clientSecret);

      return new HttpEntity<>(httpHeaders);
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

      if (authorizationToken != null) {
        httpHeaders.setBearerAuth(authorizationToken);
      }

      try {
        return new HttpEntity<>(objectMapper.writeValueAsString(objectNode), httpHeaders);
      } catch (JsonProcessingException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
}
