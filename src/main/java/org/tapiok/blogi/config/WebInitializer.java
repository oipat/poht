package org.tapiok.blogi.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer extends AbstractSecurityWebApplicationInitializer {
    
//    WebInitializer() {
//        super(WebConfig.class);
//    }
//    
//    @Override
//    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(RootConfig.class);
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(WebConfig.class);
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
}
