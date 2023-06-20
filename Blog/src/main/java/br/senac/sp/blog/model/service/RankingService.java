package br.senac.sp.blog.model.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.repository.UserRepository;

@Service
public class RankingService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> get(){
		List<User> users = userRepository.findAll();
		Collections.sort(users);
		
		return users;
	}
}