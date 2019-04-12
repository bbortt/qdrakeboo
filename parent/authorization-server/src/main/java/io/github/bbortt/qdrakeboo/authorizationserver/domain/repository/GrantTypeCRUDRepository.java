package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.GrantType;

@Repository
public interface GrantTypeCRUDRepository extends CrudRepository<GrantType, UUID> {

  Optional<GrantType> findByName(String name);
}
