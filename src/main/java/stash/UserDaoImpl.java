/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stash;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.tapiok.blogi.model.UserEntity;
import org.tapiok.blogi.model.UserEntity;

/**
 *
 * @author Tapio
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public UserEntity findByUsername(String username) {
        UserEntity findThis = new UserEntity();
        findThis.setUsername(username);
        UserEntity foundThis = entityManager.find(UserEntity.class, findThis);
        return foundThis;
    }

    @Override
    public void save(UserEntity u1) {
        entityManager.persist(u1);
    }
    
}
