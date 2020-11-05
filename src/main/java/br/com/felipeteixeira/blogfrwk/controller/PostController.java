package br.com.felipeteixeira.blogfrwk.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeteixeira.blogfrwk.model.Post;
import br.com.felipeteixeira.blogfrwk.repository.PostRepository;
import br.com.felipeteixeira.blogfrwk.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostController {
	
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
    @PostMapping("/post")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }
    
    @GetMapping("/post/{id}")
    public Optional<Post> getPostById(@PathVariable(value = "id") Long id){
    	return postRepository.findById(id);
    }
    
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
    	return postRepository.findAll();
    }
  
    
}
