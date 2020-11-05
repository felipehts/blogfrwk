package br.com.felipeteixeira.blogfrwk.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeteixeira.blogfrwk.model.Post;
import br.com.felipeteixeira.blogfrwk.payload.response.MessageResponse;
import br.com.felipeteixeira.blogfrwk.repository.PostRepository;
import br.com.felipeteixeira.blogfrwk.repository.UserRepository;
import br.com.felipeteixeira.blogfrwk.security.service.UserDetailsImpl;

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		post.setUser(userRepository.findById(userDetails.getId()).get());
        return postRepository.save(post);
    }
    
	@DeleteMapping("/post/{id_post}")
	public ResponseEntity<?> deletePost(@PathVariable(value = "id_post") Long postId) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new Exception("Post not present for the id :: " + postId));

		if (post.getUser().getId().equals(userDetails.getId())) {

			postRepository.delete(post);
			return ResponseEntity.ok(new MessageResponse("Post deleted successfully!"));
		}

		return null;
	} 
	
    @PutMapping("/post")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post postUpdate) throws  Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		Post post = postRepository.findById(postUpdate.getId())
				.orElseThrow(() -> new Exception("Post not present for the id :: " + postUpdate.getId()));
		
		if (post.getUser().getId().equals(userDetails.getId())) {
			post.setTitle(postUpdate.getTitle());
			post.setDescription(postUpdate.getDescription());
	        final Post updatedPost = postRepository.save(post);
	        return ResponseEntity.ok(updatedPost);
		}
		return null;

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
