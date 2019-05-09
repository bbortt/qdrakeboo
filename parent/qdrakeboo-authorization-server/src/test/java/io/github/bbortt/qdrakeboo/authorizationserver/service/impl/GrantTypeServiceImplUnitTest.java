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
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.repository.GrantTypeCRUDRepository;
import io.github.bbortt.qdrakeboo.authorizationserver.service.GrantTypeService;

public class GrantTypeServiceImplUnitTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  GrantTypeCRUDRepository grantTypeCRUDRepositoryMock;

  GrantTypeServiceImpl fixture;

  @Before
  public void beforeTestSetup() {

    fixture = new GrantTypeServiceImpl(grantTypeCRUDRepositoryMock);
  }

  @Test
  public void isAnnotated() {
    assertThat(GrantTypeServiceImpl.class).hasAnnotation(Service.class);
  }

  @Test
  public void implementsInterface() {
    assertThat(GrantTypeService.class).isAssignableFrom(GrantTypeServiceImpl.class);
  }

  @Test
  public void constructorAcceptsArguments() {
    assertThat(new GrantTypeServiceImpl(grantTypeCRUDRepositoryMock))
        .hasFieldOrPropertyWithValue("grantTypeCRUDRepository", grantTypeCRUDRepositoryMock);
  }

  @Test
  public void getGrantTypesTransformsRepositoryResult() {
    GrantType grantType = new GrantType();
    Iterable<GrantType> grantTypes = Collections.singletonList(grantType);
    doReturn(grantTypes).when(grantTypeCRUDRepositoryMock).findAll();

    assertThat(fixture.getGrantTypes()).containsExactly(grantType);

    verify(grantTypeCRUDRepositoryMock).findAll();
  }

  @Test
  public void findByNameQueriesRepository() {
    String nameParameter = "name-parameter";

    GrantType grantType = new GrantType();
    doReturn(Optional.of(grantType)).when(grantTypeCRUDRepositoryMock).findByName(nameParameter);

    assertThat(fixture.findByName(nameParameter)).isEqualTo(grantType);

    verify(grantTypeCRUDRepositoryMock).findByName(Mockito.eq(nameParameter));
  }

  @Test
  public void findByNameThrowsErrorIfNoGrantTypeFound() {
    String nameParameter = "name-parameter";

    doReturn(Optional.empty()).when(grantTypeCRUDRepositoryMock).findByName(nameParameter);

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("GrantType 'name-parameter' does not exist!");
    fixture.findByName(nameParameter);
  }
}
