package org.tapiok.blogi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/postform/**", "/postDelete/**",
						"/commentDelete/**")
				.hasRole("USER")
				.antMatchers("PUT", "/**")
				.hasRole("USER")
				.antMatchers("POST", "/**")
				.hasRole("USER")
				.antMatchers("DELETE", "/**")
				.hasRole("USER")
				.antMatchers("/comment/*")
				.permitAll()
				.antMatchers("GET", "/**")
				.permitAll()
				.and()
				.formLogin()
				.loginProcessingUrl("/login")
				.and()
				.logout()
				.logoutRequestMatcher(
						new AntPathRequestMatcher("/logout", "GET"))
				// .logoutUrl("/logout")
				.logoutSuccessUrl("/").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
			throws Exception {
		authManagerBuilder.ldapAuthentication().and()
				.userDetailsService(userDetailsService);
		// .passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl("ldap://localhost:389");
		contextSource.setUserDn("cn=admin,dc=nodomain");
		contextSource.setPassword("ibari");
		
		return new LdapTemplate(contextSource);
		
	}
}
