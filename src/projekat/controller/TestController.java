package projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import projekat.entity.Post;
import projekat.service.PostService;

@Controller
public class TestController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/getJson")
	@ResponseBody
	public List<Post> get() {
		return postService.findAll();
	}
	
	@PostMapping("/")
	@ResponseBody
	public String test() {
		
		Post post = new Post();
		post.setDescription("opis");
		post.setTitle("naslov");
		
		postService.save(post);
		
		return "Test finished";
	}
}
