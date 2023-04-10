package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.Post;
import br.senac.sp.blog.service.PostService;

@RestController
@RequestMapping("/posts")
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
	public ResponseEntity<Void> post(@RequestBody Post newPost){
		if(service.post(newPost) != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		return ResponseEntity.status(406).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Post newPost){
		newPost.updateId(id);
		if(service.put(newPost) != null)
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.status(406).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if(service.delete(id))
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.notFound().build();
	}
}