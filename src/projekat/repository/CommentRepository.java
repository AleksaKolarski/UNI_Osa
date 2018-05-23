package projekat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	public List<Comment> findByPost_IdIsOrderByDateDesc(Integer postId);
	
	public List<Comment> findByPost_IdIsOrderByLikesDesc(Integer postId);
}
