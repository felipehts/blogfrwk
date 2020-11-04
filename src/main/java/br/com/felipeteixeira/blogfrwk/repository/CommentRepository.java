package br.com.felipeteixeira.blogfrwk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeteixeira.blogfrwk.model.Comment;
import br.com.felipeteixeira.blogfrwk.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPost(Post post);

}
