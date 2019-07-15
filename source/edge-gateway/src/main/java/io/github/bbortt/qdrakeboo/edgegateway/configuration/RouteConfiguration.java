package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile({"!dev & !test"})
@PropertySource({"config/route.properties"})
public class RouteConfiguration {

}
