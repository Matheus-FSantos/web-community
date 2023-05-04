package br.senac.sp.blog.model.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.domain.Post;
import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.repository.PostRepository;

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