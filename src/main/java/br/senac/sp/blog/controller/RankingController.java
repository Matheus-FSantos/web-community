package br.senac.sp.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.service.RankingService;
import br.senac.sp.blog.model.annotation.RankingControllerAnnotation;

@RankingControllerAnnotation
public class RankingController {
	
	@Autowired
	private RankingService rankingService;
	
	@GetMapping
	public ResponseEntity<List<User>> get() {
		if(rankingService.get().isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(rankingService.get());
	}

}