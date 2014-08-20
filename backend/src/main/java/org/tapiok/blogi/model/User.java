package org.tapiok.blogi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7873228695885631320L;
	
	
    @ManyToOne
    @JsonIgnore
    @RestResource(exported = false)
    private UserRole userRole;
    private String firstName;
    private String surName;
    private String username;
    
    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

	public UserRole getUserRole() {
        return userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
