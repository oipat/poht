/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.dao;

import org.tapiok.blogi.model.UserEntity;
import org.tapiok.blogi.model.UserEntity;


/**
 *
 * @author Tapio
 */
public interface UserDao {

    public UserEntity findByUsername(String username);

    public void save(UserEntity u1);
    
}
