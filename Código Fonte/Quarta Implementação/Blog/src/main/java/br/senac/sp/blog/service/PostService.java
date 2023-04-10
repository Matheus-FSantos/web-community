package br.senac.sp.blog.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import br.senac.sp.blog.model.Post;
import br.senac.sp.blog.model.User;
import br.senac.sp.blog.repository.PostRepository;
import br.senac.sp.blog.repository.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	@Autowired
	private UserService userService;
	
	
	public List<Post> get(){
		if(!repository.findAll().isEmpty())
			return repository.findAll();
		
		return null;
	}
	
	public Post getById(Long id) {
		if(repository.existsById(id))
			return repository.findById(id).get();
		
		return null;
	}
	
	public Post post(Post newPost) {
		Date date = Calendar.getInstance().getTime();
		
		newPost.updateCreationDate(date);
		newPost.updateUpdateDate(date);
		
		
		User user = userService.getById(newPost.getAuthor().getId());
		user.updatePoints(user.getPoints() + 5);
		userService.put(user);
		
		newPost.updateAuthor(user);
		return repository.save(newPost);
	}
	
	public Boolean delete(Long id) {
		if(!repository.existsById(id))
			return false;
		
		Post post = this.getById(id);
		User user = post.getAuthor();
		user.updatePoints(user.getPoints() - 5);
		
		userService.put(user);
		repository.deleteById(post.getId());
		return true;
	}

	public Post put(Post newPost) {
		Post post = this.getById(newPost.getId());
		this.modify(post, newPost);
		return repository.save(post);
	}
	
	private void modify(Post post, Post newPost) {
		post.updateText(newPost.getText());
		post.updateTitle(newPost.getTitle());
		post.updateUpdateDate(Calendar.getInstance().getTime());
	}	
}