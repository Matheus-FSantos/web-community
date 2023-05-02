package br.senac.sp.blog.model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="user_tb")
public class User implements Serializable, Comparable<User> {

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
	
	private Date creationDate;
	
	private Date updateDate;
	
	private Long points;
	
	@JsonIgnore
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Post> posts = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Comment> comments = new ArrayList<>();
	
	public User() {}
	
	public User(Long id, String name, String mail, String password, Date creationDate, Date updateDate) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.points = 10L;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mail=" + mail + ", password=" + password + ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", points=" + points + "]";
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

	public Long getPoints() {
		return points;
	}
	
	public void updatePoints(Long points) {
		this.setPoints(points);
	}

	private void setPoints(Long points) {
		this.points = points;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
}