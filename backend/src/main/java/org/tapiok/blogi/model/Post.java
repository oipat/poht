package org.tapiok.blogi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Tapio
 */

@Entity
public class Post extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2372401650838806205L;
	
	@ManyToOne
	private User author;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<Comment> comments;
	
	private Date created;
	private Date updated;
	@Column(unique = true)
	private String title;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
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
		StringBuilder sb = new StringBuilder();
		sb.append("Post{" + ", user=" + author + ", created="
				+ created + ", updated=" + updated + ", title=" + title
				+ ", body=" + body + ", comments=[");
		if (comments != null) {
			for (Comment comment : comments) {
				sb.append(comment.toString());
			}
		}
		sb.append("]}");
		return sb.toString();
	}
}
