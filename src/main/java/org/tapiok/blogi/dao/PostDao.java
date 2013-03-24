/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.dao;

import java.util.List;
import org.tapiok.blogi.model.Post;

/**
 *
 * @author Tapio
 */
public interface PostDao {
    
    public void savePost(Post post);
    public void rmPost(Post post);
    public List<Post> retrievePost(Post post);
    
}
