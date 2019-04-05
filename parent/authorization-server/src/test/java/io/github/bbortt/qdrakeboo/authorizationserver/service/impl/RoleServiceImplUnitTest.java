package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

public class RoleServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  RoleCRUDRepository roleCRUDRepositoryMock;

  RoleServiceImpl fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new RoleServiceImpl(roleCRUDRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(RoleServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(RoleService.class).isAssignableFrom(RoleServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new RoleServiceImpl(roleCRUDRepositoryMock))
        .hasFieldOrPropertyWithValue("roleCRUDRepository", roleCRUDRepositoryMock);
  }

  @Test
  public void getRolesTransformsRepositoryResult() {
    Role role = new Role();
    Iterable<Role> roles = Collections.singletonList(role);

    doReturn(roles).when(roleCRUDRepositoryMock).findAll();

    assertThat(fixture.getRoles()).containsExactly(role);

    verify(roleCRUDRepositoryMock).findAll();
  }
}
