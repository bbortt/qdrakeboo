package io.github.bbortt.qdrakeboo.authorizationserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource")
  public HikariDataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Configuration
  @Profile("dev")
  public static class DevDataSourceConfig {

    @Bean
    @Profile("no-redis")
    @ConfigurationProperties("tokenstore.datasource")
    public HikariDataSource jdbcTokenStoreDatasource() {
      return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
  }
}
