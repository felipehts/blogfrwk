package br.com.felipeteixeira.blogfrwk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeteixeira.blogfrwk.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	Optional<User> findById(Long id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}