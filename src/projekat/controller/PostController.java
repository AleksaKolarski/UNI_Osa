package projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.PostDTO;
import projekat.entity.Post;
import projekat.service.PostServiceInterface;
import projekat.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private UserServiceInterface userService;
	
	// GET ALL
	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts(){
		List<Post> posts = postService.findAll();
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		for(Post post: posts) {
			post.setDate(null);
			post.getUser().setPassword(null);
			postsDTO.add(new PostDTO(post));
		}
		return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
	}
	
	// GET BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> getById(@PathVariable("id") Integer id){
		Post post = postService.findById(id);
		if(post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
		}
		post.setDate(null);
		post.getUser().setPassword(null);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}
	
	// EDIT
	@PutMapping(consumes = "application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO){
		Post post = postService.findById(postDTO.getId());
		if(post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}
		
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		//post.setPhoto(postDTO.getPhoto());
		
		postService.save(post);
		
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}
	
	// CREATE
	@PostMapping(consumes = "application/json")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setUser(userService.findByUsername(postDTO.getUser().getUsername()));
		post.setLikes(0);
		post.setDislikes(0);
		//post.setPhoto(postDTO.getPhoto());
		
		postService.save(post);
		
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.CREATED);
	}
	
	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id){
		Post post = postService.findById(id);
		if(post == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		postService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}