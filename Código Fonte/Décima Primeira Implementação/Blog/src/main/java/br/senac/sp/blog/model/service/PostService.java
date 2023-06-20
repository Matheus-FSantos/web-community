package br.senac.sp.blog.model.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
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

	@Transactional
	public Post post(Post newPost) {
		newPost.updateCreationDate(LocalDateTime.now());
		newPost.updateUpdateDate(LocalDateTime.now());

		User user = userService.getById(newPost.getAuthor().getId());
		user.updatePoints(user.getPoints() + 5);
		userService.put(user);
		
		newPost.updateAuthor(user);
		return repository.save(newPost);
	}

	@Transactional
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

	@Transactional
	public Boolean put(Post newPost) {
		if(this.getById(newPost.getId()) != null) {
			Post post = this.getById(newPost.getId());
			this.modify(post, newPost);
			repository.save(post);
			
			return true;
		}
		
		return false;
	}
	
	private void modify(Post post, Post newPost) {
		post.updateText(newPost.getText());
		post.updateTitle(newPost.getTitle());
		post.updateUpdateDate(LocalDateTime.now());
	}	
}