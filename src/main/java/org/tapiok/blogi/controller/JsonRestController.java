/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import java.util.List;
import org.tapiok.blogi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tapiok.blogi.model.Post;

/**
 *
 * @author Tapio
 */
@RequestMapping(value = "/**", produces = "application/json")
@Controller
public class JsonRestController {
    
    @Autowired
    PostService postService;
    
    
    @RequestMapping(value = "/posts/", method = RequestMethod.GET)
    @ResponseBody public List<Post> getAllPosts() {
        List<Post> posts = postService.findPosts("");
        return posts;
    }
    
}
