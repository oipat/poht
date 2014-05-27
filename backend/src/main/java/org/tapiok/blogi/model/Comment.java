package org.tapiok.blogi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Comment implements Serializable {
    
	private static final long serialVersionUID = 6132154323678280845L;
	
	@Id
    @GeneratedValue
    private Long id;
    @NotNull @Size(min=2, max=20)
    private String author;
    @NotNull @Size(min=2, max=2000)
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Comment() {
    }
    
    @PrePersist
    private void onCreate() {
	created = new Date();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	@Override
	public String toString() {
		return "Comment {id=" + id + ", author=" + author
				+ ", body=" + body + ", created=" + created + "}";
	}
    
}
