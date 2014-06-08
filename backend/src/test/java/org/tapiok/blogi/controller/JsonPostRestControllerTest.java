/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tapiok.blogi.config.TestContext;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.service.PostService;
import org.tapiok.blogi.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class JsonPostRestControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private PostService postServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private static final Logger logger = LoggerFactory.getLogger(JsonPostRestControllerTest.class);

    @Before
    public void setUp() {
        Mockito.reset(postServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllPosts() throws Exception {
        when(postServiceMock.getAll())
                .thenReturn(TestUtil.getDummyPosts());

        mockMvc.perform(get("/posts")).andExpect(status().isOk());
        mockMvc.perform(get("/posts/")).andExpect(status().isOk());
    }

    @Test
    public void testPostNewPost() throws Exception {
        when(postServiceMock.savePost(any(Post.class)))
                // return same Post instance as the given argument
                .thenAnswer(new Answer<Post>() {
                    @Override
                    public Post answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (Post) args[0];
                    }
                });
        
        MvcResult result = mockMvc.perform(post("/posts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(TestUtil.getDummyPosts().get(0)))
        )
                .andExpect(status().is(201))
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andReturn();
        
        logger.debug("Returned post: {}", result.getResponse().getContentAsString());
        
    }
    
    @Test
    public void testPutNewPost() throws Exception {
        when(postServiceMock.findById(1)).thenReturn(null);
        when(postServiceMock.savePost(any(Post.class)))
        		// return same Post instance as the given argument
		        .thenAnswer(new Answer<Post>() {
		            @Override
		            public Post answer(InvocationOnMock invocation) throws Throwable {
		                Object[] args = invocation.getArguments();
		                return (Post) args[0];
		            }
		        });
        
        MvcResult result = mockMvc.perform(put("/posts/1")
        		.contentType(TestUtil.APPLICATION_JSON_UTF8)
        		.content(TestUtil.convertObjectToJsonBytes(TestUtil.getDummyPosts().get(0)))
        )
        	.andExpect(status().is(201))
        	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        	.andReturn();
        
        logger.debug("Returned post: {}", result.getResponse().getContentAsString());
    	
    }
    
    @Test
    public void testPutExistingPost() throws Exception {
        when(postServiceMock.findById(1)).thenReturn(TestUtil.getDummyPosts().get(0));
        when(postServiceMock.savePost(any(Post.class)))
		        // return same Post instance as the given argument
		        .thenAnswer(new Answer<Post>() {
		            @Override
		            public Post answer(InvocationOnMock invocation) throws Throwable {
		                Object[] args = invocation.getArguments();
		                return (Post) args[0];
		            }
		        });
        
        MvcResult result = mockMvc.perform(put("/posts/1")
        		.contentType(TestUtil.APPLICATION_JSON_UTF8)
        		.content(TestUtil.convertObjectToJsonBytes(TestUtil.getDummyPosts().get(0)))
        )
        	.andExpect(status().is(200))
        	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        	.andReturn();
        
        logger.debug("Returned post: {}", result.getResponse().getContentAsString());
    	
    }
    
    @Test
    public void testDeletePost() throws Exception {
        when(postServiceMock.findById(1)).thenReturn(TestUtil.getDummyPosts().get(0));
    	MvcResult result = mockMvc.perform(delete("/posts/1"))
    			.andExpect(status().is(200))
    			.andReturn();
    	
    	verify(postServiceMock, times(1)).deleteById(1L);
        logger.debug("Returned: {}", result.getResponse().getContentAsString());
    }
}
