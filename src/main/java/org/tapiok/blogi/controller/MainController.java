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

/**
 *
 * @author Tapio
 */

@Controller
@RequestMapping("/")
public class MainController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        Logger.getGlobal().log(Level.INFO, "@ MainController.index()");
        return "index";
    }
    
}
