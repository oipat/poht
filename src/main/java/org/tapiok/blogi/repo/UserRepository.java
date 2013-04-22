/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tapiok.blogi.model.UserEntity;

/**
 *
 * @author Tapio
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
