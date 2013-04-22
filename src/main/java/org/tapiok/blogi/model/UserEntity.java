/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Tapio
 */
@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "userEntity", cascade = {CascadeType.ALL})
    private UserRole userRole;
    @Column(name = "firstName")
    String firstName;
    @Column(name = "surName")
    String surName;
    @Column(unique = true)
    String username;
    String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
    
}
