/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapiok.blogi.service.impl;

import org.junit.Assert;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tapiok.blogi.config.TestContext;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.service.PostService;
import org.tapiok.blogi.util.TestUtil;

/**
 *
 * @author Tapio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:blogi-appContext.xml"})
@Profile("test")
@WebAppConfiguration
public class PostServiceImplTest {
    
    @Mock
    PostRepository postRepository;
    
    @Autowired
    PostService postService;
    
    @Before
    public void setUp() {
        Mockito.reset(postRepository);
    }
    
    @Test
    public void testAddPost() {
        Post testPost = TestUtil.getDummyPosts().get(0);
        when(postRepository.save(any(Post.class)))
                // return same Post instance as the given argument
                .thenAnswer(new Answer<Post>() {
                    @Override
                    public Post answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (Post) args[0];
                    }
                });
        
        Post returnedPost = postService.addPost(testPost);
        Assert.assertNotNull(returnedPost);
        Assert.assertEquals(testPost, returnedPost);
        
    }
    
}
