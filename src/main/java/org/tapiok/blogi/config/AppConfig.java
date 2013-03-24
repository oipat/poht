/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tapiok.blogi.bean.BlogPost;

/**
 *
 * @author Tapio
 */
@Configuration
public class AppConfig {
    
    public @Bean BlogPost blogPost() {
        return new BlogPost();
    }
    
}
