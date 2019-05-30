package io.github.bbortt.qdrakeboo.core.graphql.api.account.repository;

import io.github.bbortt.qdrakeboo.core.graphql.api.account.domain.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCRUDRepository extends CrudRepository<Role, UUID> {

  Optional<Role> findByName(String name);
}
