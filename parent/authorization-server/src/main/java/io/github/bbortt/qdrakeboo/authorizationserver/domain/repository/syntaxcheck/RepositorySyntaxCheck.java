package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.syntaxcheck;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public interface RepositorySyntaxCheck extends ApplicationListener<ApplicationReadyEvent> {

  void checkSyntax();

  default void onApplicationEvent(ApplicationReadyEvent event) {
    checkSyntax();
  }
}
