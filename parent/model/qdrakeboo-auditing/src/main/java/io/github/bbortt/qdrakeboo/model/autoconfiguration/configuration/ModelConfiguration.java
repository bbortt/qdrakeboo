package io.github.bbortt.qdrakeboo.model.autoconfiguration.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"io.github.bbortt.qdrakeboo.model.*"})
public class ModelConfiguration {

}
