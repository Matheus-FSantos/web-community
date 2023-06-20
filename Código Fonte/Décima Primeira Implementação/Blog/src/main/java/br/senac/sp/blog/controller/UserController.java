package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import br.senac.sp.blog.model.DTO.JWTTokenDTO;
import br.senac.sp.blog.model.DTO.UsersCredentialsDTO;
import br.senac.sp.blog.model.security.jwt.JWTUtil;
import br.senac.sp.blog.model.service.implementation.UserServiceImplementation;
import br.senac.sp.blog.model.validation.UserValidation;
import br.senac.sp.blog.model.exception.BlogException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.service.UserService;
import br.senac.sp.blog.model.annotation.UserControllerAnnotation;

@UserControllerAnnotation
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping
	public ResponseEntity<List<User>> get(){
		if(userService.get().isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(userService.get());
	}
	
	@GetMapping(params = "id")
	public ResponseEntity<User> getById(@RequestParam("id") Long id) {
		if(userService.getById(id) == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(userService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User newUser) throws BlogException {

		//User validations 2 all fields
		UserValidation.validation(newUser);

		try {
			userService.post(newUser);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException exception) {
			throw new BlogException("DataIntegrityViolationException", "This user has already bean registered");
		}
	}

	@PostMapping("/auth")
	public ResponseEntity<JWTTokenDTO> authentication(@RequestBody UsersCredentialsDTO usersCredentialsDTO) throws BlogException {
		User userAuth = new User(null, null, usersCredentialsDTO.getMail(), usersCredentialsDTO.getPassword(), null, null, null);
		UserDetails userDetailsAuth = this.userServiceImplementation.authenticate(userAuth);
		String jwtUtilToken = this.jwtUtil.getToken(userAuth);
		return ResponseEntity.ok().body(new JWTTokenDTO(userAuth.getMail(), jwtUtilToken));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody User newUser) throws BlogException {

		UserValidation.validation(newUser);

		newUser.updateId(id);
		try {
			if(userService.put(newUser))
				return ResponseEntity.noContent().build();
		} catch(DataIntegrityViolationException dataIntegrityViolationException) {
			throw new BlogException("DataIntegrityViolationException", "This user has already bean registered");
		}


		throw new BlogException("User", "User code is invalid!!!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws BlogException {
		if(userService.delete(id))
			return ResponseEntity.noContent().build();

		throw new BlogException("User", "User code is invalid!!!");
	}
}