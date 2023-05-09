package br.senac.sp.blog.model.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.repository.UserRepository;

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

	@Transactional
	public User post(User newUser) {
		newUser.updateCreationDate(LocalDateTime.now());
		newUser.updateUpdateDate(LocalDateTime.now());
		newUser.updatePoints(10L);
		return userRepository.save(newUser);
	}

	@Transactional
	public Boolean delete(Long id) {
		if(!userRepository.existsById(id))
			return false;
		
		userRepository.deleteById(id);
		return true;
			
	}

	@Transactional
	public Boolean put(User newUser) {
		if(this.getById(newUser.getId()) != null) {
			User user = this.getById(newUser.getId());
			this.modify(user, newUser);
			userRepository.save(user);
			
			return true;
		}

		return false;
	}
	
	private void modify(User user, User newUser) {
		user.updateMail(newUser.getMail());
		user.updateName(newUser.getName());
		user.updateUpdateDate(LocalDateTime.now());
		user.updatePassword(newUser.getPassword());
	}
}