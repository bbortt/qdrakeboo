package io.github.bbortt.qdrakeboo.authorizationserver.graphql.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.UUID;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

public class AuthorityMutationResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  AuthorityService authorityServiceMock;

  AuthorityMutationResolver fixture;

  @Before
  public void beforeTestSetup() {
    fixture = new AuthorityMutationResolver(authorityServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AuthorityMutationResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLMutationResolver.class).isAssignableFrom(AuthorityMutationResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AuthorityMutationResolver(authorityServiceMock))
        .hasFieldOrPropertyWithValue("authorityService", authorityServiceMock);
  }

  @Test
  public void newAuthorityCallsService() {
    Authority authority = new Authority();

    fixture.newAuthority(authority);

    verify(authorityServiceMock).saveNewAuthority(authority);
  }

  @Test
  public void updateAuthorityCallsService() {
    Authority authority = new Authority();

    fixture.updateAuthority(authority);

    verify(authorityServiceMock).updateAuthority(authority);
  }

  @Test
  public void deleteAuthorityCallsService() {
    UUID uuid = UUID.fromString("43ba1f16-9a95-4407-9b4d-8a66a9cd18dd");

    Authority authority = new Authority();
    authority.setUuid(uuid);
    doReturn(authority).when(authorityServiceMock).deleteByUUID(uuid);

    assertThat(fixture.deleteAuthority(uuid)).isEqualTo(uuid);

    verify(authorityServiceMock).deleteByUUID(uuid);
  }
}
