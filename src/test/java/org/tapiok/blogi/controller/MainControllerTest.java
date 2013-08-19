/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tapiok.blogi.config.TestContext;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.model.UserEntity;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.service.PostService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class MainControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepositoryMock;
    @Autowired
    private PostRepository postRepositoryMock;
    @Autowired
    private CommentRepository commentRepositoryMock;
    @Autowired
    PostService postService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(userRepositoryMock);
        Mockito.reset(postRepositoryMock);
        Mockito.reset(commentRepositoryMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public MainControllerTest() {
    }

    @Test
    public void testRootContext() throws Exception {
        when(postRepositoryMock.findAll(any(PageRequest.class)))
                .thenReturn((new PageImpl<Post>(getDummyPosts())));
        when(postRepositoryMock.findAll()).thenReturn(getDummyPosts());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    private List<Post> getDummyPosts() {
        List<Post> posts = new ArrayList<Post>();
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("seppo666");
        Post post1 = new Post();
        post1.setAuthor(userEntity1);
        post1.setBody("body");
        post1.setComments(null);
        post1.setCreated(new Date(System.currentTimeMillis()));
        post1.setId(1L);
        post1.setTitle("title");
        post1.setUpdated(new Date(System.currentTimeMillis()));

        posts.add(post1);

        return posts;
    }
}