/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapiok.blogi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tapio
 */
@Entity
public class Post implements Serializable {

    public Post() {
    }
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private UserEntity author;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Comment> comments;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;
    @NotNull @Size(min=2, max=20)
    @Column(name = "title")
    private String title;
    @NotNull @Size(min=2, max=20000)
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @PrePersist
    private void onCreate() {
	updated = created = new Date();
    }

    @PreUpdate
    private void onUpdate() {
	updated = new Date();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comment) {
        this.comments = comment;
    }
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserEntity getAuthor() {
	return author;
    }

    public void setAuthor(UserEntity author) {
	this.author = author;
    }

    public Date getCreated() {
	return created;
    }

    public void setCreated(Date created) {
	this.created = created;
    }

    public Date getUpdated() {
	return updated;
    }

    public void setUpdated(Date updated) {
	this.updated = updated;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    @Override
    public String toString() {
	return "Post{" + "id=" + id + ", author=" + author + ", created=" + created + ", updated=" + updated + ", title=" + title + ", body=" + body + '}';
    }
}
