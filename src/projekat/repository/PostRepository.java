package projekat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>  {
	
	public List<Post> findByDateAfterOrderByDateDesc(Date date);
	
	public List<Post> findByDateAfterOrderByLikesDesc(Date date);
}
