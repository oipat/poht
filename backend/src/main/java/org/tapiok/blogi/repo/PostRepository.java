package org.tapiok.blogi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tapiok.blogi.model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findById(Long id);
    
}
