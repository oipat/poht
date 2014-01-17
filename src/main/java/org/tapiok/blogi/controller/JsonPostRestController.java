package org.tapiok.blogi.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        List<Post> posts = postService.findPosts("");
        return posts;
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseBody
    public Post postPost(@Valid @RequestBody Post post) {
        return postService.addPost(post);
    }
    
}
