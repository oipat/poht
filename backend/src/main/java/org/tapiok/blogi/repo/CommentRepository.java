package org.tapiok.blogi.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.tapiok.blogi.model.Comment;

@RestResource(exported = false)
//@RepositoryRestResource(path = "comments", itemResourceRel = "comment", collectionResourceRel = "comments")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    //public Comment findById(Long id);
    
}
