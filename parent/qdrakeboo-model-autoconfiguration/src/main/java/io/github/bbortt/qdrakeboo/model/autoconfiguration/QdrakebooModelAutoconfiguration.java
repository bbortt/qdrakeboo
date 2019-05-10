package io.github.bbortt.qdrakeboo.model.autoconfiguration;

import io.github.bbortt.qdrakeboo.model.autoconfiguration.configuration.AuditingEntityConfiguration;
import io.github.bbortt.qdrakeboo.model.autoconfiguration.configuration.ModelConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AuditingEntityConfiguration.class, ModelConfiguration.class})
public class QdrakebooModelAutoconfiguration {

}
