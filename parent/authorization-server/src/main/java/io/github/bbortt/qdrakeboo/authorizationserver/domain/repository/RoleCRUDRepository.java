package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Role;

@Repository
public interface RoleCRUDRepository extends CrudRepository<Role, UUID> {

  Optional<Role> findByName(String name);
}
