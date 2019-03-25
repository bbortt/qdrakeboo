package io.github.bbortt.qdrakeboo.authorizationserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Method;
import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariDataSource;

import io.github.bbortt.qdrakeboo.authorizationserver.config.DataSourceConfig;
import io.github.bbortt.qdrakeboo.authorizationserver.config.DataSourceConfig.DevDataSourceConfig;

public class DataSourceConfigUnitTest {

  private static final String PRIMARY_DATASOURCE_PREFIX = "spring.datasource";
  private static final String JDBC_TOKEN_STORE_DATASOURCE_PREFIX = "tokenstore.datasource";

  @Test
  public void isAnnotated() {
    assertThat(DataSourceConfig.class).hasAnnotation(Configuration.class);
  }

  @Test
  public void hasPublicConstructor() {
    assertThat(new DataSourceConfig()).isNotNull();
  }

  @Test
  public void springDataSourceShouldBeAnnotatedAsPrimaryBean()
      throws NoSuchMethodException, SecurityException {
    assertThat(new DataSourceConfig().dataSource()).isInstanceOf(HikariDataSource.class);

    Method datasourceConfiguration = DataSourceConfig.class.getDeclaredMethod("dataSource");

    assertThat(datasourceConfiguration.getDeclaredAnnotation(Bean.class)).isNotNull();
    assertThat(datasourceConfiguration.getDeclaredAnnotation(Primary.class)).isNotNull();
    assertThat(datasourceConfiguration.getDeclaredAnnotation(ConfigurationProperties.class).value())
        .isEqualTo(PRIMARY_DATASOURCE_PREFIX);
  }

  @Test
  public void devDataSourceConfigHasPublicConstructor() {
    assertThat(new DevDataSourceConfig()).isNotNull();
  }

  @Test
  public void jdbcTokenStoreShouldBeAnnotatedNotToOccurInProductiveEnvironment()
      throws NoSuchMethodException, SecurityException {
    assertThat(new DevDataSourceConfig().jdbcTokenStoreDatasource())
        .isInstanceOf(HikariDataSource.class);

    Method jdbcTokenStoreDatasourceConfiguration =
        DevDataSourceConfig.class.getDeclaredMethod("jdbcTokenStoreDatasource");

    assertThat(jdbcTokenStoreDatasourceConfiguration.getDeclaredAnnotation(Bean.class)).isNotNull();
    assertThat(jdbcTokenStoreDatasourceConfiguration.getDeclaredAnnotation(Profile.class).value())
        .containsExactly("no-redis");
    assertThat(jdbcTokenStoreDatasourceConfiguration
        .getDeclaredAnnotation(ConfigurationProperties.class).value())
            .isEqualTo(JDBC_TOKEN_STORE_DATASOURCE_PREFIX);
  }
}
