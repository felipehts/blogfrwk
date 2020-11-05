package br.com.felipeteixeira.blogfrwk.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotBlank
	@OneToOne
	@JoinColumn(name = "id_user")
	private User user;
	

	@Column(name = "date", length = 0)
	@Temporal(TemporalType.DATE)
	private Date date;

	
	@NotBlank
	@Column(name = "title", length = 500)
	private String title;
	

	@NotBlank
	@Column(name = "description", length = 2000)
	private String description;


	@Column(name = "tags", length = 200)
	private String tags;


	@Column(name = "active", columnDefinition = "TINYINT(1) default 1")
	private boolean active;

	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(orphanRemoval = true, mappedBy = "post", targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Comment> comments;

	public Post() {
		this.active = true;
		this.date = new Date();
		this.comments = new ArrayList<Comment>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", date=" + date + ", title=" + title + ", description="
				+ description + ", tags=" + tags + ", active=" + active + ", comments=" + comments + "]";
	}
	
	

}
