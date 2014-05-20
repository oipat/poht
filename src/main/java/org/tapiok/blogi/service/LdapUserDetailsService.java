package org.tapiok.blogi.service;

import java.util.Collections;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tapiok.blogi.repo.UserRepository;


@Service
@Transactional(readOnly = true)
public class LdapUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private LdapTemplate ldapTemplate;
    
    @Autowired
    public LdapUserDetailsService(LdapTemplate ldapTemplate) {
    	this.ldapTemplate = ldapTemplate;
	}
    
    // quick and dirty
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Object o = this.ldapTemplate.search("", "(objectClass=person)", new AttributesMapper() {
    		@Override
    		public Object mapFromAttributes(Attributes attributes)
    				throws NamingException {
    			User user = new User(attributes.get("cn").toString(), "38c38e616240647ed7b7b3f64e295dbad2bd5472",
    					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    			return user;
    		}
    		
		});
    	return (User) o;
    }
    	
//            UserEntity domainUser = userRepository.findByUsername(username);
//            boolean enabled = true;
//            boolean accountNonExpired = true;
//            boolean credentialsNonExpired = true;
//            boolean accountNonLocked = true;
//            return new User(
//                    domainUser.getUsername(),
//                    domainUser.getPassword(),
//                    enabled,
//                    accountNonExpired,
//                    credentialsNonExpired,
//                    accountNonLocked,
//                    getAuthorities(domainUser.getUserRole().getUserRoleId()));
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
//        return authList;
//    }
//
//    public List<String> getRoles(Integer role) {
//        List<String> roles = new ArrayList<String>();
//        if (role.intValue() == 1) {
//            roles.add("ROLE_USER");
//        }
//        return roles;
//    }
//
//    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
}