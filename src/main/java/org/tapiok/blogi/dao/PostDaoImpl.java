/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tapiok.blogi.model.Post;


/**
 *
 * @author Tapio
 */
@Repository
public class PostDaoImpl implements PostDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void savePost(Post post) throws Exception {
        entityManager.persist(post);
	entityManager.flush();
    }

    @Override
    public void rmPost(Post post) throws Exception {
        entityManager.remove(post);
    }

    @Override
    public List<Post> retrievePost(Post post) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
