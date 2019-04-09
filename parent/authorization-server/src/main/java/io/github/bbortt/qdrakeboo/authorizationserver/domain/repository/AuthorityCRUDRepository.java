package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Authority;

@Repository
public interface AuthorityCRUDRepository extends CrudRepository<Authority, UUID> {

  Optional<Authority> findByName(String name);
}
