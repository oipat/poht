/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.service;

import java.util.List;
import org.tapiok.blogi.model.Post;

public interface PostService {
    public List<Post> findPosts(String query);
    public Post addPost(Post post);
}
