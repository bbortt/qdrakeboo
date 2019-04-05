package io.github.bbortt.qdrakeboo.authorizationserver.graphql;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class GraphQLTestUtil {

  @Value("${graphql.servlet.mapping:/graphql}")
  private String graphqlEndpoint;

  private final TestRestTemplate testRestTemplate;
  private final ObjectMapper objectMapper;

  public GraphQLTestUtil(TestRestTemplate testRestTemplate) {
    this.testRestTemplate = testRestTemplate;

    this.objectMapper = new ObjectMapper();
  }

  public ResponseEntity<String> post(String resourceLocation) throws IOException {
    return post(resourceLocation, objectMapper.createObjectNode());
  }

  public ResponseEntity<String> post(String resourceLocation, ObjectNode variables)
      throws IOException {
    String payload = loadResource(resourceLocation);

    HttpEntity<Object> request = createHttpRequest(payload, variables);

    return testRestTemplate.exchange(graphqlEndpoint, HttpMethod.POST, request, String.class);
  }

  private String loadResource(String resourceLocation) throws IOException {
    ClassPathResource classPathResource = new ClassPathResource(resourceLocation);
    try (InputStream inputStream = classPathResource.getInputStream()) {
      return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }
  }

  private HttpEntity<Object> createHttpRequest(String payload, ObjectNode variables)
      throws JsonProcessingException {
    ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("query", payload);
    objectNode.set("variables", variables);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<Object>(objectMapper.writeValueAsString(objectNode), httpHeaders);
  }
}
