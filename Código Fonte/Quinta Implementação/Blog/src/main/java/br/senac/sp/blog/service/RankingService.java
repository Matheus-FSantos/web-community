package br.senac.sp.blog.service;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.User;
import br.senac.sp.blog.repository.UserRepository;

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