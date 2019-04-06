package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.RoleCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.RoleService;

public class RoleServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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

  @Test
  public void findByNameQueriesRepository() {
    String nameParameter = "name-parameter";

    Role role = new Role();
    doReturn(Optional.of(role)).when(roleCRUDRepositoryMock).findByName(nameParameter);

    assertThat(fixture.findByName(nameParameter)).isEqualTo(role);

    verify(roleCRUDRepositoryMock).findByName(Mockito.eq(nameParameter));
  }

  @Test
  public void findByNameThrowsErrorIfNoRoleFound() {
    String nameParameter = "name-parameter";

    doReturn(Optional.empty()).when(roleCRUDRepositoryMock).findByName(nameParameter);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Role 'name-parameter' does not exist!");
    fixture.findByName(nameParameter);
  }
}
