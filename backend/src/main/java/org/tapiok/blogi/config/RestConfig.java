package org.tapiok.blogi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RestConfig extends RepositoryRestMvcConfiguration {

//	@Override
//	protected void configureRepositoryRestConfiguration(
//			RepositoryRestConfiguration config) {
//		config.exposeIdsFor(Post.class);
//	}

}
