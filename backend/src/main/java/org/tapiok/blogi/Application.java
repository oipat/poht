package org.tapiok.blogi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tapiok.blogi.model.Comment;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.model.User;
import org.tapiok.blogi.model.UserRole;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.repo.UserRoleRepository;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	@Profile("dev")
	CommandLineRunner init(final PostRepository postRepo, final CommentRepository commentRepo, final UserRepository userRepo, final UserRoleRepository userRoleRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				DateFormat df = new SimpleDateFormat("dd.MM.yy");
				UserRole r1 = new UserRole();
				r1.setRoleName("pomo");
				userRoleRepo.save(r1);
				User u1 = new User();
				u1.setUsername("ismo");
				u1.setPassword("$2a$10$DUPVOOoWl6MIy9nj93b6Ku7mnvfXde/Is7uJTSQlcoeqiGg0pktQK");
				u1.setFirstName("Ismo");
				u1.setSurName("Laitela");
				u1.setUserRole(r1);
				userRepo.save(u1);
				
				for(int i = 1; i <= 10; i++) {
					Post p1 = new Post();
					p1.setAuthor(u1);
					p1.setBody("testpostbody" + i);
					p1.setTitle("testposttitle" + i);
					p1.setCreated(df.parse("14.07.2014"));
					p1.setUpdated(df.parse("14.07.2014"));
					Comment c1 = new Comment();
					c1.setAuthor("testcommenter" + i);
					c1.setBody("tetcommentbody" + i);
					c1.setCreated(df.parse("15.07.2014"));
					p1.setComments(ImmutableList.of(c1));
					postRepo.save(p1);
				}
				
			}
		};
	}

}