package br.com.felipeteixeira.blogfrwk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeteixeira.blogfrwk.model.ERole;
import br.com.felipeteixeira.blogfrwk.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}