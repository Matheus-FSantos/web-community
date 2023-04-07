package br.senac.sp.blog.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=160)
	private String title;
	
	@Column(nullable=false, length=75)
	private String text;
	
	public Post() {}
	
	public Post(Long id, String title, String text) {
		this.id = id;
		this.title = title;
		this.text = text;
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
}