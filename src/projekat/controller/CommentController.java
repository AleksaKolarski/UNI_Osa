package projekat.controller;

import java.util.Date;

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

import projekat.dto.CommentDTO;
import projekat.entity.Comment;
import projekat.service.CommentServiceInterface;
import projekat.service.PostServiceInterface;
import projekat.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentServiceInterface commentService;

	@Autowired
	private PostServiceInterface postService;

	@Autowired
	private UserServiceInterface userService;

	// GET BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") Integer id) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
		}
		comment.setDate(null);
		comment.getUser().setPassword(null);
		comment.getUser().setPhoto(null);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.OK);
	}

	// EDIT
	@PutMapping(consumes = "application/json")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {
		Comment comment = commentService.findById(commentDTO.getId());
		if (comment == null) {
			return new ResponseEntity<CommentDTO>(HttpStatus.BAD_REQUEST);
		}
		comment.setTitle(commentDTO.getTitle());
		comment.setDescription(commentDTO.getDescription());
		commentService.save(comment);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.OK);
	}

	// CREATE
	@PostMapping(consumes = "application/json")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setTitle(commentDTO.getTitle());
		comment.setDescription(commentDTO.getDescription());
		comment.setDate(new Date());
		comment.setLikes(0);
		comment.setDislikes(0);
		comment.setPost(postService.findById(commentDTO.getPost().getId()));
		comment.setUser(userService.findByUsername(commentDTO.getUser().getUsername()));
		commentService.save(comment);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.CREATED);
	}

	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		commentService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// LIKE COMMENT
	@PostMapping(value = "/{id}/like")
	public ResponseEntity<Integer> likeComment(@PathVariable("id") Integer id) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		comment.setLikes(comment.getLikes() + 1);
		commentService.save(comment);
		return new ResponseEntity<Integer>(comment.getLikes(), HttpStatus.OK);
	}

	// DISLIKE COMMENT
	@PostMapping(value = "/{id}/dislike")
	public ResponseEntity<Integer> dislikeComment(@PathVariable("id") Integer id) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		comment.setDislikes(comment.getDislikes() + 1);
		commentService.save(comment);
		return new ResponseEntity<Integer>(comment.getDislikes(), HttpStatus.OK);
	}
}
