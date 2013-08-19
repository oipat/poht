/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;

/**
 *
 * @author Tapio
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<Post> findPosts(String query) {
        List<Post> returnList = new ArrayList<Post>();
        if (StringUtils.isEmpty(query)) {
            for (Post post : postRepository.findAll()) {
                post.setComments(commentRepository.findByPostId(post.getId()));
                returnList.add(post);
            }
            return returnList;
        }
        else return null;
    }
}
