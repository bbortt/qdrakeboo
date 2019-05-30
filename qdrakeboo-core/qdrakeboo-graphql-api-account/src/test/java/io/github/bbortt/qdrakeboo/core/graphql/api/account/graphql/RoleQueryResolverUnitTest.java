package io.github.bbortt.qdrakeboo.core.graphql.api.account.graphql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.service.RoleService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class RoleQueryResolverUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  RoleService roleServiceMock;

  RoleQueryResolver fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new RoleQueryResolver(roleServiceMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(RoleQueryResolver.class).hasAnnotation(Component.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GraphQLQueryResolver.class).isAssignableFrom(RoleQueryResolver.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new RoleQueryResolver(roleServiceMock)).hasFieldOrPropertyWithValue("roleService",
        roleServiceMock);
  }

  @Test
  public void getAllRolesCallsService() {
    fixture.getAllRoles();

    verify(roleServiceMock).getRoles();
  }
}
