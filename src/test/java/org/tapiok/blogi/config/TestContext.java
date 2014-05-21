package org.tapiok.blogi.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.service.PostService;


@Configuration
@Import(WebConfig.class)
public class TestContext {

    @Bean
    public CommentRepository commentRepository() {
        return Mockito.mock(CommentRepository.class);
    }
    
    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }
    
    @Bean
    public PostService postService() {
        return Mockito.mock(PostService.class);
    }
}
