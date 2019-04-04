package io.github.bbortt.qdrakeboo.authorizationserver.domain.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.bbortt.qdrakeboo.authorizationserver.domain.Scope;

@Repository
public interface ScopeCRUDRepository extends CrudRepository<Scope, UUID> {

}
