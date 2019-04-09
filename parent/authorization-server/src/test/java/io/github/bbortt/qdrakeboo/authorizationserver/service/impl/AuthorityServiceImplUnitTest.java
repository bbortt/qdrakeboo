package io.github.bbortt.qdrakeboo.authorizationserver.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.stereotype.Service;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.AuthorityCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.AuthorityService;

public class AuthorityServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  AuthorityCRUDRepository authorityCRUDRepositoryMock;

  AuthorityServiceImpl fixture;

  @Before
  public void beforeTestSetup() {
    doAnswer(new Answer<Authority>() {
      @Override
      public Authority answer(InvocationOnMock invocation) throws Throwable {
        return invocation.getArgument(0);
      }
    }).when(authorityCRUDRepositoryMock).save(Mockito.any(Authority.class));

    fixture = new AuthorityServiceImpl(authorityCRUDRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(AuthorityServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(AuthorityService.class).isAssignableFrom(AuthorityServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new AuthorityServiceImpl(authorityCRUDRepositoryMock))
        .hasFieldOrPropertyWithValue("authorityCRUDRepository", authorityCRUDRepositoryMock);
  }

  @Test
  public void getAuthoritiesTransformsRepositoryResult() {
    Authority authority = new Authority();
    Iterable<Authority> authorities = Collections.singletonList(authority);
    doReturn(authorities).when(authorityCRUDRepositoryMock).findAll();

    assertThat(fixture.getAuthorities()).containsExactly(authority);

    verify(authorityCRUDRepositoryMock).findAll();
  }

  @Test
  public void findByNameQueriesRepository() {
    String nameParameter = "name-parameter";

    Authority authority = new Authority();
    doReturn(Optional.of(authority)).when(authorityCRUDRepositoryMock).findByName(nameParameter);

    assertThat(fixture.findByName(nameParameter)).isEqualTo(authority);

    verify(authorityCRUDRepositoryMock).findByName(Mockito.eq(nameParameter));
  }

  @Test
  public void findByNameThrowsErrorIfNoRoleFound() {
    String nameParameter = "name-parameter";

    doReturn(Optional.empty()).when(authorityCRUDRepositoryMock).findByName(nameParameter);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Authority 'name-parameter' does not exist!");
    fixture.findByName(nameParameter);
  }

  @Test
  public void saveNewAuthorityChecksForExistingUUID() {
    Authority authority = new Authority();
    authority.setUuid(UUID.fromString("262ff7b0-a2d8-493a-80ba-c4f98dc3894e"));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot save an existing Authority!");
    fixture.saveNewAuthority(authority);
  }

  @Test
  public void saveNewAuthoritySavesAuthority() {
    Authority authority = new Authority();

    fixture.saveNewAuthority(authority);

    verify(authorityCRUDRepositoryMock).save(Mockito.eq(authority));
  }

  @Test
  public void updateAuthorityThrowsErrorIfNullUUID() {
    Authority authority = new Authority();

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot find an Authority without an id!");
    fixture.updateAuthority(authority);
  }

  @Test
  public void updateAuthorityThrowsErrorIfUnexisting() {
    UUID uuid = UUID.fromString("5b0d8bb2-f478-4d0c-9904-c22b2db645cc");
    doReturn(Optional.empty()).when(authorityCRUDRepositoryMock).findById(Mockito.eq(uuid));

    Authority authority = new Authority();
    authority.setUuid(uuid);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot find Authority by id '" + uuid + "'");
    fixture.updateAuthority(authority);
  }

  @Test
  public void updateAuthorityUpdatesEntity() {
    UUID uuid = UUID.fromString("cc86eb2d-b4e1-48ef-95a2-45f4e0955138");
    String newName = "new-name";

    Authority authority = new Authority();
    authority.setUuid(uuid);
    authority.setName("old-name");
    doReturn(Optional.of(authority)).when(authorityCRUDRepositoryMock).findById(Mockito.eq(uuid));

    Authority updatedAuthority = new Authority();
    updatedAuthority.setUuid(uuid);
    updatedAuthority.setName(newName);

    assertThat(fixture.updateAuthority(updatedAuthority)).hasFieldOrPropertyWithValue("name",
        newName);

    verify(authorityCRUDRepositoryMock).save(Mockito.eq(updatedAuthority));
  }

  @Test
  public void deleteByUUIDThrowsErrorIfNullUUID() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot find an Authority without an id!");
    fixture.deleteByUUID(null);
  }

  @Test
  public void deleteByUUIDThrowsErrorIfUnexisting() {
    UUID uuid = UUID.fromString("6ff1bad4-31ea-4e2b-9766-1b87f43e55f4");
    doReturn(Optional.empty()).when(authorityCRUDRepositoryMock).findById(Mockito.eq(uuid));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Cannot find Authority by id '" + uuid + "'");
    fixture.deleteByUUID(uuid);
  }

  @Test
  public void deleteByUUIDDeletesExistingEntity() {
    UUID uuid = UUID.fromString("c2bdd375-fb9e-4495-aaff-ed3aec956453");

    Authority authority = new Authority();
    doReturn(Optional.of(authority)).when(authorityCRUDRepositoryMock).findById(Mockito.eq(uuid));

    fixture.deleteByUUID(uuid);

    verify(authorityCRUDRepositoryMock).delete(authority);
  }
}
