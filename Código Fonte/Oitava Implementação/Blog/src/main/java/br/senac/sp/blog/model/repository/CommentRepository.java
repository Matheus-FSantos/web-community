package br.senac.sp.blog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.sp.blog.model.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}