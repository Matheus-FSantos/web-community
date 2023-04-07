package br.senac.sp.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import br.senac.sp.blog.model.Post;
import br.senac.sp.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
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
		return repository.save(newPost);
	}
	
	public Boolean delete(Long id) {
		if(this.getById(id) != null) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public Post put(Post newPost) {
		Post post = this.getById(newPost.getId());
		this.modify(post, newPost);
		return repository.save(post);
	}
	
	public void modify(Post post, Post newPost) {
		post.updateText(newPost.getText());
		post.updateTitle(newPost.getTitle());
	}
	
	
}