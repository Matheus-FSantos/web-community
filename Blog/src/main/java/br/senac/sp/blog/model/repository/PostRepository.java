package br.senac.sp.blog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.sp.blog.model.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}