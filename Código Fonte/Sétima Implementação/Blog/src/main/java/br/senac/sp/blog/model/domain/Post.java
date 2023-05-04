package br.senac.sp.blog.model.domain;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="post_tb")
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=75)
	private String title;
	
	@Column(nullable=false)
	private String text;
	
	private Date creationDate;
	
	private Date updateDate;

	@ManyToOne
	@JoinColumn(nullable=false, name="author_id")
	private User author;
	
	@JsonIgnore
	@OneToMany(mappedBy="post", orphanRemoval=true)
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {}
	
	public Post(Long id, String title, String text, User author, Date creationDate, Date updateDate) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.author = author;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", author=" + author + ", creationDate=" + creationDate + ", updateDate=" + updateDate + "]";
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
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void updateId(Long id) {
		this.setId(id);
	}
	
	protected void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void updateTitle(String title) {
		this.setTitle(title);
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}
	
	public void updateText(String text) {
		this.setText(text);
	}

	protected void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}
	
	public void updateAuthor(User author) {
		this.setAuthor(author);
	}

	protected void setAuthor(User author) {
		this.author = author;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public String getFormatedCreationDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
		String date = dateFormat.format(this.creationDate);
		
		return date;
	}
	
	public void updateCreationDate(Date creationDate) {
		this.setCreationDate(creationDate);
	}
	
	private void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public String getFormatedUpdateDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
		String date = dateFormat.format(this.updateDate);
		
		return date;
	}
	
	public void updateUpdateDate(Date updateDate) {
		this.setUpdateDate(updateDate);
	}

	private void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
}