/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapiok.blogi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Tapio
 */
@Configuration
@Import(value = {PersistenceConfig.class})
@ComponentScan(basePackages = "org.tapiok.blogi.service")
public class RootConfig {
    
}
