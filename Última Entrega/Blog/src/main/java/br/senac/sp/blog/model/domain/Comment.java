package br.senac.sp.blog.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="comment_tb")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String text;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="author")
	private User author;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="post")
	private Post post;
	
	public Comment() {
		
	}
	
	public Comment(Long id, String text, LocalDateTime creationDate, LocalDateTime updateDate, User author, Post post) {
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.author = author;
		this.post = post;
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
	
	public String getText() {
		return text;
	}
	
	public void updateText(String text) {
		this.setText(text);
	}

	protected void setText(String text) {
		this.text = text;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getCreationFormatedDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return this.getCreationDate().format(formatter);
	}
	
	public void updateCreationDate(LocalDateTime creationDate) {
		this.setCreationDate(creationDate);
	}

	protected void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public String getUpdateFormatedDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return this.getUpdateDate().format(formatter);
	}
	
	public void updateUpdateDate(LocalDateTime updateDate) {
		this.setUpdateDate(updateDate);
	}

	protected void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
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

	public Post getPost() {
		return post;
	}

	public void updatePost(Post post) {
		this.setPost(post);
	}

	protected void setPost(Post post) {
		this.post = post;
	}
}