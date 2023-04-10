package br.senac.sp.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.User;
import br.senac.sp.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> get(){
		return userRepository.findAll();
	}
	
	public User getById(Long id) {
		if(!userRepository.existsById(id))
			return null;
		
		return userRepository.findById(id).get();
	}
	
	public User post(User newUser) {
		newUser.updatePoints(10L);
		return userRepository.save(newUser);
	}
	
	public Boolean delete(Long id) {
		if(!userRepository.existsById(id))
			return false;
		
		userRepository.deleteById(id);
		return true;
			
	}
	
	public User put(User newUser) {
		User user = this.getById(newUser.getId());
		this.modify(user, newUser);
		return userRepository.save(user);
	}
	
	private void modify(User user, User newUser) {
		user.updateMail(newUser.getMail());
		user.updateName(newUser.getName());
		user.updatePassword(newUser.getPassword());
	}
}