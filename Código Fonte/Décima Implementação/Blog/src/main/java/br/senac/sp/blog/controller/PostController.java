package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import br.senac.sp.blog.model.exception.BlogException;
import br.senac.sp.blog.model.validation.PostValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.domain.Post;
import br.senac.sp.blog.model.service.PostService;
import br.senac.sp.blog.model.annotation.PostControllerAnnotation;

@PostControllerAnnotation
public class PostController {
	
	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>> get(){
		if(service.get() != null)
			return ResponseEntity.ok().body(service.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getById(@PathVariable Long id){
		if(service.getById(id) != null)
			return ResponseEntity.ok().body(service.getById(id));
			
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> post(@RequestBody Post newPost) throws BlogException {

		PostValidation.validation(newPost);

		service.post(newPost);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Post newPost) throws BlogException {

		PostValidation.validation(newPost);

		newPost.updateId(id);
		if(service.put(newPost))
			return ResponseEntity.noContent().build();
		
		throw new BlogException("Post", "Post code is invalid!!!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws BlogException {
		if(service.delete(id))
			return ResponseEntity.noContent().build();
		
		throw new BlogException("Post", "Post code is invalid!!!");
	}
}