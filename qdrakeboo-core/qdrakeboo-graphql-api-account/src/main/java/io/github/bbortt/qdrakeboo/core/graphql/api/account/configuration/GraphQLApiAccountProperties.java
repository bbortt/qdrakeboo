package io.github.bbortt.qdrakeboo.core.graphql.api.account.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "graphql-api-account")
public class GraphQLApiAccountProperties {

  private Caching caching;

  public Caching getCaching() {
    return this.caching;
  }

  public static class Caching {

    private boolean enabled;

    public void setEnabled(boolean enabled) {

    }
  }
}
