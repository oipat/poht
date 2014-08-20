package org.tapiok.blogi.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tapiok.blogi.model.User;

@RepositoryRestResource(path = "users", itemResourceRel = "user", collectionResourceRel = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
   // User findByUsername(String username);
}
