package org.tapiok.blogi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.service.PostService;


@RequestMapping(produces = "application/json;charset=UTF-8")
@Controller
public class JsonPostRestController {
    
    @Autowired
    PostService postService;
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        List<Post> posts = postService.getAll();
        return posts;
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Post> postPost(@Valid @RequestBody Post post) {
		return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
    	Post findPost = postService.findById(id);
    	if(findPost == null) {
    		return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
    	}
    	else {
        	return new ResponseEntity<Post>(findPost, HttpStatus.OK);
    	}
    }
    
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Post> putPost(@Valid @RequestBody Post post, @PathVariable Long id) {
    	Post findPost = postService.findById(id);
    	if(findPost == null) {
    		return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
    	}
    	else {
    		post.setId(id);
    		return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.OK);
    	}
    }
    
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
    	if(postService.findById(id) == null) {
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	}
    	postService.deleteById(id);
    	return new ResponseEntity<String>(HttpStatus.OK);
    }
}
