package br.senac.sp.blog.model;

import java.io.Serializable;

import java.util.Objects;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="post_tb")
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=160)
	private String title;
	
	@Column(nullable=false, length=75)
	private String text;

	@ManyToOne
	@JoinColumn(nullable=false, name="author_id")
	private User author;
	
	public Post() {}
	
	public Post(Long id, String title, String text, User author) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", author=" + author + "]";
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
}