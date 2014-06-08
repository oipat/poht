package org.tapiok.blogi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tapiok.blogi.model.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
