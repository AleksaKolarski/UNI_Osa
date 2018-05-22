package projekat.service;

import java.util.Date;
import java.util.List;

import projekat.entity.Post;

public interface PostServiceInterface {
	
	Post findById(Integer postId);
	
	List<Post> findAll();
	
	Post save(Post post);
	
	void remove(Integer id);
	
	List<Post> findByDateAfterOrderByDateAsc(Date date);
	
	List<Post> findByDateAfterOrderByLikesDesc(Date date);
}
