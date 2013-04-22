/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tapiok.blogi.model.Post;

/**
 *
 * @author Tapio
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findById(Long id);
    
}
