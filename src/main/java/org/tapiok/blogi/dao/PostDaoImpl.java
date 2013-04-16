/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.model.Post;


/**
 *
 * @author Tapio
 */
public class PostDaoImpl implements PostDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void savePost(Post post) {
        entityManager.persist(post);
    }

    @Override
    public void rmPost(Post post) {
        entityManager.remove(post);
    }

    @Override
    public List<Post> retrievePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
