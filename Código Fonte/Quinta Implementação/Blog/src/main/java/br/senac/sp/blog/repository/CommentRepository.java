package br.senac.sp.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.sp.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}