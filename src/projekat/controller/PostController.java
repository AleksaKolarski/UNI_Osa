package projekat.controller;

import java.util.ArrayList;
import java.util.Date;
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

import projekat.dto.CommentDTO;
import projekat.dto.PostDTO;
import projekat.dto.TagDTO;
import projekat.entity.Comment;
import projekat.entity.Post;
import projekat.entity.Tag;
import projekat.service.CommentServiceInterface;
import projekat.service.PostServiceInterface;
import projekat.service.TagServiceInterface;
import projekat.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostServiceInterface postService;

	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private CommentServiceInterface commentService;
	
	@Autowired
	private TagServiceInterface tagService;

	// GET ALL
	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts() {
		List<Post> posts = postService.findAll();
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		for (Post post : posts) {
			post.getUser().setPassword(null);
			post.getUser().setPhoto(null);
			postsDTO.add(new PostDTO(post));
		}
		return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
	}

	// GET ALL (date limit, order by date)
	@PostMapping(value = "/orderByDate")
	public ResponseEntity<List<PostDTO>> getPostsOrderByDate(@RequestBody Date date) {
		List<Post> posts = postService.findByDateAfterOrderByDateDesc(date);
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		for (Post post : posts) {
			post.getUser().setPassword(null);
			post.getUser().setPhoto(null);
			postsDTO.add(new PostDTO(post));
		}
		return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
	}

	// GET ALL (date limit, order by likes)
	@PostMapping(value = "/orderByLikes")
	public ResponseEntity<List<PostDTO>> getPostsOrderByLikes(@RequestBody Date date) {
		List<Post> posts = postService.findByDateAfterOrderByLikesDesc(date);
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		for (Post post : posts) {
			post.getUser().setPassword(null);
			post.getUser().setPhoto(null);
			postsDTO.add(new PostDTO(post));
		}
		return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
	}

	// GET BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> getById(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
		}
		post.getUser().setPassword(null);
		post.getUser().setPhoto(null);
		post.setTags(tagService.findByPost_Id(post.getId()));
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}

	// GET COMMENTS
	@GetMapping(value = "/{id}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null) {
			return new ResponseEntity<List<CommentDTO>>(HttpStatus.NOT_FOUND);
		}
		List<Comment> comments = post.getComments();
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for (Comment comment : comments) {
			comment.getUser().setPassword(null);
			commentsDTO.add(new CommentDTO(comment));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}

	// GET ALL COMMENTS (order by date)
	@GetMapping(value = "/{id}/comments/orderByDate")
	public ResponseEntity<List<CommentDTO>> getCommentsOrderByDate(@PathVariable("id") Integer postId) {
		List<Comment> comments = commentService.findByPost_IdIsOrderByDateDesc(postId);
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for (Comment comment: comments) {
			comment.getUser().setPassword(null);
			commentsDTO.add(new CommentDTO(comment));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}

	// GET ALL COMMENTS (order by likes)
	@GetMapping(value = "/{id}/comments/orderByLikes")
	public ResponseEntity<List<CommentDTO>> getCommentsOrderByLikes(@PathVariable("id") Integer postId) {
		List<Comment> comments = commentService.findByPost_IdIsOrderByLikesDesc(postId);
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for (Comment comment : comments) {
			comment.getUser().setPassword(null);
			commentsDTO.add(new CommentDTO(comment));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}
	
	// GET TAGS
	@GetMapping(value = "/{id}/tags")
	public ResponseEntity<List<TagDTO>> getTags(@PathVariable("id") Integer id){
		List<Tag> tags = tagService.findByPost_Id(id);
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		for(Tag tag: tags) {
			tagsDTO.add(new TagDTO(tag));
		}
		return new ResponseEntity<List<TagDTO>>(tagsDTO, HttpStatus.OK);
	}
	
	// EDIT
	@PutMapping(consumes = "application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
		Post post = postService.findById(postDTO.getId());
		if (post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}

		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setPhoto(postDTO.getPhoto());

		postService.save(post);

		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}

	// CREATE
	@PostMapping(consumes = "application/json")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setUser(userService.findByUsername(postDTO.getUser().getUsername()));
		post.setLikes(0);
		post.setDislikes(0);
		post.setPhoto(postDTO.getPhoto());
		post.setDate(new Date());
		System.out.println("Latitude: " + postDTO.getLatitude());
		System.out.println("Longitude: " + postDTO.getLongitude());
		post.setLatitude(postDTO.getLatitude());
		post.setLongitude(postDTO.getLongitude());
		List<TagDTO> tagsDTO = postDTO.getTags();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		
		for(TagDTO tagDTO: tagsDTO) {
			Tag tag = tagService.findOne(tagDTO.getId());
			post.getTags().add(tag);
		}

		postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.CREATED);
	}

	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		postService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// LIKE POST
	@PostMapping(value = "/{id}/like")
	public ResponseEntity<Integer> likePost(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		post.setLikes(post.getLikes() + 1);
		postService.save(post);
		return new ResponseEntity<Integer>(post.getLikes(), HttpStatus.OK);
	}

	// DISLIKE POST
	@PostMapping(value = "/{id}/dislike")
	public ResponseEntity<Integer> dislikePost(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		post.setDislikes(post.getDislikes() + 1);
		postService.save(post);
		return new ResponseEntity<Integer>(post.getDislikes(), HttpStatus.OK);
	}
}
