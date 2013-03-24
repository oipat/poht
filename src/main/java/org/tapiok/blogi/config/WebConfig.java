/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Tapio
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.tapiok.blogi.controller")
public class WebConfig {

    public @Bean SimpleMappingExceptionResolver errorHandler() {
        Logger.getLogger("org.tapiok.blogi.WebConfig").info("WebConfig.errorHandler()");
        SimpleMappingExceptionResolver exceptionSolver = new SimpleMappingExceptionResolver();
        exceptionSolver.setDefaultErrorView("error");
        return exceptionSolver;
    }

    public @Bean InternalResourceViewResolver internalResourceViewResolver() {
        Logger.getLogger("org.tapiok.blogi.WebConfig").info("WebConfig.viewResolver()");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
}
