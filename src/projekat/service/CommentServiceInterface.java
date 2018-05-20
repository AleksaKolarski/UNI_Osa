package projekat.service;

import java.util.List;

import projekat.entity.Comment;

public interface CommentServiceInterface {
	
	Comment findById(Integer commentId);
		
	List<Comment> findAll();
	
	Comment save(Comment comment);
	
	void remove(Integer commentId);
	
}
