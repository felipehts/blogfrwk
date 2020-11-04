package br.com.felipeteixeira.blogfrwk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeteixeira.blogfrwk.model.Post;
import br.com.felipeteixeira.blogfrwk.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUser(User user);

}
