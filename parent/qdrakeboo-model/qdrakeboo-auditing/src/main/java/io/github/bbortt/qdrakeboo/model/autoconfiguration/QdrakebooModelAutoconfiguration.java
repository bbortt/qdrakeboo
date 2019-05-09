package io.github.bbortt.qdrakeboo.model.autoconfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.github.bbortt.qdrakeboo.model.autoconfiguration.configuration.AuditingEntityConfiguration;
import io.github.bbortt.qdrakeboo.model.autoconfiguration.configuration.ModelConfiguration;

@Configuration
@Import({AuditingEntityConfiguration.class, ModelConfiguration.class})
public class QdrakebooModelAutoconfiguration {

}
