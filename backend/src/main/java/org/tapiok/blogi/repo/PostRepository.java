package org.tapiok.blogi.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tapiok.blogi.model.Post;


@RepositoryRestResource(path = "posts", itemResourceRel = "post", collectionResourceRel = "posts")
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByTitle(@Param("title") String title);
    
}
