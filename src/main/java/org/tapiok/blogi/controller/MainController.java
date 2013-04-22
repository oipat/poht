/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.PostRepository;

/**
 *
 * @author Tapio
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @ModelAttribute("posts")
    public List<Post> getPosts() {
        PageRequest pageRequest = new PageRequest(0, 5, Sort.Direction.DESC, "created");
        return postRepository.findAll(pageRequest).getContent();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        Logger.getGlobal().log(Level.INFO, "@ MainController.index()");

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "home");
        return mav;
    }

    @RequestMapping(value = "/postform", method = RequestMethod.GET)
    public ModelAndView post(ModelMap mm) {
        Logger.getGlobal().log(Level.INFO, "@ MainController.post()");

        mm.put("post", new Post());
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("method", "post");
        mav.addObject("page", "postform");
        return mav;
    }

    @RequestMapping(value = "/postform/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable Long id, ModelMap mm) {
        Post postToEdit = postRepository.findById(id);

        mm.put("post", postToEdit);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("method", "put");
        mav.addObject("page", "postform");

        return mav;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView showPost(@PathVariable Long id) {

        Post thePost = postRepository.findById(id);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "showPost");
        mav.addObject("postToShow", thePost);

        return mav;
    }

    @RequestMapping(value = "/postDelete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePostMediator(@PathVariable Long id) {
        deletePost(id);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post deleted!");
        
        return mav;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ModelAndView deletePost(@PathVariable Long id) {
        postRepository.delete(id);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post deleted!");
        
        return mav;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    public ModelAndView editPost(@PathVariable Long id, @ModelAttribute("post") @Valid Post postValues) {

        Post thePost = postRepository.findById(id);
        thePost.setBody(postValues.getBody());
        thePost.setTitle(postValues.getTitle());
        postRepository.saveAndFlush(thePost);

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post edited!");

        return mav;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ModelAndView savePost(@ModelAttribute("post") @Valid Post postValues, BindingResult br, Principal principal) {
        Logger.getGlobal().log(Level.INFO, "@ MainController.savePost() values: " + postValues.toString() + "\nuser: " + principal.getName());

        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("page", "postform");
            return mav;
        }

        ModelAndView mav = new ModelAndView("index");

        try {
            postValues.setAuthor(userRepository.findByUsername(principal.getName()));
            Logger.getGlobal().log(Level.INFO, "found userid: " + userRepository.findByUsername(principal.getName()));
            postRepository.saveAndFlush(postValues);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mav.addObject("page", "message");
        mav.addObject("message", "Post saved!");
        return mav;
    }
}
