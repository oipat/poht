/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapiok.blogi.service.impl;

import org.junit.Assert;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.service.PostService;
import org.tapiok.blogi.service.PostServiceImpl;
import org.tapiok.blogi.util.TestUtil;


public class PostServiceImplTest {
    
    @Mock
    PostRepository postRepository;
    
    @Mock
    CommentRepository commentRepository;
    
    PostService postService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postService = new PostServiceImpl(postRepository, commentRepository);
        
        Mockito.reset(postRepository);
    }
    
    @Test
    public void testAddPost() {
        Post testPost = TestUtil.getDummyPosts().get(0);
        when(postRepository.saveAndFlush(any(Post.class)))
                // return same Post instance as the given argument
                .thenAnswer(new Answer<Post>() {
                    @Override
                    public Post answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (Post) args[0];
                    }
                });
        
        Post returnedPost = postService.savePost(testPost);
        verify(postRepository, times(1)).saveAndFlush(any(Post.class));
        Assert.assertNotNull(returnedPost);
        Assert.assertEquals(testPost, returnedPost);
    }
    
}
