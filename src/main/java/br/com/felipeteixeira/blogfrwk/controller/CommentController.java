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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeteixeira.blogfrwk.model.Comment;
import br.com.felipeteixeira.blogfrwk.model.Post;
import br.com.felipeteixeira.blogfrwk.model.User;
import br.com.felipeteixeira.blogfrwk.payload.request.ComentarioRequest;
import br.com.felipeteixeira.blogfrwk.payload.response.MessageResponse;
import br.com.felipeteixeira.blogfrwk.repository.CommentRepository;
import br.com.felipeteixeira.blogfrwk.repository.PostRepository;
import br.com.felipeteixeira.blogfrwk.repository.UserRepository;
import br.com.felipeteixeira.blogfrwk.security.service.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {
	

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	
    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@Valid @RequestBody ComentarioRequest comentarioRequest ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
    	
    	Comment comment = new Comment();
    	
    	comment.setUser(userRepository.findById(userDetails.getId()).get());
    	comment.setPost(postRepository.findById(comentarioRequest.getIdPost()).get());
    	comment.setText(comentarioRequest.getDescription());
    	
    	commentRepository.save(comment);
    	
    	return ResponseEntity.ok(new MessageResponse("Comment registered successfully!"));
    }
    
	@DeleteMapping("/comment/{id_comment}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id_comment") Long commentId) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new Exception("Comment not present for the id :: " + commentId));

		if (comment.getUser().getId().equals(userDetails.getId())) {

			commentRepository.delete(comment);
			return ResponseEntity.ok(new MessageResponse("Comment deleted successfully!"));
		}

		return null;
	}
    
    @GetMapping("/comments/{id_post}")
    public List<Comment> getAllByPost(@PathVariable(value = "id_post") Long postId){
    	return commentRepository.findByPost(postRepository.getOne(postId));
    }

}
