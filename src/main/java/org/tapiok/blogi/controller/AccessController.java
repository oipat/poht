/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tapio
 */
@Controller
@RequestMapping
public class AccessController {

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure22!";
        return "redirect:/login?message=" + message;
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
        String message = "Logout Success!";
        return "redirect:/login?message=" + message;
    }
}