/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.tapiok.blogi.config.AppConfig;
import org.tapiok.blogi.config.ApplicationInitializer;
import org.tapiok.blogi.config.WebConfig;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.model.User;

/**
 *
 * @author Tapio
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class PostTest {
     
    @Configuration
    static class ContextConfiguration {
 
        // this bean will be injected into the OrderServiceTest class
        @Bean
        public PostDao orderService() {
            PostDao postDao = new PostDaoImpl();
            // set properties, etc.
            return postDao;
        }
    }
    
    @Autowired
    private PostDao postDao;
    
    public PostTest() {
    }

    /**
     * Test of savePost method, of class PostDaoImpl.
     */
    @Test
    public void testSavePost() {
        System.out.println("savePost");
        Post post = new Post();
        post.setAuthor(new User());
        post.setBody("message");
        post.setTitle("title");
        post.setCreated(new Date(System.currentTimeMillis()));
        post.setUpdated(new Date(System.currentTimeMillis()));
        
        postDao.savePost(post);
        Assert.assertEquals("1", postDao.retrievePost(post).size());
    }

    /**
     * Test of rmPost method, of class PostDaoImpl.
     */
    @Test
    public void testRmPost() {
        System.out.println("rmPost");
        Post post = null;
        PostDaoImpl instance = new PostDaoImpl();
        instance.rmPost(post);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrievePost method, of class PostDaoImpl.
     */
    @Test
    public void testRetrievePost() {
        System.out.println("retrievePost");
        Post post = null;
        PostDaoImpl instance = new PostDaoImpl();
        List expResult = null;
        List result = instance.retrievePost(post);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}