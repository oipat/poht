/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

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
    public ModelAndView savePost(@ModelAttribute("post") Post postValues) {
        Logger.getGlobal().log(Level.INFO, "@ MainController.savePost() values: " + postValues.toString());
	ModelAndView mav = new ModelAndView();
	try {
	    postDao.savePost(postValues);
	} catch (Exception ex) {
	    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	mav.addObject("page", "saved");
	return mav;
    }
    
}
