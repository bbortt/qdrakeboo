package io.github.bbortt.qdrakeboo.core.graphql.account.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.model.account.Role;

@Repository
public interface RoleCRUDRepository extends CrudRepository<Role, UUID> {

  Optional<Role> findByName(String name);
}
