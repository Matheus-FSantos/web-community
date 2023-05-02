package br.senac.sp.blog.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.Comment;
import br.senac.sp.blog.model.User;
import br.senac.sp.blog.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	public List<Comment> get(){
		return commentRepository.findAll();
	}
	
	public List<Comment> getById(Long id) {
		if(postService.getById(id) == null)
			return null;
		
		List<Comment> allComments = this.get();
		List<Comment> comments = new ArrayList<Comment>();
		
		allComments.forEach((comment) -> {
			if(comment.getPost().getId() == id) {
				comments.add(comment);
			}
		});
		
		if(!comments.isEmpty())
			return comments;
		
		return null;
	}

	public Comment post(Comment newComment) {
		Date date = Calendar.getInstance().getTime();
		
		newComment.updateCreationDate(date);
		newComment.updateUpdateDate(date);
		
		User user = userService.getById(newComment.getAuthor().getId());
		user.updatePoints(user.getPoints() + 3);
		return commentRepository.save(newComment);
	}
}