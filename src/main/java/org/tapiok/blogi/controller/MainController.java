/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tapiok.blogi.dao.PostDao;
import org.tapiok.blogi.dao.UserDao;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.model.Post;

/**
 *
 * @author Tapio
 */

@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    PostDao postDao;
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        Logger.getGlobal().log(Level.INFO, "@ MainController.index()");
        
	ModelAndView mav = new ModelAndView("index");
	mav.addObject("page", "home");
	return mav;
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public ModelAndView post(ModelMap mm) {
        Logger.getGlobal().log(Level.INFO, "@ MainController.post()");
	
	mm.put("post", new Post());
	ModelAndView mav = new ModelAndView("index");
	mav.addObject("page", "post");
	return mav;
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ModelAndView savePost(@ModelAttribute("post") Post postValues, Principal principal) {
        Logger.getGlobal().log(Level.INFO, "@ MainController.savePost() values: " + postValues.toString() + "\nuser: " + principal.getName());
	ModelAndView mav = new ModelAndView();
	try {
            postValues.setAuthor(userRepository.findByUsername(principal.getName()));
            Logger.getGlobal().log(Level.INFO, "found userid: " + userRepository.findByUsername(principal.getName()));
	    postDao.savePost(postValues);
	} catch (Exception ex) {
	    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	mav.addObject("page", "saved");
	return mav;
    }
    
}
