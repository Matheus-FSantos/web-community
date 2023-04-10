package br.senac.sp.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.senac.sp.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}