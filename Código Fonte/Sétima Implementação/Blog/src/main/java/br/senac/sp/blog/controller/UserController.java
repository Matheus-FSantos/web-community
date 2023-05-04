package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> get(){
		if(userService.get().isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(userService.get());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		if(userService.getById(id) == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(userService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User newUser){
		userService.post(newUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody User newUser){
		newUser.updateId(id);
		if(userService.put(newUser))
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if(userService.delete(id))
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.notFound().build();
	}
}