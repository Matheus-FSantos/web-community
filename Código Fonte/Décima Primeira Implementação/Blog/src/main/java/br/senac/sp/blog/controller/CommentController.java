package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import br.senac.sp.blog.model.exception.BlogException;
import br.senac.sp.blog.model.validation.CommentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.domain.Comment;
import br.senac.sp.blog.model.service.CommentService;
import br.senac.sp.blog.model.annotation.CommentControllerAnnotation;

@CommentControllerAnnotation
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public ResponseEntity<List<Comment>> get(){
		if(commentService.get().isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(commentService.get());
	}

	@GetMapping(params = "id")
	public ResponseEntity<Comment> getById(@RequestParam("id") Long id){
		if(commentService.getById(id) == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(commentService.getById(id));
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<List<Comment>> getCommentPostById(@PathVariable Long id){
		if(commentService.getCommentPostById(id) == null || commentService.getCommentPostById(id).isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(commentService.getCommentPostById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> post(@RequestBody Comment newComment) throws BlogException {

		CommentValidation.validation(newComment);

		if(commentService.post(newComment)) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newComment.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		throw new BlogException("Comment", "Post code is invalid!!!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Comment newComment) throws BlogException {
		CommentValidation.validation(newComment);

		newComment.updateId(id);

		if(commentService.put(newComment))
			return ResponseEntity.noContent().build();

		throw new BlogException("Comment", "Comment code is invalid!!!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws BlogException {
		if(commentService.delete(id))
			return ResponseEntity.noContent().build();
		
		throw new BlogException("Comment", "Comment code is invalid!!!");
	}
}