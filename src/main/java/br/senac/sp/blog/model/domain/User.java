package br.senac.sp.blog.model.domain;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name="user_tb")
public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, nullable=false)
	private String name;
	
	@Column(length = 160, unique=true, nullable=false)
	private String mail;
	
	@Column(length = 60)
	private String password;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
	private Long points;

	private Boolean admin;
	
	@JsonIgnore
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Post> posts = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Comment> comments = new ArrayList<>();
	
	public User() {}
	
	public User(Long id, String name, String mail, String password, Boolean admin, LocalDateTime creationDate, LocalDateTime updateDate) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.admin = admin;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.points = 10L;
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
	
	@Override
	public int compareTo(User user) {
		if(this.points > user.getPoints())
			return -1;
		if(this.points < user.getPoints())
			return 1;
		else return 0;
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
	
	private void setCreationDate(LocalDateTime creationDate) {
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

	private void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Long getPoints() {
		return points;
	}
	
	public void updatePoints(Long points) {
		this.setPoints(points);
	}

	private void setPoints(Long points) {
		this.points = points;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void updateAdmin(Boolean admin) {
		this.setAdmin(admin);
	}

	private void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
}