package br.senac.sp.blog.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.sp.blog.model.domain.Comment;
import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.repository.CommentRepository;

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

	public Comment getById(Long id) {
		if(commentRepository.existsById(id))
			return commentRepository.findById(id).get();

		return null;
	}
	
	public List<Comment> getCommentPostById(Long id) {
		if(postService.getById(id) == null)
			return null;
		
		List<Comment> allComments = this.get();
		List<Comment> comments = new ArrayList<Comment>();
		
		allComments.forEach((comment) -> {
			if(comment.getPost().getId().equals(id)) {
				comments.add(comment);
			}
		});
		
		if(!comments.isEmpty())
			return comments;
		
		return null;
	}

	@Transactional
	public Boolean post(Comment newComment) {
		if(postService.getById(newComment.getPost().getId()) != null) { //verifica se o post id existe
			newComment.updateCreationDate(LocalDateTime.now());
			newComment.updateUpdateDate(LocalDateTime.now());
			
			User user = userService.getById(newComment.getAuthor().getId());
			user.updatePoints(user.getPoints() + 3);
			commentRepository.save(newComment);
			return true;
		}
		
		return false;
	}

	@Transactional
	public Boolean delete(Long id) {
		if(!commentRepository.existsById(id))
			return false;
		
		Comment comment = commentRepository.findById(id).get();
		User user = comment.getAuthor();
		user.updatePoints(user.getPoints() - 5);
		
		userService.put(user);
		commentRepository.deleteById(comment.getId());
		return true;
	}

	@Transactional
	public Boolean put(Comment newComment) {
		if(commentRepository.findById(newComment.getId()).isPresent()) {
			Comment comment = commentRepository.findById(newComment.getId()).get();
			this.modify(comment, newComment);
			commentRepository.save(comment);
			
			return true;
		}
		
		return false;
	}

	private void modify(Comment comment, Comment newComment) {
		comment.updateText(newComment.getText());
		comment.updateUpdateDate(LocalDateTime.now());
	}
}