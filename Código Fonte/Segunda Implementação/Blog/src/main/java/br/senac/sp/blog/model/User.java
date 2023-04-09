package br.senac.sp.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_tb")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45)
	private String name;
	@Column(length = 160, unique=true)
	private String mail;
	
	@Column(length = 30)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Post> posts = new ArrayList<>();
	
	public User() {}
	
	public User(Long id, String name, String mail, String password) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mail=" + mail + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}
	
	public void updateId(Long id) {
		this.setId(id);
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void updateName(String name) {
		this.setName(name);
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}
	
	public void updateMail(String mail) {
		this.setMail(mail);
	}

	private void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}
	
	public void updatePassword(String password) {
		this.setPassword(password);
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}
}