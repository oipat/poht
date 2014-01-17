package org.tapiok.blogi.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tapiok.blogi.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Comment findById(Long id);
    public List<Comment> findByPostId(Long id);
    
}
