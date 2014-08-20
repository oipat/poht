package org.tapiok.blogi.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.tapiok.blogi.model.UserRole;

//@RepositoryRestResource(path = "roles", itemResourceRel = "role", collectionResourceRel = "roles")
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {
	   // User findByUsername(String username);
}

