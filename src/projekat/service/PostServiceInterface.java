package projekat.service;

import java.util.List;

import projekat.entity.Post;

public interface PostServiceInterface {
	
	Post findById(Integer postId);
	
	List<Post> findAll();
	
	Post save(Post post);
	
	void remove(Integer id);
	
}
