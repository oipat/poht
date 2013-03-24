/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tapio
 */
@Controller
final class MainController {
    
    /**
     * 
     * @return View for default index page.
     */
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public final ModelAndView getAll() {
        Logger.getLogger("org.tapiok.blogi.MainController").info("MainController.getAll()");
        return new ModelAndView("index");
    }
}
