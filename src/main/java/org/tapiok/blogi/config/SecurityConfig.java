/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@ImportResource({"classpath:spring-security.xml"})
public class SecurityConfig {

    @Bean
    public DelegatingFilterProxy springSecurityFilterChain() {
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        return filterProxy;
    }
}
