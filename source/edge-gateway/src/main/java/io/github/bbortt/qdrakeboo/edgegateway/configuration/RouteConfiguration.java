package io.github.bbortt.qdrakeboo.edgegateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"${routes.path}"})
public class RouteConfiguration {

}
