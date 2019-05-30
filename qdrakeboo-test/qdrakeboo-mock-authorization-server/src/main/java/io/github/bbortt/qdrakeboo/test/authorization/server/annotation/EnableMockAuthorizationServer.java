package io.github.bbortt.qdrakeboo.test.authorization.server.annotation;

import io.github.bbortt.qdrakeboo.test.authorization.server.MockAuthenticationManagerConfiguration;
import io.github.bbortt.qdrakeboo.test.authorization.server.MockAuthorizationServerConfiguration;
import io.github.bbortt.qdrakeboo.test.authorization.server.MockUserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MockAuthenticationManagerConfiguration.class, MockAuthorizationServerConfiguration.class,
    MockUserDetailsServiceImpl.class})
public @interface EnableMockAuthorizationServer {

}
